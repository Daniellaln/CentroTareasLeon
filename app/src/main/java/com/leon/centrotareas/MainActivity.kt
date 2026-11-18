package com.leon.centrotareas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leon.centrotareas.ui.PantallaTareas
import com.leon.centrotareas.ui.theme.CentroTareasTheme
import com.leon.centrotareas.viewmodel.*
class MainActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle=SystemBarStyle.dark(android.graphics.Color.TRANSPARENT))
        setContent { CentroTareasTheme { val vm:TareasViewModel=viewModel(factory=TareasViewModelFactory());PantallaTareas(vm) } }
    }
}
