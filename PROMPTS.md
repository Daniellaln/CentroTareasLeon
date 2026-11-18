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

## 5. Historial persistente

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar historial persistente a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Añade androidx.datastore:datastore-preferences:1.3.0 y crea HistorialRepository con un Flow<List<ResultadoGuardado>> y suspend agregar(resultado). El modelo contiene id, tipo, resumen, milisegundos y fecha. Implementa HistorialDataStore mediante un delegado global único sobre applicationContext, archivo historial_tareas, almacenando JSON en una clave de preferencias. Usa edit atómico: inserta primero y toma los últimos cinco. Recupera IOException sin borrar otros datos; notifica fallo de escritura con un aviso de VM y conserva el resultado en pantalla. La Factory recibe applicationContext y lo entrega al Repository, nunca al ViewModel. Inyecta HistorialRepository en TareasViewModel con implementación en memoria por defecto para pruebas. Guarda solo resultados exitosos de primos y cargas. Mantén IDs únicos y no guardes cancelados ni errores como éxitos. Muestra los cinco al abrir y comprueba un cierre completo/reapertura con datos reales de DataStore.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 5. Historial persistente | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Historial persistente conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora historial persistente. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.

## 6. Comparación de tiempos

```text
Compórtate como un experto en Kotlin, corrutinas y arquitectura MVVM para Android. Ayúdame a incorporar comparación de tiempos a CentroTareasLeon, paquete com.leon.centrotareas, partiendo del avance anterior en mejora-ia. Conserva main, los modelos de estado, el resto de funciones y las tres tareas de la guía. El proyecto usa Compose Material3 y JDK21; no necesita Firebase, permisos ni Internet para simular cargas.

Añade MedicionCarga(numero,modo,milisegundos) y un StateFlow<List<MedicionCarga>> al ViewModel. Acumula únicamente las cargas exitosas de la sesión, hasta20 mediciones, con número consecutivo. El tiempo medido comprende la operación completa, incluidas esperas de reintento si se activaron. Presenta filas de ejecución, modo y milisegundos, mostrando Una por una y A la vez cuando existan ambas. No pongas5s/3s fijos como si fueran mediciones. Conserva las cargas simuladas de2 y3s y documenta que la comparación depende del equipo y configuración. Comprueba que alternar modos agrega filas y que cancelar/fallar no agrega una fila exitosa.

La UI solo observa el ViewModel y comunica acciones; no consulta repositorios ni lanza trabajos. El ViewModel depende de interfaces sin Context. Conserva la cancelación estructurada y evita GlobalScope, runBlocking y Thread.sleep en código de producción. Compila con gradlew.bat assembleDebug testDebugUnitTest (o ./gradlew en Mac) y comprueba este cambio antes de seguir al próximo prompt. Puedes consultar el código de este avance en el historial Git de la rama para reproducir firmas y etiquetas exactas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 6. Comparación de tiempos | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Comparación de tiempos conservando las interfaces y cancelación de la base. | Cumplir la mejora sin mover lógica de trabajo a Compose. |

Diferencia frente a la base: se incorpora comparación de tiempos. Reflexión: cada estado visible debe corresponder a trabajo real. Comprobar: el caso de éxito, cancelación y recuperación descritos arriba.

## 7. Medidor de respuesta propio

```text
Compórtate como un experto en rendimiento Android y MVVM. Ayúdame a añadir un medidor didáctico de respuesta a CentroTareas, com.leon.centrotareas. Conserva las seis mejoras y la cancelación. Crea ui/ObservarFotogramas.kt con DisposableEffect y Choreographer.FrameCallback; se registra solo al RESUMED, retira el callback al PAUSE o dispose y reinicia el tiempo anterior al reanudar. No uses coroutines ni bucles de trabajo en la UI: el callback solo entrega el timestamp al ViewModel. Usa rememberUpdatedState para los callbacks.

En TareasViewModel crea registrarFotograma(nanos) y reiniciarFotogramas. Solo mide mientras una tarea, cálculo o carga está activa. Calcula intervalos entre fotogramas en milisegundos con timestamps monotónicos. Al comenzar un nuevo trabajo reinicia la serie y su máximo, publica como máximo una muestra cada250ms y conserva40. Expón mayorPausa y pausas como StateFlow. Las pausas de fondo no deben contarse como lentitud de la app. Presenta el máximo y, después del rediseño, un gráfico de intervalos reales. No inventes FPS ni afirmes que el emulador representa rendimiento de un teléfono físico. Compara cómo responde Tócame durante el trabajo; este medidor es un aporte propio y no sustituye la comparación de tiempos de carga de la guía. Prueba sin trabajo, trabajo activo, reinicio y paso al fondo. Compila y verifica antes de seguir.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 7. Respuesta | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Observación de fotogramas reales con ciclo de vida, máximo y serie acotada. | Ayuda a demostrar que el hilo principal sigue dibujando mientras las corrutinas trabajan. |

