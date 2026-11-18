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
    fun tareaLarga() {
        viewModelScope.launch {
            _mensaje.value="Trabajando..."
            Log.d("HILO","Antes: ${Thread.currentThread().name}")
            _progreso.value=0f
            withContext(Dispatchers.Default) {
                Log.d("HILO","Dentro: ${Thread.currentThread().name}")
                for(i in 1..100) { delay(40);_progreso.value=i/100f }
            }
            Log.d("HILO","Después: ${Thread.currentThread().name}")
            _mensaje.value="Terminó"
        }
    }
}
