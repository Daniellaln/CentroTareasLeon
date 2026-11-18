package com.leon.centrotareas.data
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import android.util.Log
import java.io.IOException
class TareasRepositoryLocal:TareasRepository {
    override suspend fun cargarPerfil():String { delay(2000);return "Ana Torres (ficticia)" }
    override suspend fun cargarPedidos(simularError:Boolean):List<String> {
        delay(3000)
        if(simularError) throw IOException("Sin conexión")
        return listOf("Pedido 101","Pedido 102","Pedido 103")
    }
    override suspend fun contarPrimos(hasta:Int):Int=withContext(Dispatchers.Default) {
        var cantidad=0
        for(n in 2..hasta) { ensureActive();if(esPrimo(n))cantidad++ }
        cantidad
    }
    override fun progresoTarea():Flow<Float> = flow {
        Log.d("HILO","Dentro: ${Thread.currentThread().name}")
        for(i in 1..100) { delay(40);emit(i/100f) }
    }.flowOn(Dispatchers.Default)
    override fun progresoPrimos(hasta:Int):Flow<com.leon.centrotareas.model.AvancePrimos> = flow {
        require(hasta>=2)
        var cantidad=0;var ultimo=-1
        emit(com.leon.centrotareas.model.AvancePrimos(0f,0))
        for(n in 2..hasta) {
            currentCoroutineContext().ensureActive()
            if(esPrimo(n))cantidad++
            val porcentaje=(n.toLong()*100/hasta).toInt()
            if(porcentaje>ultimo) { emit(com.leon.centrotareas.model.AvancePrimos(n.toFloat()/hasta,cantidad));ultimo=porcentaje }
        }
    }.flowOn(Dispatchers.Default).conflate()
    private fun esPrimo(n:Int):Boolean {
        var d=2
        while(d.toLong()*d<=n) { if(n%d==0)return false;d++ }
        return n>=2
    }
}
