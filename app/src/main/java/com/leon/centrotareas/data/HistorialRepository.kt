package com.leon.centrotareas.data
import com.leon.centrotareas.model.ResultadoGuardado
import kotlinx.coroutines.flow.*
interface HistorialRepository {
    val resultados:Flow<List<ResultadoGuardado>>
    suspend fun agregar(resultado:ResultadoGuardado)
}
class HistorialMemoria:HistorialRepository {
    private val datos=MutableStateFlow<List<ResultadoGuardado>>(emptyList())
    override val resultados:Flow<List<ResultadoGuardado>> = datos.asStateFlow()
    override suspend fun agregar(resultado:ResultadoGuardado) { datos.update { (listOf(resultado)+it).distinctBy { r->r.id }.take(5) } }
}
