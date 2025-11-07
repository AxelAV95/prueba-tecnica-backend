package org.axelav95.cola;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para ColaBloqueante
 */
class ColaBloqueanteTest {

    // ColaBloqueante a probar
    private ColaBloqueante<String> queue;
    
    // Inicialización antes de cada test
    @BeforeEach
    void setUp() {
        // Usamos capacidad 3 para pruebas más fáciles
        queue = new ColaBloqueante<>(3);
    }
    //Se crea un test para verificar el encolado y desencolado simple
    @Test
    void testEncolarYDesencolarSimple() throws InterruptedException {
        assertTrue(queue.encolar("Test-1"));
        assertEquals(1, queue.getSize());

        String item = queue.desencolar();

        assertEquals("Test-1", item);
        assertEquals(0, queue.getSize());
    }

    //Se crea un test para verificar que no se aceptan duplicados
    @Test
    void testRechazaDuplicados() throws InterruptedException {
        assertTrue(queue.encolar("Elemento-Unico"));
        assertEquals(1, queue.getSize());

        assertFalse(queue.encolar("Elemento-Unico"));
        assertEquals(1, queue.getSize()); // El tamaño no debe cambiar
    }

    //Se crea un test para verificar la capacidad y el orden FIFO
    @Test
    void testCapacidadYOrdenFIFO() throws InterruptedException {
        // Capacidad es 3
        queue.encolar("A");
        queue.encolar("B");
        queue.encolar("C");
        assertEquals(3, queue.getSize());

        // Sacamos 'A' (FIFO - First In, First Out)
        assertEquals("A", queue.desencolar());
        assertEquals(2, queue.getSize());

        // Ahora hay espacio para 'D'
        assertTrue(queue.encolar("D"));
        assertEquals(3, queue.getSize());

        // Verificamos el orden de salida
        assertEquals("B", queue.desencolar());
        assertEquals("C", queue.desencolar());
        assertEquals("D", queue.desencolar());
        assertEquals(0, queue.getSize());
    }
}