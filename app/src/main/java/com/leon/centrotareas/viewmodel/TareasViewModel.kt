package com.leon.centrotareas.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leon.centrotareas.data.TareasRepository
import com.leon.centrotareas.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
class TareasViewModel(private val repo:TareasRepository):ViewModel() {
    private val _mensaje=MutableStateFlow("Listo")
    val mensaje:StateFlow<String> = _mensaje.asStateFlow()
    private val _primos=MutableStateFlow<PrimosUi>(PrimosUi.Inicial)
    val primos:StateFlow<PrimosUi> = _primos.asStateFlow()
    private val _carga=MutableStateFlow<CargaUi>(CargaUi.Inicial)
    val carga:StateFlow<CargaUi> = _carga.asStateFlow()
    private val _progreso=MutableStateFlow(0f)
    val progreso:StateFlow<Float> = _progreso.asStateFlow()
    private var trabajo:Job?=null
    private val _corriendo=MutableStateFlow(false)
    val corriendo:StateFlow<Boolean> = _corriendo.asStateFlow()
    fun cancelar() { if(_corriendo.value) { trabajo?.cancel();_mensaje.value="Cancelado" } }
    fun tareaLarga() {
        if(_corriendo.value)return
        _corriendo.value=true
        trabajo=viewModelScope.launch {
            try {
            _mensaje.value="Trabajando..."
            Log.d("HILO","Antes: ${Thread.currentThread().name}")
            _progreso.value=0f
            repo.progresoTarea().collect { _progreso.value=it }
            Log.d("HILO","Después: ${Thread.currentThread().name}")
            _mensaje.value="Terminó"
            } catch(e:CancellationException) { _mensaje.value="Cancelado";throw e }
            finally { _corriendo.value=false }
        }
    }
    private var calculo:Job?=null
    fun contar(hasta:Int) {
        if(hasta<2 || _primos.value==PrimosUi.Calculando)return
        _primos.value=PrimosUi.Calculando
        calculo=viewModelScope.launch {
            try {
                val inicio=System.currentTimeMillis()
                val cantidad=repo.contarPrimos(hasta)
                _primos.value=PrimosUi.Listo(cantidad,System.currentTimeMillis()-inicio)
            } catch(e:CancellationException) { _primos.value=PrimosUi.Cancelado;throw e }
        }
    }
    fun cancelarPrimos() { calculo?.cancel() }
    private val _simularError=MutableStateFlow(false)
    val simularError:StateFlow<Boolean> = _simularError.asStateFlow()
    fun cambiarSimularError(valor:Boolean) { _simularError.value=valor }
    private var cargaJob:Job?=null
    fun cargarUnaPorUna()=cargar(false)
    fun cargarALaVez()=cargar(true)
    fun cancelarCarga() { cargaJob?.cancel() }
    private fun cargar(paralelo:Boolean) {
        if(_carga.value==CargaUi.Cargando)return
        _carga.value=CargaUi.Cargando
        val fallo=_simularError.value
        val usarLimite=_limite.value
        val automatico=_reintentar.value
        cargaJob=viewModelScope.launch {
            try {
                val inicio=System.currentTimeMillis()
                val (perfil,pedidos)=conReintentos(automatico) { conLimite(usarLimite) { if(paralelo)coroutineScope {
                    val a=async { repo.cargarPerfil() }
                    val c=async { repo.cargarPedidos(fallo) }
                    a.await() to c.await()
                } else repo.cargarPerfil() to repo.cargarPedidos(fallo) } }
                _carga.value=CargaUi.Exito(if(paralelo)"A la vez" else "Una por una",perfil,pedidos.size,System.currentTimeMillis()-inicio)
            } catch(e:TimeoutCancellationException) { _carga.value=CargaUi.Error("Tardó demasiado") }
            catch(e:CancellationException) { _carga.value=CargaUi.Inicial;throw e }
            catch(e:Exception) { _carga.value=CargaUi.Error("No se pudo cargar: ${e.message ?: "Error inesperado"}") }
        }
    }
    private val _limite=MutableStateFlow(false)
    val limite:StateFlow<Boolean> = _limite.asStateFlow()
    fun cambiarLimite(valor:Boolean) { _limite.value=valor }
    private suspend fun <T> conLimite(usar:Boolean,bloque:suspend ()->T):T =
        if(usar) withTimeout(2000) { bloque() } else bloque()
    private val _reintentar=MutableStateFlow(false)
    val reintentar:StateFlow<Boolean> = _reintentar.asStateFlow()
    private val _intento=MutableStateFlow(0)
    val intento:StateFlow<Int> = _intento.asStateFlow()
    fun cambiarReintento(valor:Boolean) { _reintentar.value=valor }
    private suspend fun <T> conReintentos(automatico:Boolean,bloque:suspend ()->T):T {
        var repeticion=0
        while(true) {
            _intento.value=repeticion+1
            try { return bloque() }
            catch(e:CancellationException) { throw e }
            catch(e:java.io.IOException) {
                if(!automatico || repeticion>=3)throw e
                delay(500L shl repeticion);repeticion++
            }
        }
    }
}
