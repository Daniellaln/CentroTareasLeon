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
            withContext(Dispatchers.Default) {
                Log.d("HILO","Dentro: ${Thread.currentThread().name}")
                for(i in 1..100) { delay(40);_progreso.value=i/100f }
            }
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
}
