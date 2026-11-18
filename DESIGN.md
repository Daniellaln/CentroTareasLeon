---
name: "CentroTareas"
description: "Android nativo: vidrio azul, ilustración compartida y procesos observados."
colors:
  primary: "#195DAA"
  on-primary: "#FFFFFF"
  primary-container: "#D9E9FF"
  on-primary-container: "#123D70"
  secondary: "#14748B"
  secondary-container: "#D5EFF4"
  on-secondary-container: "#064A5A"
  background: "#F4F8FE"
  on-background: "#132A44"
  surface: "#FCFDFF"
  on-surface: "#132A44"
  surface-variant: "#E8EFF8"
  on-surface-variant: "#475F7A"
  outline: "#6C819A"
  outline-variant: "#D3DEEB"
  error: "#B32635"
  error-container: "#FFE4E8"
  on-error-container: "#7D1728"
  dark-primary: "#AACFFF"
  dark-on-primary: "#00315E"
  dark-primary-container: "#164778"
  dark-on-primary-container: "#D9E9FF"
  dark-secondary: "#8AD3E2"
  dark-secondary-container: "#0C4553"
  dark-on-secondary-container: "#BAEDF7"
  dark-background: "#091324"
  dark-on-background: "#E3EDFA"
  dark-surface: "#101F34"
  dark-on-surface: "#E3EDFA"
  dark-surface-variant: "#243850"
  dark-on-surface-variant: "#B5C8E0"
  dark-outline: "#8BA3C0"
  dark-outline-variant: "#3B526F"
  dark-error: "#FFB1BE"
  dark-error-container: "#632536"
  dark-on-error-container: "#FFD9E2"
  glass-fallback-start: "#F5FAFF"
  glass-fallback-end: "#DCEBFB"
  dark-glass-fallback-start: "#182943"
  dark-glass-fallback-end: "#0C182A"
  accent-glass-fallback-end: "#104582"
  glass-accent-content: "#FFFFFF"
typography:
  display-medium:
    fontFamily: "sans-serif"
    fontSize: "45sp"
    fontWeight: 400
    lineHeight: "52sp"
    letterSpacing: "0sp"
  headline-medium:
    fontFamily: "sans-serif"
    fontSize: "28sp"
    fontWeight: 700
    lineHeight: "36sp"
    letterSpacing: "0sp"
  headline-small:
    fontFamily: "sans-serif"
    fontSize: "24sp"
    fontWeight: 700
    lineHeight: "32sp"
    letterSpacing: "0sp"
  result-headline:
    fontFamily: "sans-serif"
    fontSize: "24sp"
    fontWeight: 600
    lineHeight: "32sp"
    letterSpacing: "0sp"
  headline-small-regular:
    fontFamily: "sans-serif"
    fontSize: "24sp"
    fontWeight: 400
    lineHeight: "32sp"
    letterSpacing: "0sp"
  title-large:
    fontFamily: "sans-serif"
    fontSize: "22sp"
    fontWeight: 400
    lineHeight: "28sp"
    letterSpacing: "0sp"
  diagnostic-title:
    fontFamily: "sans-serif"
    fontSize: "22sp"
    fontWeight: 700
    lineHeight: "28sp"
    letterSpacing: "0sp"
  title-medium:
    fontFamily: "sans-serif"
    fontSize: "16sp"
    fontWeight: 500
    lineHeight: "24sp"
    letterSpacing: "0.2sp"
  body-large:
    fontFamily: "sans-serif"
    fontSize: "16sp"
    fontWeight: 400
    lineHeight: "24sp"
    letterSpacing: "0.5sp"
  body-medium:
    fontFamily: "sans-serif"
    fontSize: "14sp"
    fontWeight: 400
    lineHeight: "20sp"
    letterSpacing: "0.2sp"
  body-small:
    fontFamily: "sans-serif"
    fontSize: "12sp"
    fontWeight: 400
    lineHeight: "16sp"
    letterSpacing: "0.4sp"
  label-large:
    fontFamily: "sans-serif"
    fontSize: "14sp"
    fontWeight: 500
    lineHeight: "20sp"
    letterSpacing: "0.1sp"
  process-title:
    fontFamily: "sans-serif"
    fontSize: "16sp"
    fontWeight: 600
    lineHeight: "24sp"
    letterSpacing: "0.2sp"
  selected-tab:
    fontFamily: "sans-serif"
    fontSize: "14sp"
    fontWeight: 700
    lineHeight: "20sp"
    letterSpacing: "0.1sp"
  compact-dial:
    fontFamily: "sans-serif"
    fontSize: "28sp"
    fontWeight: 400
    lineHeight: "36sp"
    letterSpacing: "0sp"
