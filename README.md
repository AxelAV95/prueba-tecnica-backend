# Prueba técnica - Backend (Java)

## Clonar el repositorio

Clona el repositorio localmente con el siguiente comando (reemplaza <REPO_URL> por la URL real del repositorio):

```cmd
git clone https://github.com/AxelAV95/prueba-tecnica-backend.git
cd "prueba-tecnica-backend"
```

## Descripción

Este repositorio contiene una solución para una prueba técnica de backend implementada en Java. El objetivo principal fue implementar desde cero una cola (queue) con comportamiento bloqueante y su uso mediante un Productor y un Consumidor que interactúan concurrentemente. También se incluyeron pruebas unitarias para validar la funcionalidad.

La tarea consistía en:
- Implementar una cola con capacidad fija (10 elementos) y operaciones seguras para múltiples hilos.
- Implementar un Productor que inyecte números telefónicos (con texto) en la cola.
- Implementar un Transformador que convierta cadenas con texto (ej. `800-TEST`) en números telefónicos (ej. `800-8337-7778`) siguiendo las reglas de la consigna.
- Implementar un Consumidor que procese los elementos de la cola.
- Agregar pruebas unitarias.

## Requisitos

- Java 11 o superior instalado y disponible en PATH.
- Maven 3.x instalado.

## Estructura relevante del proyecto

- `src/main/java/org/axelav95/cola/ColaBloqueante.java` - Implementación de la cola bloqueante con capacidad 10.
- `src/main/java/org/axelav95/cola/Nodo.java` - Nodo usado por la cola (estructura interna).
- `src/main/java/org/axelav95/procesador/Productor.java` - Productor que inserta números/texto en la cola.
- `src/main/java/org/axelav95/procesador/Consumidor.java` - Consumidor que extrae y procesa elementos de la cola.
- `src/main/java/org/axelav95/procesador/TransformadorTelefono.java` - Lógica para transformar texto a número telefónico.
- `src/main/java/org/axelav95/Main.java` - Punto de entrada (ejecutor simple / demo).
- `src/test/java/...` - Pruebas unitarias (JUnit) para la cola y el transformador.

## Enfoque y decisiones de diseño

- Cola bloqueante propia: se implementó una estructura de cola con capacidad fija (10) para evaluar las operaciones básicas (enqueue, dequeue, peek) y la gestión de concurrencia. Se priorizó simplicidad, corrección y comportamiento predecible.
- Sincronización: La cola fue diseñada para ser segura en entornos multi-hilo. Se usaron primitivas de sincronización (p. ej. `synchronized` + `wait`/`notify`) o mecanismos equivalentes para manejar bloqueos cuando la cola está llena o vacía.
- Productor/Consumidor: El productor inserta entradas (números con texto) y el consumidor las transforma usando `TransformadorTelefono` y luego las procesa. Ambos corren en hilos separados y respetan la semántica bloqueante de la cola.
- Reglas de transformación de teléfono (según la consigna):
  - La palabra `TEST` en el ejemplo `800-TEST` debe transformarse a `8337` (mapeo de letras a dígitos estilo teléfono), resultando en `800-8337-7778` según la consigna original.
  - El número telefónico final debe tener exactamente 11 posiciones. Si tiene menos, se rellena con ceros (`0`). Si tiene más, se trunca el excedente.
- Validaciones: Se realizaron validaciones razonables (nulos, longitudes, formato básico) y se documentaron las asunciones en el código.

## Conceptos y fundamentos usados

- Concurrencia en Java: manejo de hilos, sincronización y coordinación entre productor y consumidor.
- Estructuras de datos: implementación de una cola (FIFO) con capacidad limitada.
- Diseño orientado a pruebas: se agregaron pruebas unitarias para cubrir el comportamiento esperado de la cola y del transformador.
- Principios de robustez: validación de entradas, manejo de condiciones límite (cola llena/vacía), y saneamiento/truncamiento de datos.

## Cómo compilar y ejecutar (Windows - cmd.exe)

1. Abrir un terminal (cmd.exe) en la raíz del proyecto (la carpeta que contiene `pom.xml`).

2. Compilar el proyecto con Maven:

```cmd
mvn clean package
```

3. Ejecutar las pruebas unitarias:

```cmd
mvn test
```

4. Ejecutar la aplicación (si `Main` es el punto de entrada y no hay dependencias empaquetadas externamente):

```cmd
rem Ejecuta la clase Main (suponiendo que no requiere dependencias externas fuera de target/classes)
java -cp target/classes org.axelav95.Main
```

Nota: si el proyecto usa dependencias externas y quieres ejecutar el JAR empaquetado, puedes crear uno "fat jar" o ejecutar con el plugin apropiado de Maven; la forma anterior funciona para aplicaciones self-contained sin dependencias externas o en entornos donde `target/classes` es suficiente.

## Ejecutar en IDE

- Importa el proyecto como un proyecto Maven en IntelliJ IDEA, Eclipse o VS Code (con extensiones Java/Maven).
- Ejecuta `Main` directamente desde el IDE o usa las metas de Maven indicadas arriba.

## Pruebas unitarias

- Las pruebas están en `src/test/java/org/axelav95/...`.
- Para ejecutarlas desde la línea de comandos:

```cmd
mvn test
```

Se incluyeron tests que cubren:
- Comportamiento de la `ColaBloqueante` (enqueue/dequeue, límites de capacidad, condiciones de bloqueo en escenarios simulados).
- Transformación correcta en `TransformadorTelefono` para ejemplos típicos y casos límite (pad/truncation).

## Suposiciones y límites

- Se asumió capacidad fija de 10 para la cola (tal como pide la consigna).
- El mapeo de letras a dígitos telefónicos fue implementado siguiendo las reglas típicas de teclado telefónico (2-ABC, 3-DEF, etc.), y los ejemplos indicados en la consigna.
- Para simplificar la demostración, el `Main` puede lanzar hilos productores/consumidores con una cantidad de interacciones (p. ej. 10-20) y mostrar logs por consola.


## Resumen de lo realizado

Se implementó una cola bloqueante con capacidad 10, un productor que inserta números con texto, un transformador que convierte texto a dígitos telefónicos bajo las reglas pedidas, y un consumidor que procesa los valores. Se añadieron pruebas unitarias para validar la lógica crítica.

