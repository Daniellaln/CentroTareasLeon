package com.leon.centrotareas.data
import kotlinx.coroutines.flow.flowOn
interface TareasRepository {
    suspend fun contarPrimos(hasta:Int):Int
    suspend fun cargarPerfil():String
    suspend fun cargarPedidos(simularError:Boolean):List<String>
    fun progresoTarea():kotlinx.coroutines.flow.Flow<Float> = kotlinx.coroutines.flow.flow {
        for(i in 1..100) { kotlinx.coroutines.delay(40);emit(i/100f) }
    }.flowOn(kotlinx.coroutines.Dispatchers.Default)

}