rounded:
  glass: "30dp"
  selection-indicator: "26dp"
  tab-touch-shape: "28dp"
spacing:
  inline-detail: "6dp"
  compact: "8dp"
  compact-row: "10dp"
  control-gap: "12dp"
  field-gap: "16dp"
  section-intro: "20dp"
  section: "24dp"
  wide-columns: "36dp"
  indicator-inset: "4dp"
components:
  button-primary:
    textColor: "{colors.glass-accent-content}"
    typography: "{typography.title-medium}"
    rounded: "{rounded.glass}"
    padding: "0dp 16dp"
    width: "140dp"
    height: "56dp"
  button-outlined:
    textColor: "{colors.primary}"
    typography: "{typography.label-large}"
    height: "56dp"
  button-text:
    textColor: "{colors.primary}"
    typography: "{typography.label-large}"
    height: "56dp"
  button-glass:
    textColor: "{colors.primary}"
    typography: "{typography.title-medium}"
    rounded: "{rounded.glass}"
    padding: "0dp 16dp"
    height: "60dp"
  button-main-thread-phone:
    textColor: "{colors.primary}"
    typography: "{typography.label-large}"
    rounded: "{rounded.glass}"
    padding: "0dp 16dp"
    height: "56dp"
  glass-history:
    textColor: "{colors.primary}"
    rounded: "{rounded.glass}"
    size: "56dp"
  glass-selector:
    rounded: "{rounded.glass}"
    height: "56dp"
  selection-indicator:
    rounded: "{rounded.selection-indicator}"
  segment-selected:
    textColor: "{colors.on-primary-container}"
    typography: "{typography.selected-tab}"
  segment-unselected:
    textColor: "{colors.on-surface}"
    typography: "{typography.label-large}"
  input-number:
    textColor: "{colors.on-surface}"
    typography: "{typography.body-large}"
  option-switch:
    textColor: "{colors.on-surface}"
    typography: "{typography.body-large}"
    height: "56dp"
  process-track:
    textColor: "{colors.on-surface}"
    typography: "{typography.process-title}"
    height: "28dp"
  progress-dial:
    textColor: "{colors.primary}"
    typography: "{typography.display-medium}"
---
# Design System: CentroTareas

## Overview

**Creative North Star: "Azul y vidrio claro"**

Azul profundo y celeste sobre blanco frío. La lectura permanece mate y nítida; el vidrio da presencia a selección y controles. Una ilustración de cintas azules compartida con las lentes refuerza el área de trabajo. El tema oscuro reduce su intensidad y responde a la preferencia del sistema.

Es un sistema Android nativo con Compose Material3. La tipografía conserva sus convenciones de plataforma, mientras el material propio usa bordes luminosos, presión elástica y selección móvil. Dial y pistas animadas representan progreso observado: la ilustración es expresiva, los datos y las fases son reales.

**Key Characteristics:**

- Lectura separada del área ilustrada y texto sin distorsión.
- Vidrio azul para acción principal y vidrio claro para selección y prueba de respuesta.
- Jerarquía Material3 y controles con semántica nativa.
- Movimiento breve ligado a selección, presión y avances observados.
- Cian para finalización, rojo semántico para error y tinta secundaria para espera o cancelación.

Autoridad: `app/src/main/java/com/leon/centrotareas/ui/theme/Theme.kt`, `ui/PantallaTareas.kt`, `ui/CristalTareas.kt`, `ui/AcabadoTareas.kt` y `ui/ProcesosAnimados.kt`. La identidad azul/vidrio sigue vigente; el refuerzo de material y visualizaciones responde al contrato ampliado de `../direccion_fase2.md`. La composición de esta pantalla no impone una plantilla universal a pantallas futuras.

