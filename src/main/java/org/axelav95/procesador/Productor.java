package org.axelav95.procesador;

import org.axelav95.cola.ColaBloqueante;

/**
 * Aqui se implementa el hilo productor que básicamente lo que hace es
 * añadir datos a la cola.      
 */
public class Productor implements Runnable {

    private final ColaBloqueante<String> cola; // se establece final porque no cambia
    private final int interacciones; // número de interacciones que realizará el productor

    public Productor(ColaBloqueante<String> cola, int interacciones) {
        this.cola = cola;
        this.interacciones = interacciones; // Esta seria la cantidad de telefonos a producir
    }

    //se implementa el método run del hilo
    @Override
    public void run() {
        String[] testData = {"800-TEST", "555-JAVA", "123-CODE", "800-TEST", "999-QUEUE", "ABC-HOLA"};

        try {
            for (int i = 0; i < interacciones; i++) {
                // aqui se añade i para hacer cada teléfono único
                String phoneText = testData[i % testData.length] + "-" + i;

                cola.encolar(phoneText); /// se añade el dato a la cola

                // simula tiempo de trabajo
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { // aqui es importante, en caso de interrupción
            Thread.currentThread().interrupt();
            System.out.println("Productor interrumpido.");
        }
    }
}