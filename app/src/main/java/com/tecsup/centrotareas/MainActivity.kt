package com.tecsup.centrotareas
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tecsup.centrotareas.ui.PantallaTareas
import com.tecsup.centrotareas.ui.theme.CentroTareasTheme
import com.tecsup.centrotareas.viewmodel.*
class MainActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(statusBarStyle=SystemBarStyle.dark(android.graphics.Color.TRANSPARENT))
        setContent { CentroTareasTheme { val vm:TareasViewModel=viewModel(factory=TareasViewModelFactory());PantallaTareas(vm) } }
    }
}
