package com.leon.centrotareas.ui

import android.graphics.Paint
import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.*

// La lente y el fondo evalúan la misma superficie procedural en coordenadas globales.
private const val MATERIAL="""
uniform float2 origin;
uniform float2 size;
uniform float radius;
uniform float density;
uniform float night;
uniform float lens;
half3 background(float2 p) {
    float2 q=p/density;
    float blue=exp(-dot(q-float2(360,230),q-float2(360,230))/68000.0);
    float cyan=exp(-dot(q-float2(-100,700),q-float2(-100,700))/54000.0);
    half3 base=mix(half3(.957,.973,.996),half3(.035,.065,.12),half(night));
    base=mix(base,mix(half3(.71,.84,.99),half3(.07,.16,.3),half(night)),half(blue*.8));
    return mix(base,mix(half3(.78,.94,.98),half3(.04,.20,.25),half(night)),half(cyan*.6));
}
half4 main(float2 p) {
    if(lens<.5)return half4(background(origin+p),1);
    float2 c=p-size*.5;
    float2 q=abs(c)-(size*.5-radius);
    float d=length(max(q,0.0))+min(max(q.x,q.y),0.0)-radius;
    float2 n=normalize(max(q,0.0)*sign(c)+float2(.0001));
    float edge=1.0-smoothstep(0.0,14.0*density,-d);
    float2 sample=origin+p-n*edge*edge*9.0*density;
    half3 color=background(sample);
    color=mix(color,mix(half3(.99,1,1),half3(.05,.1,.2),half(night)),half(.20));
    float spec=(1.0-smoothstep(0.0,density*1.5,abs(d+density)))*(.2+.7*max(dot(n,normalize(float2(-1,-1.3))),0.0));
    color=min(color+half3(spec*.6),half3(1));
    return half4(color,1);
}
"""
@RequiresApi(33)
private class MaterialRuntime {
    val shader=RuntimeShader(MATERIAL)
    val paint=Paint(Paint.ANTI_ALIAS_FLAG).apply { shader=this@MaterialRuntime.shader }
}
@Composable fun SuperficieVidrio(modifier:Modifier=Modifier,fondo:Boolean=false,content:@Composable BoxScope.()->Unit={}) {
    val oscuro=MaterialTheme.colorScheme.background.luminance()<.5f
    val material=if(Build.VERSION.SDK_INT>=33)remember { runCatching { MaterialRuntime() }.getOrNull() } else null
    var origen by remember { mutableStateOf(Offset.Zero) }
    Box(modifier.graphicsLayer { if(!fondo) { shape=RoundedCornerShape(30.dp);shadowElevation=6.dp.toPx();ambientShadowColor=Color(0x201C426E);spotShadowColor=Color(0x301C426E) } }) {
        Canvas(Modifier.matchParentSize().onGloballyPositioned { origen=it.positionInRoot() }) {
            if(Build.VERSION.SDK_INT>=33 && material!=null) {
                material.shader.apply {
                    setFloatUniform("origin",origen.x,origen.y);setFloatUniform("size",size.width,size.height)
                    setFloatUniform("radius",minOf(30.dp.toPx(),size.minDimension/2));setFloatUniform("density",density)
                    setFloatUniform("night",if(oscuro)1f else 0f);setFloatUniform("lens",if(fondo)0f else 1f)
                }
                if(fondo)drawContext.canvas.nativeCanvas.drawRect(0f,0f,size.width,size.height,material.paint)
                else drawContext.canvas.nativeCanvas.drawRoundRect(0f,0f,size.width,size.height,30.dp.toPx(),30.dp.toPx(),material.paint)
            } else {
                drawRoundRect(Brush.linearGradient(if(oscuro)listOf(Color(0xFF182943),Color(0xFF0C182A)) else listOf(Color(0xFFF5FAFF),Color(0xFFDCEBFB))),cornerRadius=CornerRadius(if(fondo)0f else 30.dp.toPx()))
            }
            if(!fondo)drawRoundRect(Brush.linearGradient(listOf(Color.White.copy(alpha=if(oscuro).35f else .95f),Color.White.copy(alpha=.1f),Color.White.copy(alpha=.5f))),cornerRadius=CornerRadius(30.dp.toPx()),style=Stroke(1.dp.toPx()))
        }
        content()
    }
}
@Composable fun BotonVidrio(onClick:()->Unit,modifier:Modifier=Modifier,enabled:Boolean=true,content:@Composable RowScope.()->Unit) {
    val interaccion=remember { MutableInteractionSource() };val pulsado by interaccion.collectIsPressedAsState()
    val escala by animateFloatAsState(if(pulsado).96f else 1f,spring(.8f,600f),label="presion")
    SuperficieVidrio(modifier.graphicsLayer { scaleX=escala;scaleY=escala;alpha=if(enabled)1f else .45f }) {
        Button(onClick,Modifier.fillMaxSize(),enabled=enabled,interactionSource=interaccion,contentPadding=PaddingValues(horizontal=16.dp),
            colors=ButtonDefaults.buttonColors(containerColor=Color.Transparent,contentColor=MaterialTheme.colorScheme.primary,disabledContainerColor=Color.Transparent),content=content)
    }
}
