package com.leon.centrotareas
import com.leon.centrotareas.data.*
import com.leon.centrotareas.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.Test
import org.junit.Assert.*
class MejorasRepositoryTest {
    @Test fun primosEmitenResultadoExactoYProgresoMonotono()=runTest {
        val datos=TareasRepositoryLocal().progresoPrimos(10000).toList()
        assertEquals(0f,datos.first().progreso);assertEquals(1f,datos.last().progreso)
        assertEquals(1229,datos.last().cantidad)
        assertTrue(datos.zipWithNext().all { (a,b)->a.progreso<=b.progreso })
    }
    @Test fun historialConservaSoloCincoMasRecientesYNoDuplicaIds()=runTest {
        val repo=HistorialMemoria()
        for(i in 1..7)repo.agregar(ResultadoGuardado("$i","Primos","Resultado$i",i.toLong(),i.toLong()))
        assertEquals(listOf("7","6","5","4","3"),repo.resultados.first().map { it.id })
        repo.agregar(ResultadoGuardado("7","Primos","Actualizado",8,8))
        assertEquals(5,repo.resultados.first().size)
        assertEquals("Actualizado",repo.resultados.first().first().resumen)
    }
    @Test fun flujoDePrimosNoIgnoraLaCancelacion()=runTest {
        val inicio=CompletableDeferred<Unit>()
        val job=launch(Dispatchers.Default) { TareasRepositoryLocal().progresoPrimos(Int.MAX_VALUE).collect { inicio.complete(Unit) } }
        inicio.await();job.cancelAndJoin();assertTrue(job.isCancelled)
    }
}