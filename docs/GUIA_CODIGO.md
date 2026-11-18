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
