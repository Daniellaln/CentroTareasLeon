# CentroTareas

<!-- impeccable:product-schema 1 -->

## Platform

android

## Users

Daniella y su docente de Programación en Móviles. Usan la app para demostrar tareas largas, cancelación y diferencias de ejecución durante una exposición.

## Product Purpose

Entender por qué el trabajo no debe bloquear la interfaz. El estudiante debe poder iniciar una tarea, seguir tocando la pantalla, observar avance, cancelar y comparar resultados medidos.

## Capabilities and Constraints

Kotlin, Compose Material3 y MVVM con Repository. Paquete com.leon.centrotareas. Datos de perfil y pedidos ficticios, sin red, Firebase, permisos ni claves. main conserva la fase1; mejora-ia incorpora las seis mejoras de la guía: Flow, timeout, reintentos, progreso de primos, DataStore y comparación de tiempos.

## Operating Context

Teléfono Android y emulador en clase. Medidas reales de tiempo dependen del equipo. La base bloqueante solo existe en el historial; el código final evita GlobalScope, runBlocking y Thread.sleep. La UI observa el ViewModel, que solo conoce interfaces de Repository.

## Brand Commitments

Confirmado por Daniella: azul y vidrio claro, fondo blanco frío, una tarea visible a la vez e historial aparte. Interfaz breve, fácil de usar, controles grandes; la presentación no debe entorpecer la demostración.

## Evidence on Hand

Guía GLAB-S14, capturas reales y pruebas de fase1 en docs. La implementación se hace con asistencia de Codex. Los prompts para Gemini son de reproducción; no se inventan respuestas de sesiones no realizadas.

## Product Principles

Mediciones honestas, cancelación visible, estados recuperables y opciones reveladas cuando se necesitan. Conservar la base y publicar avances completos y comprobados.
