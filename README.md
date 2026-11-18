# CentroTareas

Daniella Leon · Laboratorio 14

Proyecto base reconstruido a partir de los contratos y capturas del documento del docente; no se adjuntó un proyecto base ejecutable. Kotlin, Compose, Material 3 y MVVM con Repository.

Esta implementación se prepara con asistencia de Codex. La guía distingue una fase 1 sin IA y una fase 2 con Gemini; no se afirma haber cumplido esa condición de autoría sin asistencia.

Abre esta carpeta en Android Studio con JDK21 y SDK37. No necesita Internet, permisos ni Firebase para funcionar. Las dependencias se descargan al sincronizar.

Avance inicial: pantalla con tres secciones; las funciones se incorporan en los siguientes commits.

## Observación del Paso 1

Durante la espera de cuatro segundos la pantalla no responde y el contador no se actualiza. Trabajando... no llega a dibujarse porque el hilo principal está ocupado. Si el bloqueo se prolonga, Android puede mostrar que la app no responde (ANR); el momento exacto depende del sistema.
