---
name: CentroTareas
description: "Android nativo: azul, lectura mate y vidrio claro en controles."
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
rounded:
  glass: "30dp"
spacing:
  inline-detail: "6dp"
  compact: "8dp"
  compact-row: "10dp"
  control-gap: "12dp"
  field-gap: "16dp"
  section-intro: "20dp"
  section: "24dp"
  primary-horizontal: "28dp"
  display-gap: "32dp"
  wide-columns: "36dp"
components:
  button-primary:
    backgroundColor: "{colors.primary}"
    textColor: "{colors.on-primary}"
    typography: "{typography.label-large}"
    padding: "0dp 28dp"
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
  glass-history:
    textColor: "{colors.primary}"
    rounded: "{rounded.glass}"
    size: "56dp"
  glass-selector:
    rounded: "{rounded.glass}"
    height: "56dp"
  segment-selected:
    backgroundColor: "{colors.primary-container}"
    textColor: "{colors.on-primary-container}"
    typography: "{typography.label-large}"
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
---
# Design System: CentroTareas

## Overview

**Creative North Star: "Azul y vidrio claro"**

Azul profundo y celeste sobre blanco frío. La lectura permanece mate y nítida; el vidrio da presencia a la selección y a los controles. El tema oscuro conserva las mismas funciones de color y responde a la preferencia del sistema.

Es un sistema Android nativo con Compose Material3. La tipografía y los controles mantienen sus convenciones de plataforma; el material propio se limita a superficies procedurales, bordes luminosos y respuesta de presión. No existe una composición raster que actúe como autoridad.

**Key Characteristics:**

- Lectura oscura sobre superficies claras en el tema diurno.
- Vidrio localizado en selección y controles; texto sin distorsión.
- Jerarquía y estados nativos Material3.
- Azul para acción y medición; rojo semántico para error.

Autoridad: `app/src/main/java/com/leon/centrotareas/ui/theme/Theme.kt`, `ui/PantallaTareas.kt` y `ui/CristalTareas.kt`. El contrato de la pantalla está en `.impeccable/surfaces/om-leon-centrotareas-ui-pantallatareas-kt-50e76765.md`; su composición no establece una plantilla obligatoria para toda pantalla futura. Este documento registra la implementación, no nuevas decisiones de identidad.

El frontmatter usa unidades Android: dp para geometría y sp para texto. Las alturas de componentes que se implementan con `heightIn(min=…)` son mínimos; no bloquear su crecimiento con texto grande. Los valores `height` de `button-glass` y `size` de `glass-history` sí son dimensiones actuales fijas. Las claves `dark-*` sustituyen a su pareja clara al activar el tema oscuro, no son una segunda paleta simultánea. Los roles Material3 no sobrescritos siguen el esquema por defecto de la dependencia; no inventarles tokens propios.

## Colors

Una paleta fría con azul de acción y cian de apoyo. Los hexadecimales normativos están en el frontmatter. Las rampas OKLCH del sidecar son metadatos sintetizados para visualizar cada color en el panel; no son nuevos colores de producción ni una escala aprobada para otras superficies.

### Primary

- **Azul de acción** (`primary` / `dark-primary`): iniciar, destacar resultados, iconos y traza medida.
- **Azul de selección** (`primary-container` y su pareja oscura): fondo del segmento activo; la implementación aplica alfa 0.8 sobre el vidrio. `on-primary-container` mantiene legible su etiqueta.
- **Texto sobre acción** (`on-primary` y su pareja oscura): etiquetas de botón relleno.

### Secondary

- **Cian de apoyo** (`secondary`, `secondary-container`, `on-secondary-container` y sus parejas oscuras): roles de apoyo del esquema Material3; no compiten con el azul de acción.

### Neutral

- **Blanco frío / noche azul** (`background`, `surface` y parejas oscuras): base cromática del tema. La pantalla visible usa además el campo procedural de `SuperficieVidrio(fondo=true)`; no reemplazar ese campo por un relleno plano al reproducir esta pantalla.
- **Tinta azul** (`on-background`, `on-surface` y parejas oscuras): texto principal.
- **Tinta secundaria** (`on-surface-variant` y pareja oscura): ayuda, tiempos, fechas y explicaciones.
- **Capas y contornos** (`surface-variant`, `outline`, `outline-variant` y parejas oscuras): soporte de estados nativos y divisores.
- **Alternativa de vidrio** (`glass-fallback-start`, `glass-fallback-end` y parejas oscuras): extremos del degradado lineal cuando no se dispone de la lente AGSL.

`error`, `error-container`, `on-error-container` y sus parejas oscuras son colores semánticos de fallo. Los mensajes de error usan `error`; no emplearlo como acento decorativo.

## Typography

**Display Font:** sans-serif de Material3 (`Typography()`).
**Body Font:** la misma sans-serif de plataforma; no hay una fuente personalizada.

