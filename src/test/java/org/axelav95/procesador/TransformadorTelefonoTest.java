package org.axelav95.procesador;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba unitaria para la transformación de teléfono.
 */
class TransformadorTelefonoTest {

    //Se crea un test para verificar la transformación simple con letras
    @Test
    void testTransformarNumero_SimpleConLetras() {
        // 800-TEST -> 8008378
        // Relleno: 80083780000
        assertEquals("80083780000", TransformadorTelefono.transformarNumero("800-TEST"));
    }
    //Se crea un test para verificar la transformación con relleno
    @Test
    void testTransformarNumero_Relleno() {
        // 123 -> 123
        // Relleno: 12300000000
        assertEquals("12300000000", TransformadorTelefono.transformarNumero("123"));
    }
    //Se crea un test para verificar la transformación con truncado
    @Test
    void testTransformarNumero_Truncado() {
        // 1234567890123 (13 dígitos)
        // Truncado: 12345678901
        assertEquals("12345678901", TransformadorTelefono.transformarNumero("1234567890123"));
    }
    //Se crea un test para verificar la transformación con longitud exacta
    @Test
    void testTransformarNumero_LongitudExacta() {
        assertEquals("12345678901", TransformadorTelefono.transformarNumero("12345678901"));
    }
    //Se crea un test para verificar la transformación ignorando símbolos
    @Test
    void testTransformarNumero_IgnoraSimbolos() {
        // (555) JAVA-NOW! -> 5555282669
        // Relleno: 55552826690
        assertEquals("55552826690", TransformadorTelefono.transformarNumero("(555) JAVA-NOW!"));
    }
    //Se crea un test para verificar la transformación con entrada nula y vacía
    @Test
    void testTransformarNumero_NuloYVacio() {
        assertNull(TransformadorTelefono.transformarNumero(null));
        assertEquals("00000000000", TransformadorTelefono.transformarNumero("")); // Rellena 0s
    }
}