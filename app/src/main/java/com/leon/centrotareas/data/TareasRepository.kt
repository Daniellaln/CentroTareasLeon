package com.leon.centrotareas.data
import kotlinx.coroutines.flow.flowOn
interface TareasRepository {
    suspend fun contarPrimos(hasta:Int):Int
    suspend fun cargarPerfil():String
    suspend fun cargarPedidos(simularError:Boolean):List<String>
    fun progresoPerfil():kotlinx.coroutines.flow.Flow<com.leon.centrotareas.model.AvanceCarga<String>> = kotlinx.coroutines.flow.flow {
        emit(com.leon.centrotareas.model.AvanceCarga(0f));emit(com.leon.centrotareas.model.AvanceCarga(1f,cargarPerfil()))
    }
    fun progresoPedidos(simularError:Boolean):kotlinx.coroutines.flow.Flow<com.leon.centrotareas.model.AvanceCarga<List<String>>> = kotlinx.coroutines.flow.flow {
        emit(com.leon.centrotareas.model.AvanceCarga(0f));emit(com.leon.centrotareas.model.AvanceCarga(1f,cargarPedidos(simularError)))
    }
    fun progresoTarea():kotlinx.coroutines.flow.Flow<Float> = kotlinx.coroutines.flow.flow {
        for(i in 1..100) { kotlinx.coroutines.delay(40);emit(i/100f) }
    }.flowOn(kotlinx.coroutines.Dispatchers.Default)

    fun progresoPrimos(hasta:Int):kotlinx.coroutines.flow.Flow<com.leon.centrotareas.model.AvancePrimos> = kotlinx.coroutines.flow.flow {
        emit(com.leon.centrotareas.model.AvancePrimos(0f,0))
        emit(com.leon.centrotareas.model.AvancePrimos(1f,contarPrimos(hasta),hasta))
    }

}