**Character:** jerarquía nativa, lectura directa y cifras amplias para el progreso. No se fuerza una familia de exhibición ajena a Android. Los tamaños se heredan de Material3; los pesos de encabezados que aparecen en el frontmatter reflejan las sustituciones explícitas de `PantallaTareas.kt`.

### Hierarchy

| Token | Estilo nativo y uso |
| --- | --- |
| `display-medium` | `displayMedium`: porcentaje de progreso. |
| `headline-medium` | `headlineMedium` con Bold: título de la tarea. |
| `headline-small` | `headlineSmall` con Bold: cabecera de pantalla. |
| `result-headline` | `headlineSmall` con SemiBold: resultado. |
| `headline-small-regular` | `headlineSmall`: título del historial vacío. |
| `title-large` | `titleLarge`: título de comparación. |
| `diagnostic-title` | `titleLarge` con Bold: diagnóstico de respuesta. |
| `title-medium` | `titleMedium`: estado, botón táctil e historial. |
| `body-large` | `bodyLarge`: lectura general y resumen de resultados. |
| `body-medium` | `bodyMedium`: diagnóstico breve. |
| `body-small` | `bodySmall`: notas, tiempos y fechas. |
| `label-large` | `labelLarge`: controles Material3. |

La dependencia resuelta es Material3 1.4.0, confirmada mediante dependencyInsight. Las métricas heredadas se verificaron en sus sources locales, `TypeScaleTokens.kt` y `TypefaceTokens.kt`; la aplicación sigue delegando en `Typography()`. Al cambiar la dependencia, reextraer métricas en lugar de fijar esta tabla dentro de la UI.

**The Native Type Rule.** Reutilizar los roles de MaterialTheme.typography y sus pesos explícitos; conservar el escalado de texto del sistema.

## Layout

La pantalla implementada usa insets de estado, navegación y teclado, margen horizontal de sección y contenido desplazable. El historial ocupa un destino separado con volver nativo y volver del sistema; estas son decisiones de esta superficie, documentadas en su contrato.

El ritmo reutiliza `compact`, `control-gap` y `section` para agrupar controles, separar acciones y abrir secciones. `primary-horizontal` pertenece al relleno horizontal del botón principal; no es un paso universal obligatorio. Los valores menores de `inline-detail` y `compact-row` agrupan texto de un mismo resultado. No imponer una retícula adicional.

A partir de 700dp de ancho disponible, el trabajo ocupa la columna flexible y el diagnóstico una columna de 300dp, separados por `wide-columns`. Por debajo, ambos se apilan en una columna con divisor. Las acciones se ajustan con `FlowRow`: separación horizontal `control-gap`, vertical `compact`. No hay otros breakpoints propios.

Los botones de acción y las filas de interruptor tienen altura mínima de 56dp; el botón táctil de vidrio mide 60dp, y el acceso a historial 56dp con icono de 24dp. Los iconos pequeños usan 20dp o 24dp según el control; el de resultado usa 28dp y el de historial vacío 48dp. Las medidas son las observadas, no una nueva escala de iconos.

## Elevation & Depth

La lectura se apoya en color y espacio, sin tarjetas elevadas alrededor de cada bloque. El vidrio propio añade elevación suave de 6dp, borde luminoso de 1dp y radio `glass`. La sombra usa `ambientShadowColor=0x201C426E` y `spotShadowColor=0x301C426E`; estos valores nativos están en las extensiones del sidecar. El fondo no recibe esa sombra ni el borde.

En API33+, AGSL evalúa un campo estático compartido de base azul y manchas azul/cian. La lente desplaza la muestra junto al borde hasta 9dp, la mezcla con una película clara u oscura y suma brillo especular. El desplazamiento se concentra en una banda interior de 14dp; las coordenadas globales mantienen la continuidad entre fondo y controles. No captura otras vistas y no distorsiona el texto ni los iconos, dibujados después.

En API24–32, o si falla la creación de `RuntimeShader`, se usa el degradado lineal del frontmatter. Esta alternativa está implementada pero no se probó en emulador antiguo. Las capturas de `.impeccable/review` corresponden a API37, incluidas oscuro, texto ampliado y horizontal; no prueban un teléfono físico.

**The Shared Background Rule.** La lente y el fondo evalúan el mismo campo procedural en coordenadas globales. No atribuir a la lente refracción de otras vistas ni del contenido.

**The Honest Trace Rule.** La traza representa intervalos reales entre fotogramas. No convertirla en una animación ornamental.

## Shapes

El material de vidrio tiene esquinas amplias con el radio `glass`; la lente limita su radio calculado a la mitad de la dimensión menor. La forma de los botones rellenos, botones de contorno, campo delineado, interruptores y segmentos procede de Material3. Conservar `SegmentedButtonDefaults.itemShape` para inicio, centro y final de la selección. No convertir los defaults de la dependencia en tokens locales nuevos.

