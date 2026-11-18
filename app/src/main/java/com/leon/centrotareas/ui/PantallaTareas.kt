package com.leon.centrotareas.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leon.centrotareas.model.*
import com.leon.centrotareas.viewmodel.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PantallaTareas(vm:TareasViewModel) {
    val mensaje by vm.mensaje.collectAsStateWithLifecycle()
    val primos by vm.primos.collectAsStateWithLifecycle()
    val carga by vm.carga.collectAsStateWithLifecycle()
    var toques by rememberSaveable { mutableIntStateOf(0) }
    var numero by rememberSaveable { mutableStateOf("500000") }
    val progreso by vm.progreso.collectAsStateWithLifecycle()
    val corriendo by vm.corriendo.collectAsStateWithLifecycle()
    Scaffold(topBar={ TopAppBar(title={ Text("CentroTareas",fontWeight=FontWeight.Bold) },colors=TopAppBarDefaults.topAppBarColors(containerColor=MaterialTheme.colorScheme.primary,titleContentColor=MaterialTheme.colorScheme.onPrimary)) }) { margen ->
        Column(Modifier.fillMaxSize().padding(margen).padding(horizontal=16.dp).verticalScroll(rememberScrollState()),verticalArrangement=Arrangement.spacedBy(16.dp)) {
            Spacer(Modifier.height(0.dp))
            Seccion("Tarea con progreso") {
                Button(onClick={ toques++ },modifier=Modifier.align(Alignment.CenterHorizontally)) { Text("Tócame: $toques") }
                Row(horizontalArrangement=Arrangement.spacedBy(10.dp),modifier=Modifier.align(Alignment.CenterHorizontally)) {
                    Button(onClick=vm::tareaLarga,enabled=!corriendo) { Text("Iniciar") }
                    OutlinedButton(onClick=vm::cancelar,enabled=corriendo) { Text("Cancelar tarea") }
                }
                LinearProgressIndicator(progress={ progreso },modifier=Modifier.fillMaxWidth())
                Text("${(progreso*100).toInt()} %",Modifier.align(Alignment.CenterHorizontally))
                Text(mensaje,Modifier.align(Alignment.CenterHorizontally))
            }
            Seccion("Contar números primos") {
                OutlinedTextField(value=numero,onValueChange={ numero=it },label={ Text("Hasta el número") },singleLine=true,
                    keyboardOptions=KeyboardOptions(keyboardType=KeyboardType.Number),modifier=Modifier.fillMaxWidth())
                Row(horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                    Button(onClick={ numero.toIntOrNull()?.takeIf { it>=2 }?.let(vm::contar) },enabled=primos!=PrimosUi.Calculando && (numero.toIntOrNull() ?: 0)>=2) { Text("Contar") }
                    OutlinedButton(onClick=vm::cancelarPrimos,enabled=primos==PrimosUi.Calculando) { Text("Cancelar cálculo") }
                }
                when(val estado=primos) {
                    PrimosUi.Inicial -> Unit
                    PrimosUi.Calculando -> { CircularProgressIndicator(Modifier.size(24.dp));Text("Calculando…") }
                    PrimosUi.Cancelado -> Text("Cálculo cancelado")
                    is PrimosUi.Listo -> Resultado { Text("Primos: ${estado.cantidad}",fontWeight=FontWeight.Bold);Text("Tiempo: ${estado.milisegundos} ms") }
                }
            }
            Seccion("Dos cargas") {
                Row(horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(onClick={},enabled=false) { Text("Una por una") }
                    Button(onClick={},enabled=false) { Text("A la vez") }
                }
                when(val estado=carga) {
                    CargaUi.Inicial -> Unit
                    CargaUi.Cargando -> {
                        CircularProgressIndicator(Modifier.size(24.dp));Text("Cargando…")
                    }
                    is CargaUi.Exito -> Resultado { Text("Modo: ${estado.modo}",fontWeight=FontWeight.Bold);Text("Perfil: ${estado.perfil}");Text("Pedidos: ${estado.pedidos}");Text("Tiempo: ${estado.milisegundos} ms") }
                    is CargaUi.Error -> {
                        Column(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.errorContainer,MaterialTheme.shapes.medium).padding(12.dp)) {
                            Text(estado.mensaje,color=MaterialTheme.colorScheme.onErrorContainer)
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}
@Composable private fun Seccion(titulo:String,content:@Composable ColumnScope.()->Unit) {
    Card(Modifier.fillMaxWidth(),colors=CardDefaults.cardColors(containerColor=MaterialTheme.colorScheme.surface),border=androidx.compose.foundation.BorderStroke(1.dp,Color(0xFFE1E5EA))) {
        Column(Modifier.padding(16.dp),verticalArrangement=Arrangement.spacedBy(10.dp)) {
            Text(titulo,style=MaterialTheme.typography.titleMedium,color=MaterialTheme.colorScheme.primary,fontWeight=FontWeight.Bold)
            content()
        }
    }
}
@Composable private fun Resultado(content:@Composable ColumnScope.()->Unit) {
    Column(Modifier.fillMaxWidth().background(Color(0xFFE2F3E5),MaterialTheme.shapes.small).padding(12.dp)) {
        CompositionLocalProvider(LocalContentColor provides Color(0xFF245630)) { content() }
    }
}
