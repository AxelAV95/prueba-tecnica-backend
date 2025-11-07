package org.axelav95.procesador;

import java.util.Map;

/**
 * Aquí se aplica toda la lógica para transformar un número de teléfono
 * que puede contener letras y otros caracteres en un formato numérico 
 */
public class TransformadorTelefono {

    //decidí usar un Map para mayor claridad, ya que es más fácil de leer y mantener,
    //además que permite una fácil modificación en caso de que se requieran cambios en la lógica
    private static final Map<Character, Character> TECLADO = Map.ofEntries(
            Map.entry('A', '2'), Map.entry('B', '2'), Map.entry('C', '2'),
            Map.entry('D', '3'), Map.entry('E', '3'), Map.entry('F', '3'),
            Map.entry('G', '4'), Map.entry('H', '4'), Map.entry('I', '4'),
            Map.entry('J', '5'), Map.entry('K', '5'), Map.entry('L', '5'),
            Map.entry('M', '6'), Map.entry('N', '6'), Map.entry('O', '6'),
            Map.entry('P', '7'), Map.entry('Q', '7'), Map.entry('R', '7'), Map.entry('S', '7'),
            Map.entry('T', '8'), Map.entry('U', '8'), Map.entry('V', '8'),
            Map.entry('W', '9'), Map.entry('X', '9'), Map.entry('Y', '9'), Map.entry('Z', '9')
    );

    // este metodo permite transformar el número de teléfono,
    //basicamente lo que hace es recorrer cada caracter del string de entrada
    //y dependiendo si es dígito o letra, lo convierte al formato numérico
    public static String transformarNumero(String input) {
        if (input == null) return null;

        StringBuilder numericString = new StringBuilder(); //auxiliar para construir el número

        //aqui procedo a recorrer cada caracter del string de entrada y transformarlo
        for (char c : input.toUpperCase().toCharArray()) {
            if (Character.isDigit(c)) { // si es dígito, se añade directamente
                numericString.append(c);
            } else if (TECLADO.containsKey(c)) { //sino, si es letra, se convierte usando el mapa
                numericString.append(TECLADO.get(c));
            }            
        }

        String numero = numericString.toString(); // se obtiene el número transformado
        int length = numero.length(); //se saca el tamaño del número

        if (length == 11) { // evaluo si ya tiene la longitud correcta
            return numero;
        } else if (length > 11) { //si es más largo
            // trunca si es más largo
            return numero.substring(0, 11); //aqui lo trunco a 11 caracteres
        } else {
            // aqui relleno con 0s a la derecha si es más corto
            return String.format("%-11s", numero).replace(' ', '0');
        }
    }
}