# Recursos visuales

Los cinco iconos en iconos/ fueron dibujados como SVG y se usan en Android mediante sus VectorDrawable equivalentes en app/src/main/res/drawable.

La imagen app/src/main/res/drawable-nodpi/cintas_vidrio.png se generó con la herramienta integrada de imágenes de Codex. El prompt completo está en cintas_vidrio.prompt.txt y también dentro de los metadatos PNG. Representa dos recorridos de vidrio que se entrelazan; es ilustración del fondo, no una medición ni una captura real del sistema. nodpi conserva los píxeles y la app controla la escala.

La textura se comparte con las lentes AGSL para que el vidrio muestree el mismo fondo y la misma ilustración. La tipografía, los datos, los marcadores de proceso y los botones se dibujan en código. Las27 comprobaciones funcionales y el movimiento no dependen de la imagen.

Referencia de la API gráfica: [RuntimeShader de Android](https://developer.android.com/reference/android/graphics/RuntimeShader), en particular setInputShader y el muestreo eval del BitmapShader. La alternativa API24–32 conserva degradados y la ilustración, sin prometer refracción AGSL.
