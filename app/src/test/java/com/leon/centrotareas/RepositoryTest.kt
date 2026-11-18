package com.leon.centrotareas
import com.leon.centrotareas.data.TareasRepositoryLocal
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.Test
import org.junit.Assert.*
@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {
    @Test fun valoresDeReferencia()=runTest {
        val repo=TareasRepositoryLocal()
        for((n,cantidad) in listOf(100 to 25,1000 to 168,10000 to 1229,100000 to 9592,500000 to 41538)) assertEquals(cantidad,repo.contarPrimos(n))
    }
    @Test fun limiteInferiorNoCuentaPrimos()=runTest {
        val repo=TareasRepositoryLocal();assertEquals(0,repo.contarPrimos(1));assertEquals(1,repo.contarPrimos(2))
    }
    @Test fun cargasUsanLasDuracionesYDatosDeLaGuia()=runTest {
        val repo=TareasRepositoryLocal();val inicio=testScheduler.currentTime
        assertEquals("Ana Torres (ficticia)",repo.cargarPerfil());assertEquals(2000,testScheduler.currentTime-inicio)
        assertEquals(listOf("Pedido 101","Pedido 102","Pedido 103"),repo.cargarPedidos(false));assertEquals(5000,testScheduler.currentTime-inicio)
    }
    @Test fun errorSimuladoEsIOException()=runTest {
        try { TareasRepositoryLocal().cargarPedidos(true);fail("Se esperaba IOException") } catch(e:java.io.IOException){assertEquals("Sin conexión",e.message)}
    }
    @Test fun cancelarCalculoPesadoInterrumpeElRepository()=runTest {
        val iniciado=CompletableDeferred<Unit>()
        val trabajo=launch(Dispatchers.Default) { iniciado.complete(Unit);TareasRepositoryLocal().contarPrimos(Int.MAX_VALUE);fail("No debe terminar el cálculo") }
        iniciado.await();trabajo.cancelAndJoin();assertTrue(trabajo.isCancelled)
    }
}
