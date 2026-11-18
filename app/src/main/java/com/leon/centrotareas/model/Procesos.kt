package com.leon.centrotareas.model

data class AvanceCarga<T>(val progreso:Float,val resultado:T?=null,val hilo:String="")
enum class FaseProceso(val titulo:String) {
    Esperando("En espera"),Trabajando("En curso"),Listo("Terminado"),Error("Falló"),Cancelado("Cancelado")
}
data class ProcesoVisible(val fase:FaseProceso=FaseProceso.Esperando,val progreso:Float=0f,val hilo:String="")
