package com.tecsup.centrotareas.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.centrotareas.data.TareasRepository
import com.tecsup.centrotareas.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
class TareasViewModel(private val repo:TareasRepository):ViewModel() {
    private val _mensaje=MutableStateFlow("Listo")
    val mensaje:StateFlow<String> = _mensaje.asStateFlow()
    private val _primos=MutableStateFlow<PrimosUi>(PrimosUi.Inicial)
    val primos:StateFlow<PrimosUi> = _primos.asStateFlow()
    private val _carga=MutableStateFlow<CargaUi>(CargaUi.Inicial)
    val carga:StateFlow<CargaUi> = _carga.asStateFlow()
}
