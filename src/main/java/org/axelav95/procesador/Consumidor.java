package org.axelav95.procesador;

import org.axelav95.cola.ColaBloqueante;

/**
 * Aqui se implementa el hilo consumidor que basicamente lo que hace es 
 * sacar datos de la cola y procesarlos.
 */
public class Consumidor implements Runnable {

    private final ColaBloqueante<String> queue; // se establece final porque no cambia

    public Consumidor(ColaBloqueante<String> queue) {
        this.queue = queue; //se recibe la cola en el constructor
    }

    //implementación del método run
    @Override
    public void run() {
        try {
            // corre "infinitamente" hasta que el programa sea interrumpido
            while (!Thread.currentThread().isInterrupted()) {

                // Se obtiene el dato de la cola
                String rawPhone = queue.desencolar();

                // Se procesa el dato (transformación del número de teléfono)
                String processedPhone = TransformadorTelefono.transformarNumero(rawPhone);

                //se muestra el resultado
                System.out.println("-> Procesado: " + rawPhone + " => " + processedPhone);

                // se simula tiempo de procesamiento
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) { // aqui es importante, en caso de interrupción
            Thread.currentThread().interrupt();
            System.out.println("Consumidor interrumpido.");
        }
    }
}