Diferencia frente a la base: mide pausas de renderizado. Reflexión: una carga rápida no implica una interfaz fluida. Comprobar: no incluir el tiempo en segundo plano.

## 8. Diseño azul y vidrio claro

```text
Compórtate como un experto en diseño de interfaces Android con Jetpack Compose y Material 3. Ayúdame a rediseñar CentroTareasLeon, paquete com.leon.centrotareas, conservando las seis mejoras, el medidor y MVVM. Trabaja en mejora-ia; no cambies main. Usa el código del commit de este prompt como referencia exacta, incluidos ui/CristalTareas.kt, ui/PantallaTareas.kt y ui/theme/Theme.kt. No necesitas fotos: los fondos y el vidrio son procedurales. Los cinco iconos propios están en app/src/main/res/drawable/ic_historial.xml, ic_volver.xml, ic_tocar.xml, ic_check.xml e ic_opciones.xml; sus originales SVG están en docs/recursos/iconos. No dependas de carpetas fuera de la repo.

Fondo blanco frío #F4F8FE, texto #132A44, azul #195DAA y campos de luz azul/cian. Usa el esquema oscuro del sistema (#091324 y #AACFFF). Mantén tipografía Material3, títulos 24–28sp, margen horizontal24dp y separación principal24dp. Nada de tarjetas dentro de tarjetas. Cabecera CentroTareas con botón de historial56dp. Debajo, selector nativo Progreso / Primos / Cargas: una tarea visible. Acciones de al menos56dp; iniciar/contar/A la vez azules y cancelar/Una por una con borde. Pruebas de carga y comparación se despliegan solo al tocarlas. El historial es una pantalla aparte con sus cinco filas y Atrás nativo. A partir de700dp, usa dos columnas: tarea y diagnóstico de300dp; en teléfono, todo se desplaza verticalmente. Adapta texto grande y teclado sin recortar controles.

Crea SuperficieVidrio y BotonVidrio con Canvas y RuntimeShader AGSL en API33+, y degradado de respaldo en API24–32. La lente redondeada muestrea el mismo fondo procedural usando coordenadas globales, desplaza la muestra en los bordes, añade reflejo direccional blanco y sombra suave. No afirmes que refracta otras vistas: refracta ese fondo. El botón tiene respuesta a presión con escala0.96 y resorte amortiguado, pero mantiene la semántica, foco y eventos de Button. Respeta la reducción de animaciones del sistema. Evita transparencias en los datos si perjudican el contraste.

En Comprueba la respuesta muestra Tócame, mayor pausa y una curva de muestras reales del medidor. Captura una lista inmutable local al componer y usa esa misma lista al dibujar: la serie puede reiniciarse mientras Canvas dibuja y no debe fallar ni dividir entre cero. No pongas datos ficticios en la curva. Prueba los tres apartados, historial, error, cancelación, tema oscuro, letra grande y horizontal. Ejecuta assembleDebug, testDebugUnitTest, lintDebug y connectedDebugAndroidTest antes de continuar. Las capturas deben salir del emulador ejecutando esta versión, sin pintar interfaces falsas.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 8. Diseño | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Una tarea visible, historial aparte, vidrio procedural y controles nativos accesibles. | Reducir opciones simultáneas y mantener legible la demostración. |

Diferencia frente a la base: cambia la organización y el material visual. Comprobar: navegación, contraste, texto grande y reinicio de la gráfica durante el dibujo.

## 9. Verificación y documentación

```text
Compórtate como un experto en pruebas Android y documentación para estudiantes. Ayúdame a cerrar la fase2 de CentroTareasLeon sin modificar main ni sustituir mediciones por valores fijos. Trabaja sobre los ocho avances anteriores en mejora-ia, com.leon.centrotareas. Conserva las seis mejoras y el medidor propio. Comprueba assembleDebug, testDebugUnitTest, lintDebug y connectedDebugAndroidTest con JDK21 y un emulador. Respeta la estructura MVVM: la vista muestra y envía eventos, el ViewModel coordina y los repositorios hacen el trabajo.