El frontmatter usa dp para geometría y sp para texto. Las alturas de contorno, texto, selector e interruptor son mínimos (`heightIn`); las de los botones de vidrio son dimensiones fijas actuales. `process-track.height` mide solo el Canvas, no toda su fila. `progress-dial` adapta su tamaño al espacio y fuente como se documenta en Layout. Las parejas `dark-*` sustituyen a las claras según el sistema. Los roles Material3 no sobrescritos siguen los defaults de la dependencia.

## Colors

Una paleta fría con azul de acción y cian de apoyo. Los hexadecimales normativos están en el frontmatter. Las rampas OKLCH del sidecar son metadatos sintetizados para visualizar cada color en el panel; no son nuevos colores de producción ni una escala aprobada para otras superficies.

### Primary

- **Azul de acción** (`primary` / `dark-primary`): iniciar, destacar resultados, iconos y traza medida.
- **Azul de selección** (`primary-container` y su pareja oscura): base del indicador activo; la implementación aplica un degradado vertical con alfa 0.95 a 0.5 sobre el vidrio. `on-primary-container` mantiene legible su etiqueta.
- **Texto sobre acción nativa** (`on-primary` y su pareja oscura): roles del esquema Material3. La acción de vidrio acentuado usa `glass-accent-content`, blanco en ambos temas.

### Secondary

- **Cian de apoyo** (`secondary`, `secondary-container`, `on-secondary-container` y sus parejas oscuras): roles de apoyo del esquema Material3; `secondary` identifica además los procesos terminados.

### Neutral

- **Blanco frío / noche azul** (`background`, `surface` y parejas oscuras): base cromática del tema. La pantalla visible usa además el campo procedural de `SuperficieVidrio(fondo=true)`; no reemplazar ese campo por un relleno plano al reproducir esta pantalla.
- **Tinta azul** (`on-background`, `on-surface` y parejas oscuras): texto principal.
- **Tinta secundaria** (`on-surface-variant` y pareja oscura): ayuda, tiempos, fechas y explicaciones.
- **Capas y contornos** (`surface-variant`, `outline`, `outline-variant` y parejas oscuras): soporte de estados nativos y divisores.
- **Alternativa de vidrio** (`glass-fallback-start`, `glass-fallback-end` y parejas oscuras): extremos del degradado lineal cuando no se dispone de la lente AGSL.

El vidrio acentuado tiene un extremo fallback propio (`accent-glass-fallback-end`); el otro extremo reutiliza `primary`. En AGSL usa una mezcla azul específica, registrada en profundidad.

`error`, `error-container`, `on-error-container` y sus parejas oscuras son colores semánticos de fallo. Los mensajes de error usan `error`; no emplearlo como acento decorativo.

## Typography

**Display Font:** sans-serif de Material3 (`Typography()`).
**Body Font:** la misma sans-serif de plataforma; no hay una fuente personalizada.

**Character:** jerarquía nativa, lectura directa y cifras amplias para el progreso. No se fuerza una familia de exhibición ajena a Android. Los tamaños se heredan de Material3; los pesos de encabezados que aparecen en el frontmatter reflejan las sustituciones explícitas de `PantallaTareas.kt`, `AcabadoTareas.kt` y `ProcesosAnimados.kt`.

### Hierarchy

| Token | Estilo nativo y uso |
| --- | --- |
| `display-medium` | `displayMedium`: porcentaje del dial no compacto. |
| `compact-dial` | `headlineMedium` regular: porcentaje con ancho disponible menor de 200dp. |
| `headline-medium` | `headlineMedium` con Bold: título de la tarea. |
| `headline-small` | `headlineSmall` con Bold: cabecera de pantalla. |
| `result-headline` | `headlineSmall` con SemiBold: resultado. |
| `headline-small-regular` | `headlineSmall`: título del historial vacío. |
| `title-large` | `titleLarge`: título de comparación. |
| `diagnostic-title` | `titleLarge` con Bold: diagnóstico de respuesta. |
| `title-medium` | `titleMedium`: estado, acción principal, botón táctil ancho e historial. |
| `process-title` | `titleMedium` con SemiBold: nombre de la corrutina. |
| `selected-tab` | `labelLarge` con Bold: pestaña seleccionada. |
| `body-large` | `bodyLarge`: lectura general y resumen de resultados. |
| `body-medium` | `bodyMedium`: diagnóstico breve. |
| `body-small` | `bodySmall`: notas, tiempos y fechas. |
| `label-large` | `labelLarge`: controles Material3. |

