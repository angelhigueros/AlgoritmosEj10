import java.io.IOException;

/**
 * @author Angel Higueros  - 20460
 * @version 1.0
 */

public class main {

    public static void main(String[] args) throws IOException {

        // Generar vista y controlador
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);

        // llamar a las funciones principales del programa
        controlador.generarGrafo();
        controlador.implementacion();

    }
}