Prueba progreso, toques, cancelación y reinicio; primos1000→168 y5000000→348513; cargas una por una y a la vez; tiempo máximo2s, error, tres reintentos adicionales y cancelación durante la espera. El historial debe conservar exactamente los cinco resultados más recientes tras cerrar y reabrir. Compara solo tiempos de cargas exitosas. Revisa que los callbacks de Choreographer se retiren al pausar y no incluyan tiempo en segundo plano. Comprueba claro, oscuro, texto al130%, teclado y horizontal sin recortes de acciones. En Opcion convierte cada fila en Row.toggleable(value=valor, enabled=enabled, role=Role.Switch, onValueChange=cambiar), con Switch(onCheckedChange=null). La etiqueta, estado y acción deben formar un único nodo accesible. Añade una prueba de los tres nombres, conmutación y deshabilitado durante la carga. Distingue lo probado en emulador de lo revisado en código: no inventes una prueba de teléfono físico ni de otra versión de Android.

Guarda las capturas directas en docs/capturas_fase2 y los resultados JUnit y lint en docs/pruebas_fase2. Deja README con comparación fase1/fase2 cara a cara y acceso a la galería; docs/GUIA_CODIGO.md con carpeta, archivo y función de cada mejora; docs/VERIFICACION.md con resultados y límites reales. Mantén esta única guía PROMPTS.md ordenada. Registra las respuestas reales del agente cuando se ejecute cada bloque; los bloques existentes son instrucciones de reproducción y no justifican inventar respuestas. Verifica que todos los recursos SVG y VectorDrawable y la referencia del vidrio estén en la repo, sin rutas a carpetas privadas ni claves. Documenta tokens y comportamiento en DESIGN.md. Cada commit tiene que ser concreto y probado; no cambies fechas ni reescribas los avances publicados.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 9. Cierre | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Evidencia de8 pruebas unitarias,14 Android, capturas reales y mapa de código. | Facilitar la revisión y la reproducción sin depender de carpetas privadas. |

Diferencia frente a la base: el informe del repositorio incluye todas las mejoras, sus pruebas y el diseño final. Comprobar: rutas válidas y mensajes coherentes con cada avance.

## 10. Procesos visibles y acabado final

