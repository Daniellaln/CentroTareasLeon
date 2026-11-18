# Verificación de fase 1

Windows, JDK21, Pixel_8_Pro_2 API37. Compilación de cada avance antes de publicar.5 pruebas unitarias y8 instrumentadas correctas, sin errores de lint. Resultados JUnit en pruebas/.

Se probaron valores de primos100→25,1000→168,10000→1229,100000→9592 y500000→41538, cancelación cooperativa de un cálculo grande, datos y duraciones simuladas y error IOException.

Las pruebas Android comprueban estados de botones, toques durante el progreso, cancelación/reinicio, final100%, primos y cancelación, entrada inválida, superposición real de las cargas en un Repository controlado, error/reintento y cancelación sin convertirse en error.

El recorrido de la app normal comprobó progreso y contador, cancelación, rotación durante tarea y cargas con las esperas reales. Mediciones capturadas: {"progreso_y_toques": true, "cancelacion": true, "rotacion_conserva_tarea": true, "primos_5000000": "Tiempo: 2571 ms", "secuencial": "Tiempo: 5013 ms", "paralelo": "Tiempo: 3006 ms", "error_y_reintento": true, "reintento": "Tiempo: 3001 ms", "fuente_tiempos": "Valores cotejados visualmente en capturas 08,09 y11; se corrigió la extracción que confundía el tiempo de primos con el de cargas"}.

El caso bloqueante es intencional y está únicamente en su avance histórico. Los logs HILO y capturas proceden del emulador. Una imagen estática por sí sola no demuestra bloqueo; se comprobó además la respuesta al tocar y el registro de hilos al corregirlo.

No se probó en Mac, hardware físico ni otra API. La app no usa red ni servicios externos. Las pruebas de cargas de UI usan repositorios controlados; las capturas del recorrido usan el Repository de producción.