La dependencia resuelta es Material3 1.4.0, confirmada mediante dependencyInsight. Las métricas heredadas se verificaron en sus sources locales, `TypeScaleTokens.kt` y `TypefaceTokens.kt`; la aplicación sigue delegando en `Typography()`. Al cambiar la dependencia, reextraer métricas en lugar de fijar esta tabla dentro de la UI.

**The Native Type Rule.** Reutilizar los roles de MaterialTheme.typography y sus pesos explícitos; conservar el escalado de texto del sistema.

## Layout

La pantalla usa insets de estado, navegación y teclado, margen horizontal `section` y trabajo desplazable. Al cambiar de tarea se crea un estado de desplazamiento nuevo y el contenido comienza arriba. Historial sigue como destino separado con volver nativo y del sistema.

A partir de 700dp, el trabajo es flexible y el diagnóstico mide 300dp, separados por `wide-columns`. Por debajo, la fila «Hilo principal» y «Tócame» queda fuera del desplazamiento del trabajo, justo bajo el selector: pesos 1 y 1.2, separación `control-gap`, botón de 56dp e icono de 20dp. En ancho, «Tócame» aparece en la columna derecha, alto 60dp e icono de 24dp; el diagnóstico inferior del teléfono conserva la medición sin duplicar ese botón.

`CabeceraTrabajo` ocupa la mitad izquierda si su ancho disponible es menor de 500dp, con altura mínima de 112dp; desde 500dp ocupa todo el ancho y su mínimo es 64dp. Su separación de título/detalle usa `compact-row` y relleno vertical `compact`.

El dial usa un lado de 180dp multiplicado por `max(fontScale,1)`, limitado al ancho disponible. Por debajo de 200dp disponibles cambia a un lado máximo de 140dp y `compact-dial`. En la fila ancha se le reservan 140dp. No fijar cifras de texto en píxeles para conservar este comportamiento.

`FlowRow` ajusta acciones con separación horizontal `control-gap` y vertical `compact`. La acción principal mide 140×56dp; la secundaria mantiene altura mínima de 56dp. Campos, ayuda e historial conservan el ritmo de `inline-detail`, `compact`, `field-gap` y `section`. No se introduce otra retícula.

La ilustración ocupa el 82% derecho del ancho raíz, desde 260dp de altura, conservando su proporción. Se oculta con ancho raíz igual o mayor a 700dp y en el historial. Al dejar el inicio del desplazamiento pierde visibilidad con una transición de 180ms; vuelve al regresar arriba. La región izquierda y los bordes verticales de la textura llevan máscaras en AGSL para separar lectura e imagen.

## Elevation & Depth

La lectura usa color y espacio, sin tarjetas elevadas para cada bloque. Las superficies de vidrio añaden elevación nativa de 6dp, borde luminoso de 1dp y radio `glass`; sombra ambiental `0x201C426E` y puntual `0x301C426E`. El fondo no lleva esa sombra ni borde.

En API33+, AGSL comparte campo procedural azul/cian y `cintas_vidrio.png`. `Theme.kt` decodifica la imagen una vez por instancia de recursos mediante `remember(recursos)` y la proporciona a las superficies; cada shader recibe esa misma textura con `BitmapShader`. Las coordenadas globales mantienen continuidad entre fondo y lente. La lente desplaza la muestra en una banda interior de 14dp: refracción de 9dp en reposo a 13dp bajo presión. Añade película clara/oscura y brillo especular; el vidrio acentuado mezcla el resultado con `half3(.055,.24,.50)` a un factor de 0.88. No captura otras vistas ni distorsiona texto, iconos, datos o marcadores dibujados después.

La máscara de imagen combina suavizado horizontal de 0.35 a 0.6 del ancho raíz, entrada vertical de 0 a 0.12 y salida de 0.78 a 1 de su alto. Su intensidad máxima es 0.95 en claro y 0.13 en oscuro, multiplicada por la visibilidad animada del arte.

