package com.leon.centrotareas.ui
import android.view.Choreographer
import androidx.compose.runtime.*
import androidx.lifecycle.*
import androidx.lifecycle.compose.LocalLifecycleOwner
@Composable fun ObservarFotogramas(onFrame:(Long)->Unit,onReset:()->Unit) {
    val ciclo=LocalLifecycleOwner.current.lifecycle
    val frame by rememberUpdatedState(onFrame)
    val reset by rememberUpdatedState(onReset)
    DisposableEffect(ciclo) {
        val reloj=Choreographer.getInstance();var activo=false
        val callback=object:Choreographer.FrameCallback {
            override fun doFrame(nanos:Long) { if(activo) { frame(nanos);reloj.postFrameCallback(this) } }
        }
        fun parar() { activo=false;reloj.removeFrameCallback(callback);reset() }
        fun iniciar() { if(!activo) { activo=true;reset();reloj.postFrameCallback(callback) } }
        val observador=LifecycleEventObserver { _,evento -> when(evento) { Lifecycle.Event.ON_RESUME->iniciar();Lifecycle.Event.ON_PAUSE->parar();else->Unit } }
        ciclo.addObserver(observador)
        if(ciclo.currentState.isAtLeast(Lifecycle.State.RESUMED))iniciar()
        onDispose { parar();ciclo.removeObserver(observador) }
    }
}
