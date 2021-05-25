/**
 * @author Angel Higueros - 20460
 * @version 1.0
 */

public class Vista {

    /**
     * Imprime un mensaje sin salto de linea
     * 
     * @return void
     * @param mensaje
     */
    public void print(String mensaje) {
        System.out.print(mensaje);
    }

    /**
     * Imprime un mensaje con salto de linea
     * 
     * @return void
     * @param mensaje
     */
    public void printLn(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Imprime el menu principal
     * 
     * @return void
     * @param -
     */
    public void menu() {

        printLn(" :: CENTRO DE RESPUESTA COVID-19 :: ");
        printLn(" 1. Ver ruta mas corta entre ciudades");
        printLn(" 2. Ver nombre ciudad del centro ");
        printLn(" 3. Modificar ruta ");
        printLn(" 4. Salir");

    }

}
