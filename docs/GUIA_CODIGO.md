# Dónde está cada cosa

Paquete principal: app/src/main/java/com/leon/centrotareas.

Hilos/corrutinas: viewmodel/TareasViewModel.kt, tareaLarga. launch conserva el ciclo de vida; withContext(Default) cambia el contexto del trabajo; los logs HILO muestran el hilo real.

Progreso/barra: _progreso y progreso en el ViewModel; LinearProgressIndicator en ui/PantallaTareas.kt.

Cancelar/detener: trabajo y cancelar; calculo y cancelarPrimos; cargaJob y cancelarCarga. CancellationException se relanza.

Primos/cálculo: data/TareasRepositoryLocal.kt, contarPrimos y esPrimo. ensureActive comprueba cancelación.

Paralelo/simultáneo: cargarALaVez llama a cargar(true); coroutineScope contiene los dos async y sus await. Secuencial/una por una usa cargar(false).

Error/reintento: simularError se entrega al Repository; cargar captura el error después de dejar pasar la cancelación. Reintentar llama de nuevo a cargarALaVez.

MVVM/arquitectura: model guarda estados, viewmodel coordina, ui muestra. data guarda la interfaz y su implementación. TareasViewModelFactory construye la dependencia.

Dependencias/librerías: app/build.gradle.kts. Paquete/identificador: namespace y applicationId en ese archivo.

En Android Studio, Git → Log → elegir commit → Show Diff permite enseñar el cambio de cada paso. El bloqueo original solo existe en su commit, no en el archivo final.
