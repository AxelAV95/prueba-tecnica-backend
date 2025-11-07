package org.axelav95;

import org.axelav95.cola.ColaBloqueante;
import org.axelav95.procesador.Consumidor;
import org.axelav95.procesador.Productor;

/**
 * Aquí se orquesta la simulación del problema Productor/Consumidor
 */
public class Main {

    public static void main(String[] args) {

        // Primero creo una cola bloqueante con capacidad limitada
        ColaBloqueante<String> queue = new ColaBloqueante<>(10);

        // se procede a crear unas 20 interacciones
        Productor tareaProductor = new Productor(queue, 20);
        Consumidor tareaConsumidor = new Consumidor(queue);

        // creo los hilos para el productor y consumidor y la concurrencia
        Thread hiloProductor = new Thread(tareaProductor, "Productor-1");
        Thread hiloConsumidor = new Thread(tareaConsumidor, "Consumidor-1");

        // inicio la simulación
        System.out.println("Iniciando simulación Productor/Consumidor...");
        hiloProductor.start();
        hiloConsumidor.start();

        try {
            // aqui se procede a esperar a que el productor termine
            hiloProductor.join();
            System.out.println(">>> Productor ha terminado de generar datos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}