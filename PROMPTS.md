# Prompts

Estos prompts se pueden utilizar uno por uno, siguiendo este orden, para replicar los resultados. Parte del cierre de fase1 en main y trabaja en mejora-ia.

La referencia se implementó con Codex. Los bloques son instrucciones de reproducción para Gemini en Android Studio; sus respuestas deben registrarse cuando se ejecuten, sin inventarlas. Los recursos y código de referencia están en esta misma repo.

## 1. Progreso desde Repository

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar progreso desde repository a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Amplía data/TareasRepository.kt con progresoTarea():Flow<Float>. Implementa el flujo en TareasRepositoryLocal usando flow, cien emisiones tras delay(40), i/100f y flowOn(Dispatchers.Default). El trabajo simulado sale del ViewModel. TareasViewModel.tareaLarga recoge el Flow y actualiza el StateFlow de lectura, conservando el Job, el bloqueo de doble inicio, cancelar y finally. El progreso debe detenerse donde estaba si cancelas y volver a cero al reiniciar. Mantén los logs HILO: main antes/después, worker dentro del flujo. La interfaz puede tener una implementación por defecto para repositorios de prueba, pero producción debe sobrescribirla. Comprueba la pantalla con Tócame mientras avanza y prueba cancelación, final100% y reinicio.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 1. Progreso desde Repository | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Progreso desde Repository conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora progreso desde repository. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.

## 2. Tiempo máximo

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar tiempo máximo a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Añade StateFlow<Boolean> limite en TareasViewModel, inicial false. En la sección de cargas añade un Switch Límite de2s. Captura la opción al empezar y envuelve el conjunto de cargas en withTimeout(2000). Maneja TimeoutCancellationException por separado con Error("Tardó demasiado"), antes de CancellationException. La cancelación manual se relanza y devuelve la carga a Inicial. El límite se puede desactivar: las cargas de2 y3s de la base deben seguir comparándose correctamente en modo normal. No cambies sus duraciones para fabricar éxito bajo un límite imposible. Comprueba ambos modos, timeout de2s y recuperación desactivando la opción.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 2. Tiempo máximo | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Tiempo máximo conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora tiempo máximo. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.

## 3. Reintento automático

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar reintento automático a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Añade reintentar e intento como StateFlow y un Switch Reintento automático, desactivado inicialmente. Si IOException falla una carga, reintenta como máximo tres veces adicionales: intento original más tres reintentos. Espera500ms,1000ms y2000ms entre intentos. Muestra el intento actual. Mantén un único Job: los botones se deshabilitan al cargar. Cada intento paralelo va dentro de coroutineScope para cancelar el hermano si falla. No reintentes CancellationException ni un timeout; propaga cancelación manual. Captura la configuración al iniciar para que cambiar un switch no altere silenciosamente una ejecución ya activa. Comprueba cuatro intentos en error, esperas crecientes y que Cancelar detenga también una espera de reintento.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 3. Reintento automático | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Reintento automático conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora reintento automático. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.

## 4. Primos con progreso

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar primos con progreso a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Crea model/AvancePrimos(progreso:Float,cantidad:Int). Añade progresoPrimos(hasta):Flow<AvancePrimos> al Repository. En producción usa flowOn(Default), ensureActive en cada número y esPrimo existente. Emite al inicio y al cambiar cada porcentaje entero, con último progreso1f y resultado exacto. Usa n.toLong()*100/hasta para evitar desbordamiento. Puedes conflate las emisiones: el resultado final no debe perderse. TareasViewModel.contar recoge el Flow, actualiza progresoPrimos y finalmente Listo(cantidad,ms). Mantén validación de hasta>=2 y Cancelado con cancelación relanzada. La pantalla debe mostrar barra y porcentaje del cálculo. No introduzcas delay para falsear duración. Verifica100→25,1000→168,10000→1229 y cancelación de un cálculo grande.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 4. Primos con progreso | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Primos con progreso conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora primos con progreso. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.
