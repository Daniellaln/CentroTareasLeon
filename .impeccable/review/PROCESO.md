# Alcance del cierre visual

Dirección fijada por Daniella: azul y vidrio claro, fondo blanco frío, una tarea visible e historial aparte. Implementación nativa desde código. No hubo sorteo/semilla de concepto ni tarjeta QUALITY BAR; se conserva esta omisión del procedimiento, sin inventar aprobación retrospectiva. La revisión se limita al contrato confirmado y las convenciones Android observables.

No hubo comp aprobado ni detector HTML/CSS, porque el artefacto es Compose. Diez capturas obligatorias de Pixel_8_Pro_2 API37, incluyendo oscuro, texto1.3 y horizontal. No hay evidencia de teléfono físico ni de API24–32.

Primera revisión independiente: disposición fix. Se mantiene el diseño; se solicita documentar el alcance anterior y asociar semánticamente los tres interruptores con sus etiquetas. Se aplica Row.toggleable con Role.Switch y Switch sin acción independiente, más una prueba Android de nombre, acción, estado y bloqueo durante la carga. La prueba del árbol semántico no equivale a una auditoría auditiva completa de TalkBack.

Dictamen posterior: **ship**, limitado a los dos hallazgos puntuados. Ambos fueron resueltos. El revisor abrió las diez recapturas y comprobó la prueba específica aprobada en TEST-Android.xml; no observó regresiones materiales del lote. El dictamen no certifica una tarjeta de calidad ausente ni amplía el alcance a otras APIs o a hardware físico.

## Refuerzo pedido por Daniella

Después de ese cierre, Daniella pidió más presencia visual y animaciones de los procesos para explicar corrutinas. Se integró la ilustración generada con procedencia incrustada, el progreso circular real, los recorridos secuencial/paralelo, el candidato de primos y el control fijo del hilo principal. Una revisión fresca abrió las15 vistas del nuevo alcance y encontró un fallo: el marcador podía conservar posición del intento anterior. Se corrige con identidades de ejecución y key(ejecucion,intento). La prueba Android reintentoNoHeredaElMarcadorAnteriorYMantieneAnimacion verifica el reinicio0.8→0.01 y la interpolación posterior hacia0.5 en el mismo intento. Las9 pruebas unitarias y18 Android pasan, con build/lint sin errores. Se recaptura la misma matriz y la grabación real para el dictamen limitado al hallazgo.

Dictamen de este lote: **ship**, con el arrastre del marcador resuelto. Las15 capturas fueron validadas, incluida la recaptura horizontal2992×1344. El segundo intento muestra ambas pistas al inicio y textos0%. La prueba Android consta aprobada en JUnit. No se observaron regresiones materiales del lote; el dictamen no certifica perceptualmente el video completo.
