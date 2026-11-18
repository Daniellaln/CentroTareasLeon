---
version: 1
slug: "om-leon-centrotareas-ui-pantallatareas-kt-50e76765"
primary_target: "app/src/main/java/com/leon/centrotareas/ui/PantallaTareas.kt"
related_targets: ["app/src/main/java/com/leon/centrotareas/ui/theme/Theme.kt"]
---

## Direction contract

THESIS: CentroTareas demuestra corrutinas. Una tarea activa ocupa el foco; el usuario ve su progreso y puede comprobar la respuesta de la pantalla.

OWN-WORLD: azul profundo y celeste sobre blanco frío. Texto Material3 oscuro y nítido; lectura mate, vidrio en selección y controles. La firma visual es una traza de intervalos reales entre fotogramas, no un adorno animado.

STORY: elegir tarea, iniciar, tocar, cancelar o comparar. Opciones de error, timeout y reintentos se despliegan en Cargas; el historial tiene su propio destino.

FIRST VIEWPORT: título CentroTareas y acceso a historial; selector nativo Progreso/Primos/Cargas; área de trabajo con indicador amplio, estado y acción principal. Prueba de respuesta abajo, compacta. En ancho, trabajo y diagnóstico comparten dos columnas. Historial muestra cinco filas, con volver nativo y del sistema.

FORM: Android nativo desde código. Dirección azul/vidrio y organización confirmadas por Daniella; sin comp raster. Controles Material3 con respuesta de resorte y lente AGSL que refracta el mismo fondo procedural, sin distorsionar contenido. Alternativa anterior a API33 y opción del sistema de quitar animación. Uso en clase con luz ambiente: base clara; oscuro para la preferencia del sistema.

FINISH: unreviewed and undocumented is unfinished; this build ends with the finish review, the verdict, DESIGN.md, and every shipping raster carrying its provenance
