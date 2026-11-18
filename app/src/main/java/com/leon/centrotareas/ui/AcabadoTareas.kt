package com.leon.centrotareas.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.*

@Composable fun SelectorTareas(elegida:Int,elegir:(Int)->Unit) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val ancho=maxWidth/3
        val x by animateDpAsState(ancho*elegida,spring(.85f,500f),label="seleccion")
        val color=MaterialTheme.colorScheme.primaryContainer
        SuperficieVidrio(Modifier.fillMaxWidth()) {
            Canvas(Modifier.matchParentSize()) {
                val inset=4.dp.toPx()
                drawRoundRect(Brush.verticalGradient(listOf(color.copy(alpha=.95f),color.copy(alpha=.5f))),Offset(x.toPx()+inset,inset),Size(ancho.toPx()-2*inset,size.height-2*inset),CornerRadius(26.dp.toPx()))
                drawRoundRect(Color.White.copy(alpha=.55f),Offset(x.toPx()+inset,inset),Size(ancho.toPx()-2*inset,size.height-2*inset),CornerRadius(26.dp.toPx()),style=Stroke(1.dp.toPx()))
            }
            Row(Modifier.fillMaxWidth().selectableGroup()) {
                listOf("Progreso","Primos","Cargas").forEachIndexed { i,nombre ->
                    Box(Modifier.weight(1f).heightIn(min=56.dp).clip(RoundedCornerShape(28.dp)).selectable(selected=i==elegida,role=Role.Tab,onClick={ elegir(i) }).padding(horizontal=8.dp,vertical=12.dp),contentAlignment=Alignment.Center) {
                        Text(nombre,style=MaterialTheme.typography.labelLarge,fontWeight=if(i==elegida)FontWeight.Bold else FontWeight.Medium,color=if(i==elegida)MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    }
}

@Composable fun CabeceraTrabajo(titulo:String,detalle:String) {
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth(if(maxWidth<500.dp).5f else 1f).heightIn(min=if(maxWidth<500.dp)112.dp else 64.dp).padding(vertical=8.dp),verticalArrangement=Arrangement.spacedBy(10.dp)) {
            Text(titulo,style=MaterialTheme.typography.headlineMedium,fontWeight=FontWeight.Bold)
            Text(detalle,color=MaterialTheme.colorScheme.onSurfaceVariant,style=MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable fun DialProgreso(progreso:Float,modifier:Modifier=Modifier) {
    val avance by animateFloatAsState(progreso.coerceIn(0f,1f),tween(110,easing=LinearOutSlowInEasing),label="avance_real")
    val color=MaterialTheme.colorScheme.primary
    val pista=MaterialTheme.colorScheme.outlineVariant
    val claro=MaterialTheme.colorScheme.surface
    val escala=LocalDensity.current.fontScale.coerceAtLeast(1f)
    BoxWithConstraints(modifier.fillMaxWidth(),contentAlignment=Alignment.Center) {
        val compacto=maxWidth<200.dp
        val lado=minOf(maxWidth,if(compacto)140.dp else 180.dp*escala)
        Box(Modifier.size(lado).semantics(mergeDescendants=true) { progressBarRangeInfo=ProgressBarRangeInfo(progreso.coerceIn(0f,1f),0f..1f) },contentAlignment=Alignment.Center) {
            Canvas(Modifier.fillMaxSize()) {
                val centro=center;val radio=size.minDimension/2-8.dp.toPx()
                for(i in 0 until 100) {
                    val angulo=(i*3.6-90)*PI/180
                    val dentro=radio-5.dp.toPx();val fuera=radio
                    drawLine(if(i<avance*100)color else pista,Offset(centro.x+cos(angulo).toFloat()*dentro,centro.y+sin(angulo).toFloat()*dentro),Offset(centro.x+cos(angulo).toFloat()*fuera,centro.y+sin(angulo).toFloat()*fuera),2.dp.toPx(),StrokeCap.Round)
                }
                val margen=24.dp.toPx();val diametro=size.minDimension-2*margen
                drawCircle(Brush.radialGradient(listOf(claro.copy(alpha=.9f),claro.copy(alpha=.25f))),diametro/2,centro)
                drawArc(pista.copy(alpha=.5f),-90f,360f,false,Offset(margen,margen),Size(diametro,diametro),style=Stroke(6.dp.toPx(),cap=StrokeCap.Round))
                drawArc(Brush.sweepGradient(listOf(color.copy(alpha=.5f),color,color.copy(alpha=.7f))),-90f,360*avance,false,Offset(margen,margen),Size(diametro,diametro),style=Stroke(6.dp.toPx(),cap=StrokeCap.Round))
            }
            Text("${(progreso*100).toInt()} %",style=if(compacto)MaterialTheme.typography.headlineMedium else MaterialTheme.typography.displayMedium,color=color)
        }
    }
}
