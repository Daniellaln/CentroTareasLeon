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
