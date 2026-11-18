package com.leon.centrotareas.ui

import android.graphics.Paint
import android.graphics.RuntimeShader
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
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

val LocalImagenCristal=staticCompositionLocalOf<Bitmap?> { null }
val LocalVisibilidadArte=staticCompositionLocalOf { 1f }

// La lente y el fondo evalúan la misma superficie procedural en coordenadas globales.
private const val MATERIAL="""
uniform float2 origin;
uniform float2 size;
uniform float radius;
uniform float density;
uniform float night;
uniform float lens;
uniform float accent;
uniform float pressure;
uniform float artAmount;
uniform float viewportWidth;
uniform float2 artSize;
uniform shader artTexture;
half3 background(float2 p) {
    float2 q=p/density;
    float blue=exp(-dot(q-float2(360,230),q-float2(360,230))/68000.0);
    float cyan=exp(-dot(q-float2(-100,700),q-float2(-100,700))/54000.0);
    half3 base=mix(half3(.957,.973,.996),half3(.035,.065,.12),half(night));
    base=mix(base,mix(half3(.71,.84,.99),half3(.07,.16,.3),half(night)),half(blue*.8));
    base=mix(base,mix(half3(.78,.94,.98),half3(.04,.20,.25),half(night)),half(cyan*.6));
    float top=260.0*density;
    float artWidth=viewportWidth*.82;
    float left=viewportWidth-artWidth;
    float height=artWidth*artSize.y/artSize.x;
    if(viewportWidth/density<700.0 && p.y>top && p.y<top+height) {
        float vertical=(p.y-top)/height;
        float opacity=smoothstep(.35,.6,p.x/viewportWidth)*(1.0-smoothstep(.78,1.0,vertical))*smoothstep(0.0,.12,vertical);
        half3 art=artTexture.eval((p-float2(left,top))*artSize.x/artWidth).rgb;
        base=mix(base,art,half(opacity*artAmount*mix(.95,.13,night)));
    }
    return base;
}
half4 main(float2 p) {
    if(lens<.5)return half4(background(origin+p),1);
    float2 c=p-size*.5;
    float2 q=abs(c)-(size*.5-radius);
    float d=length(max(q,0.0))+min(max(q.x,q.y),0.0)-radius;
    float2 n=normalize(max(q,0.0)*sign(c)+float2(.0001));
    float edge=1.0-smoothstep(0.0,14.0*density,-d);
    float2 sample=origin+p-n*edge*edge*(9.0+4.0*pressure)*density;
    half3 color=background(sample);
    color=mix(color,mix(half3(.99,1,1),half3(.05,.1,.2),half(night)),half(.20));
    color=mix(color,half3(.055,.24,.50),half(accent*.88));
    float spec=(1.0-smoothstep(0.0,density*1.5,abs(d+density)))*(.2+.7*max(dot(n,normalize(float2(-1,-1.3))),0.0));
    color=min(color+half3(spec*.6),half3(1));
    return half4(color,1);
}
"""
@RequiresApi(33)
private class MaterialRuntime(bitmap:Bitmap) {
    val shader=RuntimeShader(MATERIAL)
    init { shader.setInputShader("artTexture",BitmapShader(bitmap,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)) }
    val paint=Paint(Paint.ANTI_ALIAS_FLAG).apply { shader=this@MaterialRuntime.shader }
}
@Composable fun SuperficieVidrio(modifier:Modifier=Modifier,fondo:Boolean=false,ilustrada:Boolean=true,acentuada:Boolean=false,presion:Float=0f,content:@Composable BoxScope.()->Unit={}) {
    val oscuro=MaterialTheme.colorScheme.background.luminance()<.5f
    val imagen=LocalImagenCristal.current
    val visibilidadArte=LocalVisibilidadArte.current
    val bitmap=remember(imagen) { imagen?.asImageBitmap() }
    val material=if(Build.VERSION.SDK_INT>=33 && imagen!=null)remember(imagen) { runCatching { MaterialRuntime(imagen) }.onFailure { android.util.Log.e("CRISTAL","No se pudo preparar el material",it) }.getOrNull() } else null
    var origen by remember { mutableStateOf(Offset.Zero) }
    var anchoRaiz by remember { mutableFloatStateOf(1f) }
    Box(modifier.graphicsLayer { if(!fondo) { shape=RoundedCornerShape(30.dp);shadowElevation=6.dp.toPx();ambientShadowColor=Color(0x201C426E);spotShadowColor=Color(0x301C426E) } }) {
        Canvas(Modifier.matchParentSize().onGloballyPositioned { origen=it.positionInRoot();anchoRaiz=it.findRootCoordinates().size.width.toFloat().coerceAtLeast(1f) }) {
            if(Build.VERSION.SDK_INT>=33 && material!=null) {
                material.shader.apply {
                    setFloatUniform("origin",origen.x,origen.y);setFloatUniform("size",size.width,size.height)
                    setFloatUniform("radius",minOf(30.dp.toPx(),size.minDimension/2));setFloatUniform("density",density)
                    setFloatUniform("night",if(oscuro)1f else 0f);setFloatUniform("lens",if(fondo)0f else 1f)
                    setFloatUniform("accent",if(acentuada)1f else 0f);setFloatUniform("artAmount",if(ilustrada)visibilidadArte else 0f)
                    setFloatUniform("pressure",presion.coerceIn(0f,1f))
                    setFloatUniform("viewportWidth",anchoRaiz);setFloatUniform("artSize",imagen!!.width.toFloat(),imagen.height.toFloat())
                }
                if(fondo)drawContext.canvas.nativeCanvas.drawRect(0f,0f,size.width,size.height,material.paint)
                else drawContext.canvas.nativeCanvas.drawRoundRect(0f,0f,size.width,size.height,30.dp.toPx(),30.dp.toPx(),material.paint)
            } else {
                val colores=if(acentuada)listOf(Color(0xFF195DAA),Color(0xFF104582)) else if(oscuro)listOf(Color(0xFF182943),Color(0xFF0C182A)) else listOf(Color(0xFFF5FAFF),Color(0xFFDCEBFB))
                drawRoundRect(Brush.linearGradient(colores),cornerRadius=CornerRadius(if(fondo)0f else 30.dp.toPx()))
                if(fondo && ilustrada && bitmap!=null && size.width/density<700)drawImage(bitmap,dstOffset=IntOffset((size.width*.18f).toInt(),260.dp.roundToPx()),dstSize=IntSize((size.width*.82f).toInt(),(size.width*.82f*bitmap.height/bitmap.width).toInt()),alpha=(if(oscuro).13f else .8f)*visibilidadArte)
            }
            if(!fondo)drawRoundRect(Brush.linearGradient(listOf(Color.White.copy(alpha=if(oscuro).35f else .95f),Color.White.copy(alpha=.1f),Color.White.copy(alpha=.5f))),cornerRadius=CornerRadius(30.dp.toPx()),style=Stroke(1.dp.toPx()))
        }
        content()
    }
}
@Composable fun BotonVidrio(onClick:()->Unit,modifier:Modifier=Modifier,enabled:Boolean=true,acentuado:Boolean=false,content:@Composable RowScope.()->Unit) {
    val interaccion=remember { MutableInteractionSource() };val pulsado by interaccion.collectIsPressedAsState()
    val presion by animateFloatAsState(if(pulsado)1f else 0f,spring(.7f,600f),label="presion")
    SuperficieVidrio(modifier.graphicsLayer { scaleX=1f+.025f*presion;scaleY=1f-.05f*presion;alpha=if(enabled)1f else .45f },acentuada=acentuado,presion=presion) {
        Button(onClick,Modifier.fillMaxSize(),enabled=enabled,interactionSource=interaccion,contentPadding=PaddingValues(horizontal=16.dp),
            colors=ButtonDefaults.buttonColors(containerColor=Color.Transparent,contentColor=if(acentuado)Color.White else MaterialTheme.colorScheme.primary,disabledContainerColor=Color.Transparent,disabledContentColor=if(acentuado)Color.White else MaterialTheme.colorScheme.onSurfaceVariant),content=content)
    }
}
