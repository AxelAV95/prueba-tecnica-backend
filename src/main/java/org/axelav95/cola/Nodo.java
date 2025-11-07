package org.axelav95.cola;

/**
 * Esta clase igualmente está basdda en ejercicios prácticos de la universidad
 * para más referencia, visitar el drive del código: https://drive.google.com/drive/u/6/folders/12cufyG-PJdkAmwKrQHqjJHx-xrrHjQwX
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}