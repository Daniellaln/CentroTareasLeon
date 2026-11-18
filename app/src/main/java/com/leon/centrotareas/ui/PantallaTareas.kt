package com.leon.centrotareas.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leon.centrotareas.R
import com.leon.centrotareas.model.*
import com.leon.centrotareas.viewmodel.TareasViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PantallaTareas(vm:TareasViewModel) {
    val locale=LocalConfiguration.current.locales[0]
    var tarea by rememberSaveable { mutableIntStateOf(0) }
    var historial by rememberSaveable { mutableStateOf(false) }
    var toques by rememberSaveable { mutableIntStateOf(0) }
    var numero by rememberSaveable { mutableStateOf("5000000") }
    val mensaje by vm.mensaje.collectAsStateWithLifecycle()
    val progreso by vm.progreso.collectAsStateWithLifecycle()
    val corriendo by vm.corriendo.collectAsStateWithLifecycle()
    val primos by vm.primos.collectAsStateWithLifecycle()
    val progresoPrimos by vm.progresoPrimos.collectAsStateWithLifecycle()
    val carga by vm.carga.collectAsStateWithLifecycle()
    val guardados by vm.historial.collectAsStateWithLifecycle()
    val aviso by vm.aviso.collectAsStateWithLifecycle()
    ObservarFotogramas(vm::registrarFotograma,vm::reiniciarFotogramas)
    BackHandler(historial) { historial=false }
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val ancho=maxWidth>=700.dp
        SuperficieVidrio(Modifier.fillMaxSize(),fondo=true)
        Column(Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding().imePadding().padding(horizontal=24.dp)) {
            Row(Modifier.fillMaxWidth().padding(top=12.dp,bottom=20.dp),verticalAlignment=Alignment.CenterVertically) {
                if(historial) IconButton(onClick={ historial=false }) { Icon(painterResource(R.drawable.ic_volver),"Volver a las tareas") }
                Text(if(historial)"Historial" else "CentroTareas",style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.Bold,modifier=Modifier.weight(1f))
                if(!historial) BotonVidrio({ historial=true },Modifier.size(56.dp)) { Icon(painterResource(R.drawable.ic_historial),"Abrir historial",Modifier.size(24.dp)) }
            }
            if(historial) {
                Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(18.dp)) {
                    Text("Tus últimos cinco resultados",style=MaterialTheme.typography.titleMedium,color=MaterialTheme.colorScheme.onSurfaceVariant)
                    if(guardados.isEmpty()) {
                        Icon(painterResource(R.drawable.ic_historial),null,Modifier.padding(top=40.dp).size(48.dp),tint=MaterialTheme.colorScheme.primary)
                        Text("Aún no hay resultados",style=MaterialTheme.typography.headlineSmall)
                        Text("Completa un cálculo o una carga para guardarlo aquí.",color=MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    guardados.forEach { item ->
                        Column(Modifier.fillMaxWidth().padding(vertical=10.dp),verticalArrangement=Arrangement.spacedBy(6.dp)) {
                            Text(item.tipo,style=MaterialTheme.typography.titleMedium,color=MaterialTheme.colorScheme.primary)
                            Text(item.resumen,style=MaterialTheme.typography.bodyLarge)
                            Text("${item.milisegundos} ms · ${SimpleDateFormat("dd/MM HH:mm",locale).format(Date(item.fecha))}",style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        HorizontalDivider(color=MaterialTheme.colorScheme.outlineVariant)
                    }
                }
            } else {
                SuperficieVidrio(Modifier.fillMaxWidth().heightIn(min=56.dp)) {
                    SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
                        listOf("Progreso","Primos","Cargas").forEachIndexed { i,texto ->
                            SegmentedButton(tarea==i,{ tarea=i },shape=SegmentedButtonDefaults.itemShape(i,3),
                                colors=SegmentedButtonDefaults.colors(activeContainerColor=MaterialTheme.colorScheme.primaryContainer.copy(alpha=.8f),inactiveContainerColor=Color.Transparent,activeContentColor=MaterialTheme.colorScheme.onPrimaryContainer,inactiveContentColor=MaterialTheme.colorScheme.onSurface,activeBorderColor=Color.Transparent,inactiveBorderColor=Color.Transparent),icon={}) { Text(texto) }
                        }
                    }
                }
                Spacer(Modifier.height(24.dp))
                val trabajo:@Composable ()->Unit={
                    when(tarea) {
                        0 -> {
                            Text("Tarea con progreso",style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
                            Text("100 pasos · aproximadamente 4 segundos",color=MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(Modifier.height(32.dp))
                            Text("${(progreso*100).toInt()} %",style=MaterialTheme.typography.displayMedium,color=MaterialTheme.colorScheme.primary)
                            LinearProgressIndicator(progress={ progreso },modifier=Modifier.fillMaxWidth().padding(vertical=16.dp).height(8.dp))
                            Text(mensaje,style=MaterialTheme.typography.titleMedium)
                            Spacer(Modifier.height(24.dp))
                            Acciones("Iniciar",!corriendo,vm::tareaLarga,"Cancelar tarea",corriendo,vm::cancelar)
                        }
                        1 -> {
                            Text("Contar números primos",style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
                            Spacer(Modifier.height(20.dp))
                            OutlinedTextField(numero,{ numero=it },label={ Text("Hasta el número") },singleLine=true,keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Number),modifier=Modifier.fillMaxWidth(),enabled=primos!=PrimosUi.Calculando)
                            Spacer(Modifier.height(16.dp))
                            Acciones("Contar",primos!=PrimosUi.Calculando && (numero.toIntOrNull() ?: 0)>=2,{ numero.toIntOrNull()?.takeIf { it>=2 }?.let(vm::contar) },"Cancelar cálculo",primos==PrimosUi.Calculando,vm::cancelarPrimos)
                            Spacer(Modifier.height(24.dp))
                            when(val estado=primos) {
                                PrimosUi.Inicial -> Text("El cálculo se ejecuta en segundo plano.",color=MaterialTheme.colorScheme.onSurfaceVariant)
                                PrimosUi.Calculando -> { LinearProgressIndicator(progress={ progresoPrimos },modifier=Modifier.fillMaxWidth());Text("Cálculo: ${(progresoPrimos*100).toInt()} %",Modifier.padding(top=12.dp)) }
                                PrimosUi.Cancelado -> Text("Cálculo cancelado",style=MaterialTheme.typography.titleMedium)
                                is PrimosUi.Listo -> Resultado("${estado.cantidad} primos","Tiempo: ${estado.milisegundos} ms")
                            }
                        }
                        else -> PanelCargas(vm,carga)
                    }
                }
                if(ancho) Row(Modifier.fillMaxWidth().weight(1f),horizontalArrangement=Arrangement.spacedBy(36.dp)) {
                    Column(Modifier.weight(1f).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(8.dp)) { trabajo();aviso?.let { Text(it,color=MaterialTheme.colorScheme.error) } }
                    Column(Modifier.width(300.dp).verticalScroll(rememberScrollState())) { Diagnostico(vm,toques) { toques++ } }
                } else Column(Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(10.dp)) {
                    trabajo();aviso?.let { Text(it,color=MaterialTheme.colorScheme.error) }
                    Spacer(Modifier.height(24.dp));HorizontalDivider(color=MaterialTheme.colorScheme.outlineVariant)
                    Diagnostico(vm,toques) { toques++ };Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable private fun Acciones(primario:String,puedeIniciar:Boolean,iniciar:()->Unit,secundario:String,puedeCancelar:Boolean,cancelar:()->Unit) {
    FlowRow(horizontalArrangement=Arrangement.spacedBy(12.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
        Button(iniciar,enabled=puedeIniciar,modifier=Modifier.heightIn(min=56.dp),contentPadding=PaddingValues(horizontal=28.dp)) { Text(primario) }
        OutlinedButton(cancelar,enabled=puedeCancelar,modifier=Modifier.heightIn(min=56.dp)) { Text(secundario) }
    }
}
@Composable private fun Resultado(titulo:String,detalle:String) {
    Column(Modifier.fillMaxWidth().padding(vertical=8.dp),verticalArrangement=Arrangement.spacedBy(6.dp)) {
        Icon(painterResource(R.drawable.ic_check),null,tint=MaterialTheme.colorScheme.primary,modifier=Modifier.size(28.dp))
        Text(titulo,style=MaterialTheme.typography.headlineSmall,fontWeight=FontWeight.SemiBold)
        Text(detalle,color=MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
@Composable private fun Diagnostico(vm:TareasViewModel,toques:Int,tocar:()->Unit) {
    val pausas by vm.pausas.collectAsStateWithLifecycle()
    val mayor by vm.mayorPausa.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxWidth().padding(top=18.dp),verticalArrangement=Arrangement.spacedBy(12.dp)) {
        Text("Comprueba la respuesta",style=MaterialTheme.typography.titleLarge,fontWeight=FontWeight.Bold)
        BotonVidrio(tocar,Modifier.fillMaxWidth().height(60.dp)) {
            Icon(painterResource(R.drawable.ic_tocar),null,Modifier.size(24.dp));Spacer(Modifier.width(12.dp));Text("Tócame: $toques",style=MaterialTheme.typography.titleMedium)
        }
        Text(if(mayor==0f)"Inicia una tarea para medir" else "Mayor pausa: ${mayor.toInt()} ms",style=MaterialTheme.typography.bodyMedium,color=MaterialTheme.colorScheme.onSurfaceVariant)
        val muestras=pausas
        if(muestras.size>1) {
            val tinta=MaterialTheme.colorScheme.primary;val guia=MaterialTheme.colorScheme.outlineVariant
            Canvas(Modifier.fillMaxWidth().height(70.dp).semantics { contentDescription="Intervalos entre fotogramas de la última ejecución, en milisegundos" }) {
                drawLine(guia,Offset(0f,size.height*.8f),Offset(size.width,size.height*.8f),1.dp.toPx())
                val maximo=muestras.maxOrNull()?.coerceAtLeast(33f) ?: 33f
                val camino=Path();muestras.forEachIndexed { i,v ->
                    val x=i*size.width/(muestras.size-1);val y=size.height*(.95f-.85f*v/maximo)
                    if(i==0)camino.moveTo(x,y) else camino.lineTo(x,y)
                }
                drawPath(camino,tinta,style=Stroke(2.dp.toPx()))
            }
            Text("Intervalos reales de dibujo · menor es mejor",style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
@Composable private fun Opcion(titulo:String,valor:Boolean,enabled:Boolean,cambiar:(Boolean)->Unit) {
    Row(Modifier.fillMaxWidth().heightIn(min=56.dp),verticalAlignment=Alignment.CenterVertically) {
        Text(titulo,Modifier.weight(1f));Switch(valor,cambiar,enabled=enabled)
    }
}
@Composable private fun PanelCargas(vm:TareasViewModel,carga:CargaUi) {
    val error by vm.simularError.collectAsStateWithLifecycle();val limite by vm.limite.collectAsStateWithLifecycle()
    val automatico by vm.reintentar.collectAsStateWithLifecycle();val intento by vm.intento.collectAsStateWithLifecycle()
    val mediciones by vm.mediciones.collectAsStateWithLifecycle()
    var opciones by rememberSaveable { mutableStateOf(false) }
    var tabla by rememberSaveable { mutableStateOf(false) }
    val ocupado=carga==CargaUi.Cargando
    Text("Dos cargas",style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
    Text("Perfil: 2 s · pedidos: 3 s",color=MaterialTheme.colorScheme.onSurfaceVariant)
    Spacer(Modifier.height(20.dp))
    Acciones("A la vez",!ocupado,vm::cargarALaVez,"Una por una",!ocupado,vm::cargarUnaPorUna)
    if(ocupado) {
        LinearProgressIndicator(Modifier.fillMaxWidth().padding(top=20.dp))
        Text("Cargando… · intento $intento de ${if(automatico)4 else 1}",Modifier.padding(top=10.dp))
        TextButton(vm::cancelarCarga,Modifier.heightIn(min=48.dp)) { Text("Cancelar carga") }
    }
    when(val estado=carga) {
        is CargaUi.Exito -> { Resultado("Modo: ${estado.modo}","Tiempo: ${estado.milisegundos} ms");Text("${estado.perfil} · ${estado.pedidos} pedidos",color=MaterialTheme.colorScheme.onSurfaceVariant) }
        is CargaUi.Error -> {
            Text(estado.mensaje,Modifier.padding(top=20.dp),color=MaterialTheme.colorScheme.error,style=MaterialTheme.typography.titleMedium)
            OutlinedButton(vm::cargarALaVez,Modifier.heightIn(min=56.dp)) { Text("Reintentar") }
        }
        else -> Unit
    }
    TextButton({ opciones=!opciones },Modifier.heightIn(min=56.dp)) { Icon(painterResource(R.drawable.ic_opciones),null,Modifier.size(20.dp));Spacer(Modifier.width(10.dp));Text(if(opciones)"Ocultar pruebas de carga" else "Pruebas de carga") }
    AnimatedVisibility(opciones) {
        Column {
            Opcion("Simular error",error,!ocupado,vm::cambiarSimularError)
            Opcion("Límite de 2 s",limite,!ocupado,vm::cambiarLimite)
            Opcion("Reintento automático",automatico,!ocupado,vm::cambiarReintento)
            Text("El límite se aplica a cada intento. Los tiempos de espera simulados son 2 y 3 segundos.",style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
    if(mediciones.isNotEmpty()) {
        TextButton({ tabla=!tabla },Modifier.heightIn(min=56.dp)) { Text(if(tabla)"Ocultar comparación" else "Comparar tiempos (${mediciones.size})") }
        AnimatedVisibility(tabla) {
            Column(verticalArrangement=Arrangement.spacedBy(10.dp)) {
                Text("Comparación de tiempos",style=MaterialTheme.typography.titleLarge)
                Row(Modifier.fillMaxWidth()) { Text("Modo",Modifier.weight(1f),fontWeight=FontWeight.Bold);Text("Tiempo",fontWeight=FontWeight.Bold) }
                mediciones.forEach { item ->
                    Row(Modifier.fillMaxWidth()) { Text("${item.numero} · ${item.modo}",Modifier.weight(1f));Text("${item.milisegundos} ms") }
                }
            }
        }
    }
}
