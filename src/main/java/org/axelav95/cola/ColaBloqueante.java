package org.axelav95.cola;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Esta es una cola mejorada y basada en ejercicios prácticos que hice en la universidad
 * para mas referencia, visitar el drive del código: 
 * https://drive.google.com/drive/u/6/folders/12cufyG-PJdkAmwKrQHqjJHx-xrrHjQwX
 * 
 * En si esta clase se han añadido las siguientes características:
 * 1. Capacidad fija: La cola tiene un límite máximo de elementos.
 * 2. Elementos únicos: No se permiten elementos duplicados.
 * 3. Bloqueo en encolar/desencolar: Si la cola está llena o vacía, los hilos
 *    se bloquean hasta que puedan proceder.
 * 4. Uso de wait() y notifyAll() para gestionar la sincronización entre hilos.
 * 5. Mensajes en consola para seguimiento de operaciones y estados.
 * 6. Métodos sincronizados para asegurar la integridad de los datos.
 * 7. También la hice genérica para poder usar cualquier tipo de dato.
 * 8. Es una cola bloqueante porque usa wait y notifyAll para manejar
 *    situaciones de espera cuando la cola está llena o vacía.
 * 
 * En resumen, esta clase es una implementación de una cola bloqueante
 * con capacidad fija y que no permite elementos duplicados. 
 */
public class ColaBloqueante<T> {

    private Nodo<T> principio; //permite mantener referencia al primer nodo
    private Nodo<T> fin; //permite mantener referencia al último nodo
    private final int capacidad; //capacidad máxima de la cola
    private int tamanio; //tamaño actual de la cola
    private final Set<T> elementosUnicos; //para rastrear elementos únicos

    public ColaBloqueante(int capacidad) {
        this.principio = null;
        this.fin = null;
        this.capacidad = capacidad;
        this.tamanio = 0;
        this.elementosUnicos = new HashSet<>(capacidad);
    }

    /**
     * Añade un elemento
     * Se bloquea si la cola está llena
     * Rechaza si el elemento es duplicado
     */
    public synchronized boolean encolar(T dato) throws InterruptedException {
        // se espera si la cola está LLENA
        while (this.tamanio == this.capacidad) {
            System.out.println("Cola LLENA. Hilo (" + Thread.currentThread().getName() + ") esperando...");
            wait(); // se libera el lock y se duerme
        }

        //se corrobora que el elemento no sea duplicado
        if (elementosUnicos.contains(dato)) {
            System.out.println("Rechazado (duplicado): " + dato);
            return false;
        }

        // logica para encolar
        Nodo<T> nuevo = new Nodo<>(dato);
        if (this.principio == null) {
            this.principio = nuevo;
            this.fin = nuevo;
        } else {
            this.fin.setSiguiente(nuevo);
            this.fin = nuevo;
        }

        // posteriormente se actualiza el estado
        this.tamanio++;
        this.elementosUnicos.add(dato);

        System.out.println("Encolado: " + dato + " (Tamaño: " + this.tamanio + ")");

        // Aqui se despiertan a los hilos consumidores
        notifyAll();

        return true;
    }

    /**
     * Saca un elemento
     * Se bloquea si la cola está vacía
     */
    public synchronized T desencolar() throws InterruptedException {
        // Aqui igual se espera si la cola está VACÍA
        while (this.tamanio == 0) {
            System.out.println("Cola VACÍA. Hilo (" + Thread.currentThread().getName() + ") esperando...");
            wait(); // Libera el lock y se duerme
        }

        //Posteriormente se procede a desencolar
        T dato = this.principio.getDato();
        this.principio = this.principio.getSiguiente();
        if (this.principio == null) {
            this.fin = null;
        }

        // Procedo a actualizar el estado
        this.tamanio--;
        this.elementosUnicos.remove(dato);

        System.out.println("Desencolado: " + dato + " (Tamaño: " + this.tamanio + ")");

        // Y luego se despiertan a los hilos productores
        notifyAll();

        return dato;
    }

    // Método para obtener el tamaño actual de la cola, para pruebas o monitoreo
    public synchronized int getSize() {
        return this.tamanio;
    }
}