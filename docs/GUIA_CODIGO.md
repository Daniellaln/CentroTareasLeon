# Dónde está cada cosa

En Android Studio abre **app → kotlin+java → com.leon.centrotareas**. En vista Project la ruta completa es **app/src/main/java/com/leon/centrotareas/**. Las rutas siguientes parten de ahí.

| Si pregunta por… | Abre y busca… |
|---|---|
| Corrutinas / trabajos en segundo plano | viewmodel/TareasViewModel.kt: tareaLarga, contar y cargar; viewModelScope.launch. |
| Progreso / barra de avance | data/TareasRepositoryLocal.kt: progresoTarea y progresoPrimos; flowOn(Default). La vista recoge progreso. |
| Cancelar / detener | viewmodel/TareasViewModel.kt: cancelar, cancelarPrimos y cancelarCarga. CancellationException se relanza. |
| Primos / cálculo con CPU | data/TareasRepositoryLocal.kt: esPrimo, progresoPrimos y ensureActive. |
| Simultáneo / paralelo | viewmodel/TareasViewModel.kt: cargarALaVez → cargar(true), coroutineScope, async y await. Una por una llama a cargar(false). |
| Tiempo máximo / timeout | TareasViewModel.kt: conLimite, withTimeout(2000), captura específica de TimeoutCancellationException. |
| Reintentos / recuperar una carga fallida | TareasViewModel.kt: conReintentos. Captura IOException, espera500/1000/2000ms y permite cuatro intentos en total. |
| Historial / resultados guardados | data/HistorialDataStore.kt: preferencias y JSON con los cinco resultados. data/HistorialRepository.kt es la interfaz. |
| Tabla / comparación de tiempos | model/MedicionCarga.kt y TareasViewModel.kt: mediciones, numeroMedicion y duración real. ui/PantallaTareas.kt: PanelCargas. |
| Aporte propio / medidor de respuesta | ui/ObservarFotogramas.kt: Choreographer y ciclo de vida. TareasViewModel.kt: registrarFotograma. PantallaTareas.kt: Diagnostico. |
| Vidrio / reflejos | ui/CristalTareas.kt: MATERIAL, SuperficieVidrio y BotonVidrio. |
| Colores / tema claro y oscuro | ui/theme/Theme.kt: Claro, Oscuro y CentroTareasTheme. |
| Historial aparte / navegación | ui/PantallaTareas.kt: variable historial y BackHandler. |
| MVVM / separación de capas | model son estados, ui muestra, viewmodel coordina y data implementa repositorios. TareasViewModelFactory conecta las dependencias. |

**Dependencias / librerías:** app/build.gradle.kts. Ahí están Compose Material3, lifecycle, corrutinas y DataStore. Namespace y applicationId definen com.leon.centrotareas. Esta app no llama a una API remota.

**Iconos / recursos gráficos:** app/src/main/res/drawable/ic_*.xml. Los originales están en docs/recursos/iconos/*.svg. Android dibuja los VectorDrawable mediante painterResource(R.drawable.ic_...). El fondo se genera en Canvas; no es una foto descargada.

## Mostrar un commit

En Git → Log selecciona main o mejora-ia, elige el commit y abre Show Diff. El lado derecho contiene el código añadido en ese avance. Para enseñar su pantalla completa, abre su carpeta acumulativa; no mezcles archivos de diferentes avances.

En fase2, los commits1–6 son las seis mejoras en el orden de la tabla de la guía. El7 incorpora el medidor propio y sus pruebas. El8 cambia diseño, navegación, tema y recursos. El cierre unifica los nombres y acciones de los interruptores para accesibilidad y añade capturas, pruebas y documentación.

El bloqueo del hilo principal está solo en9de0c8c, en main. El avance siguiente lo sustituye. El código final evita Thread.sleep, runBlocking y GlobalScope en producción.

## Procesos animados: avance10

**Estados / fases:** model/Procesos.kt. Esperando, Trabajando, Listo, Error y Cancelado. AvanceCarga lleva progreso, resultado y nombre del hilo emisor.

**Avance / simulación:** data/TareasRepositoryLocal.kt, progresoPerfil, progresoPedidos y simularCarga. Los pasos de50ms suman las esperas ficticias2/3s y emiten desde Default. No son llamadas a Internet.

**Orden / concurrencia:** viewmodel/TareasViewModel.kt, cargar y observarProceso. En secuencial espera el primer resultado antes de iniciar el segundo; en paralelo contiene dos async dentro de coroutineScope. Una corrutina no equivale a un hilo exclusivo. perfilVisible y pedidosVisible describen lo que está ocurriendo.

**Primos / candidato actual:** model/AvancePrimos.kt y progresoPrimos en el Repository. evaluado es el último número examinado, no una cifra inventada a partir del porcentaje. El ViewModel expone avancePrimos y hastaPrimos.

**Recorridos / animación de trabajo:** ui/ProcesosAnimados.kt, ProcesoAnimado. El marcador interpola el avance recibido y cambia con la fase. ui/AcabadoTareas.kt contiene dial y selector. PantallaTareas usa AnimatedContent para las transiciones; cambiar pestaña no reinicia los Jobs.

**Imagen / material:** app/src/main/res/drawable-nodpi/cintas_vidrio.png. El tema carga una copia compartida; ui/CristalTareas.kt la incorpora al fondo y a las lentes con BitmapShader. El prompt está en docs/recursos/cintas_vidrio.prompt.txt. Hilo principal y Tócame quedan fijos en teléfono, para tocar mientras avanzan las tareas.

## Recorrido corto para exponer

“Pulso Una por una. Primero avanza Perfil y Pedidos espera. Ahora pulso A la vez: los dos avanzan y el tiempo total baja. Mientras trabajan, puedo tocar este botón del hilo principal. El cálculo de primos se hace en Default y aquí se ve su avance. Si cancelo, se detiene el recorrido. Los resultados y los estados salen del Repository y los observa el ViewModel.”

Para mostrar el caso que bloquea, usa el commit histórico9de0c8c de main, carpeta fase1/commit_03_bloqueo/proyecto. La guía exige eliminarlo del código final: no se añadió un botón bloqueante a esta versión.
