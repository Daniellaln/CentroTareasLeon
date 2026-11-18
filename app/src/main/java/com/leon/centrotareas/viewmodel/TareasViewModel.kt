package com.leon.centrotareas.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leon.centrotareas.data.TareasRepository
import com.leon.centrotareas.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
class TareasViewModel(private val repo:TareasRepository,private val almacen:com.leon.centrotareas.data.HistorialRepository=com.leon.centrotareas.data.HistorialMemoria()):ViewModel() {
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
                _progresoPrimos.value=0f
                var cantidad=0
                repo.progresoPrimos(hasta).collect { _progresoPrimos.value=it.progreso;cantidad=it.cantidad }
                val duracion=System.currentTimeMillis()-inicio
                _primos.value=PrimosUi.Listo(cantidad,duracion)
                guardar("Primos","$cantidad hasta $hasta",duracion)
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
                val duracion=System.currentTimeMillis()-inicio
                val modo=if(paralelo)"A la vez" else "Una por una"
                _carga.value=CargaUi.Exito(modo,perfil,pedidos.size,duracion)
                _mediciones.value=(_mediciones.value+MedicionCarga(++numeroMedicion,modo,duracion)).takeLast(20)
                guardar("Cargas",modo,duracion)
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
    private val _progresoPrimos=MutableStateFlow(0f)
    val progresoPrimos:StateFlow<Float> = _progresoPrimos.asStateFlow()
    val historial=almacen.resultados.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),emptyList())
    private val _aviso=MutableStateFlow<String?>(null)
    val aviso:StateFlow<String?> = _aviso.asStateFlow()
    private suspend fun guardar(tipo:String,resumen:String,ms:Long) {
        try { almacen.agregar(ResultadoGuardado(java.util.UUID.randomUUID().toString(),tipo,resumen,ms,System.currentTimeMillis())) }
        catch(e:CancellationException) { throw e }
        catch(e:Exception) { _aviso.value="El resultado se obtuvo, pero no se pudo guardar" }
    }
    private val _mediciones=MutableStateFlow<List<MedicionCarga>>(emptyList())
    val mediciones:StateFlow<List<MedicionCarga>> = _mediciones.asStateFlow()
    private var numeroMedicion=0
    private val _pausas=MutableStateFlow<List<Float>>(emptyList())
    val pausas:StateFlow<List<Float>> = _pausas.asStateFlow()
    private val _mayorPausa=MutableStateFlow(0f)
    val mayorPausa:StateFlow<Float> = _mayorPausa.asStateFlow()
    private var fotogramaAnterior=0L
    private var ultimaMuestra=0L
    private var midiendo=false
    fun reiniciarFotogramas() { fotogramaAnterior=0;ultimaMuestra=0;midiendo=false }
    fun registrarFotograma(nanos:Long) {
        val ocupado=_corriendo.value || _primos.value==PrimosUi.Calculando || _carga.value==CargaUi.Cargando
        if(!ocupado) { fotogramaAnterior=0;midiendo=false;return }
        if(!midiendo) { _pausas.value=emptyList();_mayorPausa.value=0f;midiendo=true;fotogramaAnterior=nanos;ultimaMuestra=nanos;return }
        if(fotogramaAnterior!=0L && nanos>fotogramaAnterior) {
            val ms=(nanos-fotogramaAnterior)/1_000_000f
            _mayorPausa.value=maxOf(_mayorPausa.value,ms)
            if(nanos-ultimaMuestra>=250_000_000L) {
                _pausas.value=(_pausas.value+ms).takeLast(40);ultimaMuestra=nanos
            }
        }
        fotogramaAnterior=nanos
    }
}
