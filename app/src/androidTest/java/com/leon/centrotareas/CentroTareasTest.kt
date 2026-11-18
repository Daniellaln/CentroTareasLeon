package com.leon.centrotareas
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.ViewModelStore
import com.leon.centrotareas.data.*
import com.leon.centrotareas.model.*
import com.leon.centrotareas.viewmodel.TareasViewModel
import com.leon.centrotareas.ui.PantallaTareas
import com.leon.centrotareas.ui.theme.CentroTareasTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.ui.semantics.SemanticsProperties
import org.junit.*
import org.junit.Assert.*
class CentroTareasTest {
    @get:Rule val ui=createComposeRule()
    private val store=ViewModelStore()
    private fun mostrar(repo:TareasRepository=TareasRepositoryLocal()):TareasViewModel {
        val vm=TareasViewModel(repo);store.put("vm",vm)
        ui.setContent { CentroTareasTheme { PantallaTareas(vm) } };return vm
    }
    @After fun cerrar() { ui.runOnIdle { store.clear() } }
    @Test fun botonesInicialesTienenEstadosCorrectos() {
        mostrar();ui.onNodeWithText("Iniciar").assertIsEnabled();ui.onNodeWithText("Cancelar tarea").assertIsNotEnabled()
    }
    @Test fun progresoNoBloqueaToquesYSePuedeCancelarYReiniciar() {
        val vm=mostrar();ui.onNodeWithText("Iniciar").performClick()
        ui.waitUntil(5000) { vm.progreso.value>.05f }
        ui.onNodeWithText("Tócame: 0").performClick();ui.onNodeWithText("Tócame: 1").assertIsDisplayed()
        ui.onNodeWithText("Iniciar").assertIsNotEnabled();ui.onNodeWithText("Cancelar tarea").performClick()
        ui.waitUntil { !vm.corriendo.value };val detenido=vm.progreso.value
        ui.onNodeWithText("Cancelado").assertIsDisplayed();ui.onNodeWithText("Iniciar").performClick()
        ui.waitUntil { vm.corriendo.value };assertTrue(vm.progreso.value<=detenido)
    }
    @Test fun tareaCompletaLlegaACien() {
        val vm=mostrar();ui.onNodeWithText("Iniciar").performClick()
        ui.waitUntil(10000) { vm.mensaje.value=="Terminó" };assertEquals(1f,vm.progreso.value)
    }
    @Test fun primosDevuelven168YCancelacionFunciona() {
        val vm=mostrar();ui.runOnIdle { vm.contar(1000) }
        ui.waitUntil(10000) { vm.primos.value is PrimosUi.Listo };assertEquals(168,(vm.primos.value as PrimosUi.Listo).cantidad)
        ui.runOnIdle { vm.contar(Int.MAX_VALUE) };ui.waitUntil { vm.primos.value==PrimosUi.Calculando }
        ui.runOnIdle { vm.cancelarPrimos() };ui.waitUntil(5000) { vm.primos.value==PrimosUi.Cancelado }
    }
    @Test fun textoInvalidoNoLanzaCalculo() {
        val vm=mostrar();ui.onNodeWithText("Primos").performClick();ui.onNodeWithText("Hasta el número").performScrollTo().performTextReplacement("abc")
        ui.onNodeWithText("Contar").assertIsNotEnabled();assertEquals(PrimosUi.Inicial,vm.primos.value)
    }
    @Test fun cargasEnParaleloSuperponenSusEsperas() {
        var activas=0;var maximo=0
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            private suspend fun esperar() { activas++;maximo=maxOf(maximo,activas);delay(80);activas-- }
            override suspend fun cargarPerfil():String { esperar();return "Ana" }
            override suspend fun cargarPedidos(simularError:Boolean):List<String> { esperar();return listOf("101") }
        }
        val vm=mostrar(repo);ui.runOnIdle { vm.cargarALaVez() };ui.waitUntil(5000) { vm.carga.value is CargaUi.Exito }
        assertEquals(2,maximo);maximo=0
        ui.runOnIdle { vm.cargarUnaPorUna() };ui.waitUntil(5000) { vm.carga.value is CargaUi.Exito };assertEquals(1,maximo)
    }
    @Test fun errorYReintentoConservanLaAppOperativa() {
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            override suspend fun cargarPerfil():String { delay(20);return "Ana" }
            override suspend fun cargarPedidos(simularError:Boolean):List<String> { delay(40);if(simularError)throw java.io.IOException("Sin conexión");return listOf("101","102","103") }
        }
        val vm=mostrar(repo);ui.onNodeWithText("Cargas").performClick();ui.runOnIdle { vm.cambiarSimularError(true);vm.cargarALaVez() }
        ui.waitUntil(5000) { vm.carga.value is CargaUi.Error }
        ui.onNodeWithText("Reintentar").performScrollTo().assertIsDisplayed()
        ui.runOnIdle { vm.cambiarSimularError(false) };ui.onNodeWithText("Reintentar").performClick()
        ui.waitUntil(5000) { vm.carga.value is CargaUi.Exito };assertEquals(3,(vm.carga.value as CargaUi.Exito).pedidos)
    }
    @Test fun cancelarCargaNoSeConvierteEnError() {
        val vm=mostrar();ui.runOnIdle { vm.cargarALaVez() };ui.waitUntil { vm.carga.value==CargaUi.Cargando }
        ui.runOnIdle { vm.cancelarCarga() };ui.waitUntil(5000) { vm.carga.value==CargaUi.Inicial }
    }

    @Test fun limiteDeDosSegundosPuedeDesactivarseParaRecuperarLaCarga() {
        val vm=mostrar();ui.runOnIdle { vm.cambiarLimite(true);vm.cargarALaVez() }
        ui.waitUntil(6000) { vm.carga.value is CargaUi.Error }
        assertEquals("Tardó demasiado",(vm.carga.value as CargaUi.Error).mensaje)
        ui.runOnIdle { vm.cambiarLimite(false);vm.cargarALaVez() }
        ui.waitUntil(7000) { vm.carga.value is CargaUi.Exito }
    }
    @Test fun reintentosSeAcotanYCancelarNoSeConfundeConError() {
        var pedidos=0
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            override suspend fun cargarPerfil():String { delay(10);return "Ana" }
            override suspend fun cargarPedidos(simularError:Boolean):List<String> { pedidos++;delay(20);throw java.io.IOException("Sin conexión") }
        }
        val vm=mostrar(repo);ui.runOnIdle { vm.cambiarReintento(true);vm.cargarALaVez() }
        ui.waitUntil(10000) { vm.carga.value is CargaUi.Error };assertEquals(4,pedidos)
        ui.runOnIdle { vm.cargarALaVez() };ui.waitUntil { vm.intento.value==1 }
        ui.runOnIdle { vm.cancelarCarga() };ui.waitUntil { vm.carga.value==CargaUi.Inicial }
    }
    @Test fun datastoreRealRecuperaSoloLosCincoUltimosResultados() {
        val c=androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
        val scope=CoroutineScope(SupervisorJob()+Dispatchers.Main)
        var datos:List<ResultadoGuardado>?=null
        mostrar()
        ui.runOnIdle { scope.launch {
            val repo=HistorialDataStore(c)
            for(i in 1..7)repo.agregar(ResultadoGuardado("prueba$i","Primos","$i",i.toLong(),i.toLong()))
            datos=HistorialDataStore(c).resultados.first()
        } }
        ui.waitUntil(10000) { datos!=null };scope.cancel()
        assertEquals(listOf("prueba7","prueba6","prueba5","prueba4","prueba3"),datos!!.map { it.id })
    }
    @Test fun comparacionSoloAgregaCargasExitosas() {
        val vm=mostrar();ui.runOnIdle { vm.cargarALaVez() };ui.waitUntil(6000) { vm.carga.value is CargaUi.Exito }
        assertEquals(1,vm.mediciones.value.size)
        ui.runOnIdle { vm.cambiarLimite(true);vm.cargarUnaPorUna() };ui.waitUntil(6000) { vm.carga.value is CargaUi.Error }
        assertEquals(1,vm.mediciones.value.size)
    }
    @Test fun reintentoNoHeredaElMarcadorAnteriorYMantieneAnimacion() {
        val fallar=CompletableDeferred<Unit>();val nuevo=MutableStateFlow(.01f)
        var perfiles=0;var pedidos=0
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            override suspend fun cargarPerfil():String=error("Usa el Flow")
            override suspend fun cargarPedidos(simularError:Boolean):List<String> = error("Usa el Flow")
            override fun progresoPerfil()=flow<AvanceCarga<String>> {
                if(++perfiles==1) { emit(AvanceCarga(.8f));awaitCancellation() }
                else nuevo.collect { emit(AvanceCarga(it)) }
            }
            override fun progresoPedidos(simularError:Boolean)=flow<AvanceCarga<List<String>>> {
                if(++pedidos==1) { emit(AvanceCarga(.8f));fallar.await();throw java.io.IOException("Primer intento") }
                else nuevo.collect { emit(AvanceCarga(it)) }
            }
        }
        val vm=mostrar(repo);ui.onNodeWithText("Cargas").performClick()
        ui.runOnIdle { vm.cambiarReintento(true);vm.cargarALaVez() }
        ui.waitUntil { vm.perfilVisible.value.progreso==.8f && vm.pedidosVisible.value.progreso==.8f }
        ui.waitForIdle()
        fun pintado()=ui.onNodeWithContentDescription("Corrutina Perfil, En curso").fetchSemanticsNode().config[SemanticsProperties.ProgressBarRangeInfo].current
        assertEquals(.8f,pintado(),.001f)
        ui.mainClock.autoAdvance=false
        try {
            ui.runOnIdle { fallar.complete(Unit) }
            ui.waitUntil(5000) { vm.intento.value==2 && vm.perfilVisible.value.progreso==.01f }
            ui.mainClock.advanceTimeByFrame()
            assertTrue("El intento nuevo arrastra el anterior",pintado()<=.02f)
            ui.runOnIdle { nuevo.value=.5f }
            ui.waitUntil { vm.perfilVisible.value.progreso==.5f }
            ui.mainClock.advanceTimeByFrame();ui.mainClock.advanceTimeBy(48)
            val intermedio=pintado();assertTrue(intermedio>.01f && intermedio<.5f)
        } finally { ui.mainClock.autoAdvance=true;ui.runOnIdle { vm.cancelarCarga() } }
    }
    @Test fun procesosSecuencialesEsperanElResultadoAnterior() {
        val perfil=CompletableDeferred<Unit>();val pedidos=CompletableDeferred<Unit>()
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            override suspend fun cargarPerfil():String { perfil.await();return "Ana" }
            override suspend fun cargarPedidos(simularError:Boolean):List<String> { pedidos.await();return listOf("101") }
        }
        val vm=mostrar(repo);ui.runOnIdle { vm.cargarUnaPorUna() }
        ui.waitUntil { vm.perfilVisible.value.fase==FaseProceso.Trabajando }
        assertEquals(FaseProceso.Esperando,vm.pedidosVisible.value.fase)
        ui.runOnIdle { perfil.complete(Unit) }
        ui.waitUntil { vm.pedidosVisible.value.fase==FaseProceso.Trabajando }
        assertEquals(FaseProceso.Listo,vm.perfilVisible.value.fase)
        ui.runOnIdle { pedidos.complete(Unit) };ui.waitUntil { vm.carga.value is CargaUi.Exito }
        assertEquals(FaseProceso.Listo,vm.pedidosVisible.value.fase)
    }
    @Test fun procesosParalelosSeSuperponenYAmbosSeCancelan() {
        val repo=object:TareasRepository {
            override suspend fun contarPrimos(hasta:Int)=0
            override suspend fun cargarPerfil():String { awaitCancellation() }
            override suspend fun cargarPedidos(simularError:Boolean):List<String> { awaitCancellation() }
        }
        val vm=mostrar(repo);ui.runOnIdle { vm.cargarALaVez() }
        ui.waitUntil { vm.perfilVisible.value.fase==FaseProceso.Trabajando && vm.pedidosVisible.value.fase==FaseProceso.Trabajando }
        ui.runOnIdle { vm.cancelarCarga() };ui.waitUntil { vm.carga.value==CargaUi.Inicial }
        assertEquals(FaseProceso.Cancelado,vm.perfilVisible.value.fase);assertEquals(FaseProceso.Cancelado,vm.pedidosVisible.value.fase)
    }
    @Test fun cambiarDeTareaNoReiniciaNiCancelaElTrabajoActivo() {
        val vm=mostrar();ui.onNodeWithText("Iniciar").performClick()
        ui.onNodeWithText("Primos").performClick();ui.onNodeWithText("Hasta el número").assertIsDisplayed()
        ui.onNodeWithText("Cargas").performClick();ui.onNodeWithText("Dos cargas").assertIsDisplayed()
        ui.onNodeWithText("Progreso").performClick()
        assertTrue(vm.corriendo.value)
        ui.onNodeWithText("Cancelar tarea").performClick();ui.waitUntil { !vm.corriendo.value }
        assertEquals("Cancelado",vm.mensaje.value)
    }
    @Test fun opcionesSonControlesUnicosConNombreEstadoYAccion() {
        val vm=mostrar();ui.onNodeWithText("Cargas").performClick()
        ui.onNodeWithText("Pruebas de carga").performScrollTo().performClick()
        for(nombre in listOf("Simular error","Límite de 2 s","Reintento automático")) {
            ui.onNodeWithText(nombre).performScrollTo().assertIsToggleable().assertIsOff().assertHasClickAction().performClick().assertIsOn()
        }
        assertTrue(vm.simularError.value);assertTrue(vm.limite.value);assertTrue(vm.reintentar.value)
        ui.runOnIdle { vm.cargarALaVez() }
        ui.onNodeWithText("Simular error").assertIsNotEnabled()
    }
    @Test fun medidorNoCuentaElTiempoEnSegundoPlano() {
        val vm=mostrar();ui.runOnIdle {
            vm.tareaLarga();vm.registrarFotograma(1_000_000_000);vm.registrarFotograma(1_020_000_000)
            assertEquals(20f,vm.mayorPausa.value)
            vm.reiniciarFotogramas();vm.registrarFotograma(8_000_000_000)
            assertEquals(0f,vm.mayorPausa.value)
            vm.registrarFotograma(8_016_000_000);assertEquals(16f,vm.mayorPausa.value)
        }
    }
}