En API24–32, o si falla RuntimeShader, el vidrio usa degradados del frontmatter. El fondo conserva la ilustración, a alfa 0.8 en claro o 0.13 en oscuro, con igual colocación y visibilidad; esa ruta no reproduce las máscaras ni refracción AGSL. El bitmap también se usa como ilustración, no como medición. Esta alternativa sigue sin prueba en API antigua o teléfono físico; la evidencia visual recapturada corresponde a emulador API37.

**The Shared Background Rule.** La lente y el fondo evalúan el mismo campo procedural y la misma textura en coordenadas globales. No atribuir a la lente refracción de otras vistas ni del contenido.

**The Observed Process Rule.** Los marcadores representan progreso y fases del Repository. Interpolar solo dentro de una ejecución o intento; reiniciar inmediatamente al cambiar su identidad.

**The Honest Trace Rule.** La traza representa intervalos reales entre fotogramas. No convertirla en una animación ornamental.

## Shapes

Vidrio con radio `glass`; el cálculo de la lente limita su radio a la mitad de la dimensión menor. El indicador móvil tiene radio `selection-indicator`, inset `indicator-inset`, degradado vertical de contenedor primario y borde blanco con alfa 0.55 de 1dp. Las áreas táctiles de pestañas usan `tab-touch-shape`. Son formas propias, ya no `SegmentedButtonDefaults.itemShape`. Contorno, campo e interruptores conservan forma Material3.

Los iconos siguen como SVG en `docs/recursos/iconos` y VectorDrawable en `app/src/main/res/drawable`. La imagen `app/src/main/res/drawable-nodpi/cintas_vidrio.png` es textura ilustrativa, sin texto ni controles; su prompt está incrustado en PNG y documentado en `docs/recursos/ORIGEN.md`. No es una composición raster de la UI ni reemplaza sus componentes.

## Components

### Buttons

- **Principal:** `BotonVidrio(acentuado=true)` de 140×56dp, texto `title-medium` blanco y relleno horizontal de 16dp. Usa material azul AGSL o degradado `primary` a `accent-glass-fallback-end`; no es ya un botón relleno plano.
- **Vidrio claro:** acceso a historial de 56dp, prueba del hilo principal de 56dp en teléfono o 60dp en ancho. El teléfono usa `labelLarge`; el ancho usa `titleMedium`.
- **Presión compartida:** presión normalizada p con `spring(dampingRatio=0.7, stiffness=600)`, escala X `1+0.025p`, Y `1−0.05p`; alimenta también la refracción. Deshabilitado: alfa de la capa 0.45, sin acción; tinta blanca en acentuado o `onSurfaceVariant` en claro.
- **Contorno y texto:** controles Material3 para cancelar, reintentar o revelar pruebas/comparación. Altura mínima de 56dp; cancelar carga activa usa mínimo local de 48dp. Foco y ripple proceden de Material3, sin hover propio.

### Selection and task transitions

`SelectorTareas` contiene tres opciones con `selectableGroup` y `selectable(role=Role.Tab)`. Cada una ocupa un tercio del ancho, mínimo 56dp, relleno horizontal 8dp/vertical 12dp. La elegida usa `selected-tab` y `onPrimaryContainer`; las otras `labelLarge` y `onSurface`. El indicador se desplaza con `spring(dampingRatio=0.85, stiffness=500)`; su posición procede de la selección real.

`AnimatedContent` cambia el trabajo con entrada de opacidad de 180ms y deslizamiento horizontal de 220ms desde un doceavo del ancho, según dirección del cambio; la salida de opacidad dura 90ms. La imagen cambia visibilidad en 180ms. Estas son transiciones de interfaz, no duraciones del trabajo.

### Progress dial

`DialProgreso` toma el Flow real, acotado entre 0 y 1, e interpola el dibujo durante 110ms con `LinearOutSlowInEasing`. Sus cien marcas usan trazos de 2dp, radio exterior menos 8dp y marcas de 5dp. El aro interior lleva margen de 24dp y trazo de 6dp, con superficie radial y barrido azul. La cifra muestra el valor real, mientras el dibujo interpola. El nodo une descendientes y publica `ProgressBarRangeInfo` del valor real. `key(ejecucionProgreso)` reinicia la animación al iniciar otra ejecución.

### Observed process tracks