Los iconos son trazos vectoriales: sus originales están en `docs/recursos/iconos` y su ejecución Android usa VectorDrawable en `app/src/main/res/drawable`. No existe un raster de interfaz que deba conservarse o generarse.

## Components

### Buttons

- **Principal:** botón Material3 relleno con pareja `primary` / `on-primary`, altura mínima y relleno del token `button-primary`. Deshabilitado durante una ejecución incompatible.
- **Contorno:** `OutlinedButton`, altura mínima `button-outlined`; usa estados, borde y forma Material3. Cancelación solo se habilita mientras corresponde.
- **Texto:** `TextButton` para revelar pruebas o comparación; altura mínima `button-text`. La cancelación de una carga activa usa un mínimo local de 48dp.
- **Vidrio:** `BotonVidrio` conserva contenido nativo sobre `SuperficieVidrio`. Al presionar, escala a 0.96 y vuelve a 1 con `spring(dampingRatio=0.8, stiffness=600)`. Cuando se deshabilita, la capa completa usa alfa 0.45 y el botón nativo conserva su estado deshabilitado. El botón táctil usa `titleMedium`; el acceso a historial usa solo icono con descripción accesible.
- **Interacción:** ripple, foco y respuesta nativos de Material3; no hay un estado hover propio del producto. La animación respeta la escala de duración del sistema, incluida su reducción o desactivación. No hay duraciones propias de animación expresadas en milisegundos.

### Selection

`SingleChoiceSegmentedButtonRow` en vidrio. El segmento activo usa el contenedor primario con alfa 0.8 y su tinta correspondiente; los inactivos son transparentes con `onSurface`. Los bordes de segmento son transparentes y no se muestra icono de selección. El token de altura del selector es un mínimo, no una restricción para etiquetas ampliadas.

### Inputs / Fields

El límite para primos usa `OutlinedTextField` Material3 de una línea, ancho disponible, etiqueta persistente y teclado numérico. Color, forma, borde, foco y tipografía se heredan de Material3. Se bloquea mientras se calcula; un valor inválido impide iniciar mediante el estado del botón. No se inventa un estado visual de error que el campo no implementa.

### Options

Cada opción es una fila completa con `Row.toggleable(role=Role.Switch)` y un `Switch` sin acción independiente (`onCheckedChange=null`). Texto y estado forman un único control accesible. Su altura es mínima y se deshabilita durante la carga. La apertura de opciones y comparación usa `AnimatedVisibility` con defaults de Compose, sin una duración de tarea convertida en transición.

### Result and history

Resultados legibles sin tarjeta: icono de confirmación, `result-headline` y detalle secundario. Historial con filas, títulos `titleMedium`, resumen `bodyLarge`, tiempo/fecha `bodySmall` y divisor `outlineVariant`. La pantalla vacía usa un icono ilustrativo vectorial y una instrucción breve.

### Measured trace

`Canvas` de 70dp de alto con trazo primario de 2dp y guía `outlineVariant` de 1dp. Solo aparece con más de una muestra; normaliza la escala vertical con un máximo mínimo de 33ms. Es una visualización medida, no un indicador decorativo. Su descripción accesible identifica intervalos entre fotogramas en milisegundos.

El sidecar contiene siete ejemplos HTML/CSS autónomos para el panel. Son traducciones estáticas de referencia (1dp se representa como 1px de CSS), no componentes de producción ni simulaciones de AGSL. Usan la alternativa de degradado y foco del navegador. Estados, semántica y renderizado reales se verifican en Compose.

## Do's and Don'ts

### Do:

- Do conservar las parejas de color del tema activo y el escalado de texto del sistema.
- Do usar el vidrio en controles y mantener el contenido textual nítido.
- Do conservar las áreas mínimas y el ajuste de acciones con FlowRow.
- Do asociar cada interruptor a su etiqueta en un único nodo accionable con Role.Switch.
- Do respetar la reducción de animaciones del sistema y medir el comportamiento real antes de afirmar fluidez.

### Don't:

- Don't sustituir Typography() por una fuente de exhibición o un tamaño fijo en píxeles.
- Don't describir la lente como un desenfoque o una captura de vistas subyacentes.
- Don't registrar las esperas de tareas, timeout o reintentos como duraciones de animación.
- Don't convertir los ejemplos HTML del panel en autoridad sobre la implementación Compose.
- Don't afirmar validación del fallback en API24–32 ni de teléfono físico: la evidencia disponible corresponde al emulador API37.

No se canonizan omisiones del procedimiento: no hubo semilla de concepto ni tarjeta QUALITY BAR. Tampoco se convierte la prueba del árbol semántico en una auditoría auditiva de TalkBack. El alcance y las correcciones están registrados en `.impeccable/review/PROCESO.md`.
