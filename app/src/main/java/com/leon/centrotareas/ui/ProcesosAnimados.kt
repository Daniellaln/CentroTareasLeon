package com.leon.centrotareas.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.leon.centrotareas.model.*

@Composable fun ProcesoAnimado(nombre:String,proceso:ProcesoVisible,detalle:String?=null) {
    val avance by animateFloatAsState(proceso.progreso.coerceIn(0f,1f),tween(120,easing=LinearOutSlowInEasing),label="proceso_$nombre")
    val tono=when(proceso.fase) {
        FaseProceso.Error -> MaterialTheme.colorScheme.error
        FaseProceso.Listo -> MaterialTheme.colorScheme.secondary
        FaseProceso.Esperando,FaseProceso.Cancelado -> MaterialTheme.colorScheme.onSurfaceVariant
        else -> MaterialTheme.colorScheme.primary
    }
    val tinta by animateColorAsState(tono,tween(160),label="estado_$nombre")
    val pista=MaterialTheme.colorScheme.outlineVariant
    Column(Modifier.fillMaxWidth().padding(vertical=8.dp),verticalArrangement=Arrangement.spacedBy(6.dp)) {
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween) {
            Text(nombre,style=MaterialTheme.typography.titleMedium,fontWeight=FontWeight.SemiBold,modifier=Modifier.weight(1f))
            Text(proceso.fase.titulo,color=tinta,style=MaterialTheme.typography.labelLarge)
        }
        Canvas(Modifier.fillMaxWidth().height(28.dp).semantics {
            contentDescription="$nombre, ${proceso.fase.titulo}"
            progressBarRangeInfo=ProgressBarRangeInfo(avance.coerceIn(0f,1f),0f..1f)
        }) {
            val inicio=12.dp.toPx();val fin=size.width-inicio;val y=size.height/2
            val x=inicio+(fin-inicio)*avance
            drawLine(pista,Offset(inicio,y),Offset(fin,y),4.dp.toPx(),StrokeCap.Round)
            if(avance>0)drawLine(tinta,Offset(inicio,y),Offset(x,y),4.dp.toPx(),StrokeCap.Round)
            drawCircle(tinta.copy(alpha=.15f),11.dp.toPx(),Offset(x,y));drawCircle(tinta,7.dp.toPx(),Offset(x,y))
            if(proceso.fase==FaseProceso.Listo) {
                val p=androidx.compose.ui.graphics.Path().apply { moveTo(x-3.dp.toPx(),y);lineTo(x-1.dp.toPx(),y+2.dp.toPx());lineTo(x+3.dp.toPx(),y-2.dp.toPx()) }
                drawPath(p,Color.White,style=androidx.compose.ui.graphics.drawscope.Stroke(1.5.dp.toPx(),cap=StrokeCap.Round,join=StrokeJoin.Round))
            }
        }
        Text(detalle ?: when(proceso.fase) {
            FaseProceso.Esperando -> "Aún no empieza"
            FaseProceso.Trabajando -> "${(proceso.progreso*100).toInt()} % · corrutina activa"
            FaseProceso.Listo -> "100 % · resultado disponible"
            FaseProceso.Error -> "El intento terminó con error"
            FaseProceso.Cancelado -> "Avance detenido"
        },style=MaterialTheme.typography.bodySmall,color=MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
