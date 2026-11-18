package com.leon.centrotareas.ui.theme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val Colores=lightColorScheme(primary=Color(0xFF176B97),onPrimary=Color.White,background=Color(0xFFFAFAFE),surface=Color.White,onSurface=Color(0xFF1F2930),onSurfaceVariant=Color(0xFF4C5963),error=Color(0xFFAA2934),errorContainer=Color(0xFFFFE3E6),onErrorContainer=Color(0xFF741B27))
@Composable fun CentroTareasTheme(content:@Composable ()->Unit) { MaterialTheme(colorScheme=Colores,content=content) }
