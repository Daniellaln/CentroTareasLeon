# Verificación de fase2

Windows, JDK21, Pixel_8_Pro_2 / API37. Las funciones se compilaron antes de publicar cada avance. La rama main permanece en el cierre de fase1.

## Resultado

8 pruebas unitarias y14 instrumentadas: cero fallos, errores y omisiones. JUnit y resumen en pruebas_fase2/. Compilación debug correcta y lint final con cero errores; conserva advertencias de dependencias y targetSDK, sin ocultarlas con un baseline.

Se comprobó Flow de progreso, primos y referencias numéricas, cancelación cooperativa, cargas simultáneas frente a secuenciales, error y reintento, límite opcional de2segundos, cuatro intentos máximos y cancelación en las esperas. La comparación solo agrega cargas exitosas. DataStore conserva cinco resultados y el medidor excluye la pausa de segundo plano.

La revisión final pidió unir etiqueta, acción y estado de cada interruptor. Se corrigió con una fila toggleable y Role.Switch, manteniendo el Switch nativo como indicador. Una prueba adicional comprueba los tres nombres, acción de toda la fila, estado y bloqueo durante la carga. La ejecución completa posterior pasó8 pruebas unitarias y14 Android; registro verificacion_final.txt. Es una comprobación del árbol semántico, no una auditoría auditiva completa de TalkBack.

La primera ejecución de las pruebas del rediseño detectó una lectura de la lista que podía vaciarse entre composición y dibujo. Se corrigió tomando una lista estable por composición y las13 pruebas pasaron. Esa ejecución aún tuvo un error de lint por la lectura no observable del idioma; se corrigió con LocalConfiguration y la compilación/lint posterior pasó. Los registros separados conservan esa secuencia: pruebas_android.txt y compilacion_lint.txt. No se presenta un log fallido como una compilación correcta.

## Recorrido de la app

Las capturas usan el Repository de producción, con sus esperas reales simuladas. Se pulsó Tócame durante el progreso, se canceló y reinició, se calcularon168 primos hasta1000 y348513 hasta5000000. La tabla muestra mediciones alrededor de5s secuenciales y3s paralelos. Los valores exactos se ven en las capturas de cada ejecución; son observaciones de ese equipo, no tiempos impuestos a la UI.

El límite de2segundos mostró Tardó demasiado; después se activaron error y reintento, se canceló la carga y se recuperó con las opciones apagadas. Se cerró el proceso y reabrió el historial con los cinco resultados conservados. La recaptura11 espera a que termine la transición de la tabla. El JSON del recorrido y las imágenes están en capturas_fase2/.

Se revisaron tema claro, oscuro, letra1.3 y horizontal de dos columnas desplazables. Los controles siguen siendo nativos. El vidrio usa el mismo fondo procedural con refracción de bordes en API33+, y degradado de respaldo anterior.

## Alcance

No se probó en Mac, teléfono físico ni API24–32; el respaldo antiguo se revisó en código. El medidor de fotogramas es didáctico: las mediciones del emulador dependen del equipo y no miden directamente la latencia del toque. Las cargas son ficticias, no una conexión de red.

Las pruebas y recursos están dentro de la repo. No se incluyen cachés, local.properties ni claves. La fase1 mantiene sus propias capturas y evidencia en main y en pruebas/.
