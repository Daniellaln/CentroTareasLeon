# Alcance del cierre visual

Dirección fijada por Daniella: azul y vidrio claro, fondo blanco frío, una tarea visible e historial aparte. Implementación nativa desde código. No hubo sorteo/semilla de concepto ni tarjeta QUALITY BAR; se conserva esta omisión del procedimiento, sin inventar aprobación retrospectiva. La revisión se limita al contrato confirmado y las convenciones Android observables.

No hubo comp aprobado ni detector HTML/CSS, porque el artefacto es Compose. Diez capturas obligatorias de Pixel_8_Pro_2 API37, incluyendo oscuro, texto1.3 y horizontal. No hay evidencia de teléfono físico ni de API24–32.

Primera revisión independiente: disposición fix. Se mantiene el diseño; se solicita documentar el alcance anterior y asociar semánticamente los tres interruptores con sus etiquetas. Se aplica Row.toggleable con Role.Switch y Switch sin acción independiente, más una prueba Android de nombre, acción, estado y bloqueo durante la carga. La prueba del árbol semántico no equivale a una auditoría auditiva completa de TalkBack.

Dictamen posterior: **ship**, limitado a los dos hallazgos puntuados. Ambos fueron resueltos. El revisor abrió las diez recapturas y comprobó la prueba específica aprobada en TEST-Android.xml; no observó regresiones materiales del lote. El dictamen no certifica una tarjeta de calidad ausente ni amplía el alcance a otras APIs o a hardware físico.
