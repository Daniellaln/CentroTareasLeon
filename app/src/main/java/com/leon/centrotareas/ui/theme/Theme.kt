package com.leon.centrotareas.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
private val Claro=lightColorScheme(primary=Color(0xFF195DAA),onPrimary=Color.White,primaryContainer=Color(0xFFD9E9FF),onPrimaryContainer=Color(0xFF123D70),secondary=Color(0xFF14748B),secondaryContainer=Color(0xFFD5EFF4),onSecondaryContainer=Color(0xFF064A5A),background=Color(0xFFF4F8FE),onBackground=Color(0xFF132A44),surface=Color(0xFFFCFDFF),onSurface=Color(0xFF132A44),surfaceVariant=Color(0xFFE8EFF8),onSurfaceVariant=Color(0xFF475F7A),outline=Color(0xFF6C819A),outlineVariant=Color(0xFFD3DEEB),error=Color(0xFFB32635),errorContainer=Color(0xFFFFE4E8),onErrorContainer=Color(0xFF7D1728))
private val Oscuro=darkColorScheme(primary=Color(0xFFAACFFF),onPrimary=Color(0xFF00315E),primaryContainer=Color(0xFF164778),onPrimaryContainer=Color(0xFFD9E9FF),secondary=Color(0xFF8AD3E2),secondaryContainer=Color(0xFF0C4553),onSecondaryContainer=Color(0xFFBAEDF7),background=Color(0xFF091324),onBackground=Color(0xFFE3EDFA),surface=Color(0xFF101F34),onSurface=Color(0xFFE3EDFA),surfaceVariant=Color(0xFF243850),onSurfaceVariant=Color(0xFFB5C8E0),outline=Color(0xFF8BA3C0),outlineVariant=Color(0xFF3B526F),error=Color(0xFFFFB1BE),errorContainer=Color(0xFF632536),onErrorContainer=Color(0xFFFFD9E2))
@Composable fun CentroTareasTheme(content:@Composable ()->Unit) {
    val colores=if(isSystemInDarkTheme())Oscuro else Claro
    MaterialTheme(colorScheme=colores,typography=Typography()) { CompositionLocalProvider(LocalContentColor provides colores.onSurface,content=content) }
}