```text
Compórtate como un experto en corrutinas, Jetpack Compose y diseño de interfaces Android. Necesito que CentroTareasLeon, com.leon.centrotareas, haga visible cómo trabajan las tareas. Conserva main, las seis mejoras, el medidor, MVVM, cancelación y los resultados correctos. Trabaja después del avance9 en mejora-ia. Daniella quiere azul y vidrio claro, con más presencia visual, pero pocas opciones. La animación debe enseñar el proceso real, no limitarse a decorar los botones.

Recursos disponibles dentro de la repo: app/src/main/res/drawable-nodpi/cintas_vidrio.png es la imagen de dos cintas de vidrio azul. Su prompt de generación está en docs/recursos/cintas_vidrio.prompt.txt y en sus metadatos. Usa ese archivo ya proporcionado, no otra imagen aleatoria. Los iconos originales SVG están en docs/recursos/iconos y sus VectorDrawable en drawable. La referencia exacta se encuentra en ui/AcabadoTareas.kt, ui/CristalTareas.kt, ui/ProcesosAnimados.kt y ui/PantallaTareas.kt del commit de este prompt. DESIGN.md documenta los tokens. No necesitas carpetas privadas, APIs ni claves.

Integra la imagen en el fondo del área de trabajo: ancho82% de la ventana, alineada a la derecha, desde260dp de altura global. Reserva la mitad izquierda a los títulos. Aplica máscaras suaves para integrarla; no dibujes la imagen sobre los datos. En oscuro reduce su intensidad y en horizontal de700dp+ y en historial ocúltala. Carga el bitmap una vez en el tema y comparte la textura con las lentes RuntimeShader mediante BitmapShader y setInputShader, de forma que fondo y botones usen las mismas coordenadas. Mantén el degradado de respaldo API24–32. El vidrio azul de la acción principal debe conservar contraste con texto blanco. Botón140x56dp, presión con resorte damping0.7/stiffness600: escalaX=1+0.025*p, escalaY=1-0.05*p; la refracción del borde pasa de9 a13dp al presionar. Respeta los ajustes de movimiento del sistema.

Sustituye el selector por tres pestañas accesibles con Role.Tab, estado seleccionado y un indicador de vidrio redondeado que se desplaza con resorte0.85/500. Cambia el contenido con AnimatedContent: entrada de180ms, desplazamiento de220ms de1/12del ancho según dirección y salida de90ms. El avance circular tiene100marcas y usa el progreso real del Flow; interpola solo su dibujo110ms, dejando porcentaje y semántica ligados al valor real. Adapta su tamaño a fontScale y al ancho. En horizontal pon el dial compacto junto a estado y acciones. En teléfono fija Tócame en una fila bajo el selector, rotulada Hilo principal; la gráfica de respuesta queda después del trabajo. En horizontal Tócame sigue en la columna derecha. Mantén una única copia accesible del botón en cada tamaño.

En model/Procesos.kt define AvanceCarga<T>(progreso,resultado nullable,hilo), FaseProceso(Esperando,Trabajando,Listo,Error,Cancelado) y ProcesoVisible. Amplía TareasRepository con progresoPerfil y progresoPedidos: sus adaptadores por defecto usan los métodos suspend existentes para conservar repositorios de prueba. En producción emite pasos con delay50:40para Perfil y60para Pedidos, Flow con flowOn(Default) y conflate, resultado real simulado al final y nombre de hilo capturado en el emisor. Un error de Pedidos debe seguir fallando, y las esperas2/3s siguen siendo simuladas. Conserva las firmas anteriores.

El ViewModel recoge los flujos y expone cada proceso por StateFlow. Una por una comienza Perfil y deja Pedidos esperando hasta recibir su resultado; A la vez lanza ambos con async dentro de coroutineScope. No confundas corrutinas con un hilo exclusivo por tarea. Cada reintento reinicia ambos recorridos; cancelar o agotar tiempo detiene activos y pendientes, conserva los avances y relanza CancellationException. Solo un resultado recibido marca Terminado. Guarda el resultado completo una sola vez al tener las dos cargas, con tiempos medidos e historial iguales que antes.

Muestra dos recorridos simples: Corrutina Perfil y Corrutina Pedidos, con un marcador que se mueve según el progreso real, estado visible y transición de color160ms. El dibujo interpola120ms; no añade un temporizador falso de trabajo. En paralelo se ven avanzar los dos; en secuencial el segundo queda quieto y rotulado En espera hasta su turno. Las animaciones se detienen al terminar, fallar o cancelar. En primos amplía AvancePrimos con evaluado e hilo: cada emisión trae el último candidato examinado y la cantidad encontrada. Muestra Revisando X de N y los primos encontrados mientras trabaja Default; no calcules esos contadores desde un porcentaje inventado. Los movimientos y colores expresan estado; los datos vienen del Repository.

El ViewModel debe exponer identidades incrementales de ejecución: ejecucionCarga, ejecucionPrimos y ejecucionProgreso. Agrupa las dos pistas con key(ejecucionCarga,intento), el cálculo con key(ejecucionPrimos) y el dial con key(ejecucionProgreso). Al iniciar otro trabajo o reintento, la animación comienza en el avance nuevo y no interpola hacia atrás desde el intento anterior. La semántica del Canvas informa el avance pintado. Prueba el caso real en PantallaTareas con un Repository controlado: primer intento en0.8, error y segundo intento en0.01; el marcador nuevo debe estar cerca de0.01 desde su primer fotograma, y un cambio posterior a0.5 dentro de ese mismo intento sí debe interpolarse. La imagen se desvanece180ms al desplazar el trabajo y cada pestaña abre al inicio, para conservar legibles tablas y opciones.

Comprueba fases secuenciales con puertas controladas, solapamiento paralelo y cancelación de ambos, progreso parcial y resultado final del Repository, candidatos reales de primos y cambio de pestaña sin reiniciar el trabajo. Ejecuta assembleDebug, testDebugUnitTest, lintDebug y connectedDebugAndroidTest. Captura los procesos a mitad de ejecución, ambos modos, final/error/cancelación, tema oscuro, letra grande y horizontal. Prueba movimiento reducido. Actualiza README, galería, guía de código, verificación, DESIGN.md y este registro, sin inventar respuestas de Gemini.
```

| Prompt | Qué respondió Gemini | Qué adapté | Por qué |
|---|---|---|---|
| 10. Procesos y acabado | Pendiente de ejecutar en Gemini; referencia realizada con Codex. | Flujos de avance de cargas, candidatos de primos, recorridos animados y vidrio con imagen integrada. | Explicar visualmente el orden, la concurrencia y la respuesta del hilo principal. |

Comprobar: el segundo proceso espera en secuencial, ambos avanzan en paralelo y la pantalla sigue respondiendo. Las corrutinas no equivalen a un hilo por cada tarea.