`ProcesoAnimado` muestra nombre `process-title`, fase `labelLarge`, Canvas de 28dp y detalle `bodySmall`. Pista de 4dp, extremos a 12dp, círculo de 7dp y halo de 11dp a alfa 0.15. Terminado añade una marca blanca de 1.5dp. Color por fase: azul trabajando, cian terminado, rojo error, tinta secundaria esperando o cancelado; el cambio cromático dura 160ms.

El marcador interpola 120ms con `LinearOutSlowInEasing` dentro del mismo intento. Publica nombre, fase y rango de progreso accesibles. Perfil y Pedidos tienen pistas independientes ligadas a sus Flow reales: avanzan juntas en paralelo y Pedidos espera a Perfil en secuencial. Primos muestra candidato examinado y cantidad encontrados del estado real; no se anima una cuenta ficticia.

`key(ejecucion,intento)` recrea las dos pistas al iniciar o reintentar; `key(ejecucionPrimos)` hace lo mismo para primos. La identidad procede del ViewModel y evita interpolar hacia atrás desde el intento anterior. La regresión 0.8 → 0.01 → 0.5 comprueba reinicio inmediato y posterior interpolación. No extender suavizado entre identidades.

### Inputs / Fields and options

`OutlinedTextField` numérico de una línea, ancho disponible, etiqueta persistente y teclado numérico; defaults Material3 de color, forma y foco. Se bloquea mientras calcula, y un valor inválido impide iniciar. No se inventa un error visual del campo.

Las opciones usan una fila `toggleable(Role.Switch)` y `Switch(onCheckedChange=null)`, una sola acción accesible, mínimo 56dp y estado deshabilitado durante carga. Opciones y comparación usan `AnimatedVisibility` con defaults de Compose.

### Result, history and measured trace

Resultado sin tarjeta: confirmación vectorial, `result-headline` y detalle. Historial usa filas `titleMedium`, `bodyLarge`, `bodySmall` y divisor `outlineVariant`, sin ilustración. La traza conserva un Canvas de 70dp, trazo primario de 2dp, guía de 1dp y escala con máximo mínimo de 33ms; aparece con más de una muestra y describe intervalos reales entre fotogramas.

Todo movimiento depende de las APIs de animación Compose y respeta la escala de duración del sistema, incluida su reducción o desactivación. Ninguna espera de tareas, timeout o reintento es un token de movimiento. No hay un bucle ornamental independiente del estado.

El sidecar contiene nueve ejemplos HTML/CSS autónomos para el panel, con equivalencia de referencia 1dp a 1px. Son traducciones, no producción: aproximan el fallback sin cargar la textura, no reproducen AGSL ni resortes Compose y mantienen foco del navegador. Los diagramas de proceso son instantáneas ilustrativas; datos, semántica y movimiento reales se validan en Android.

## Do's and Don'ts

### Do:

- Do conservar las parejas de color del tema activo y el escalado de texto del sistema.
- Do mantener el texto nítido y usar la ilustración como fondo, sin cifras ni controles incrustados.
- Do ligar cada pista y cifra a estados observados, con semántica de progreso y fase.
- Do reiniciar dial y marcadores al cambiar ejecución o intento, antes de interpolar nuevos valores.
- Do conservar visibles los controles del hilo principal y el ajuste de acciones con FlowRow.
- Do asociar cada interruptor a su etiqueta en un único nodo accionable con Role.Switch.
- Do respetar la reducción de animaciones del sistema y medir el comportamiento real antes de afirmar fluidez.

### Don't:

- Don't sustituir Typography() por una fuente de exhibición o un tamaño fijo en píxeles.
- Don't describir la lente como un desenfoque o una captura de vistas subyacentes.
- Don't prolongar una animación desde el avance del intento anterior al iniciar otro.
- Don't registrar las esperas de tareas, timeout o reintentos como duraciones de animación.
- Don't convertir los ejemplos HTML del panel en autoridad sobre la implementación Compose.
- Don't afirmar validación del fallback en API24–32 ni de teléfono físico: la evidencia disponible corresponde al emulador API37.

No se canonizan omisiones del procedimiento: no hubo semilla de concepto ni tarjeta QUALITY BAR. El refuerzo conserva el mundo confirmado; no crea una aprobación retrospectiva. Tampoco se convierte la prueba del árbol semántico en auditoría auditiva de TalkBack. El alcance permanece registrado en `.impeccable/review/PROCESO.md`.
