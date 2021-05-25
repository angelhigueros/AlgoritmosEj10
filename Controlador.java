import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author Angel Higueros  - 20460
 * @version 1.0
 */

public class Controlador {

    // Utilidades
    Vista vista;
    Scanner sc = new Scanner(System.in);
    Scanner scInt = new Scanner(System.in);
    Grafo grafo;

    // Archivo
    String src = "./guategrafo.txt";
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    // Rutas
    ArrayList<String> ciudades = new ArrayList<>();
    ArrayList<String> rutas = new ArrayList<>();

    public Controlador(Vista vista) {
        this.vista = vista;
    }

    /**
     * Inicia la implementación del programa
     * 
     * @return void
     * @param -
     */
    public void implementacion() throws IOException {

        Boolean estado = true;
        while (estado) {
            vista.menu();
            int opcion = scInt.nextInt();

            if (opcion == 1) {
                rutaMasCorta();
            } else if (opcion == 2) {
                ciudadCentroGrafo();
            } else if (opcion == 3) {
                modificarRuta();
            } else if (opcion == 4) {
                vista.printLn("[OK] Finalizando programa ....");
                estado = false;
            } else {
                vista.printLn("[Error] Ingrese un numero valido");
            }
        }

    }

    /**
     * Calcula el camino mas corto entre dos rutas
     * 
     * @return void
     * @param -
     */
    public void rutaMasCorta() {

        vista.printLn("\n :: RUTA MAS CORTA ::\n");

        vista.printLn("-> Ingrese la ciudad de origen: ");
        String origen = sc.nextLine().toLowerCase();

        vista.printLn("-> Ingrese la ciudad de destino: ");
        String destino = sc.nextLine().toLowerCase();

        // Compara cada ciudad con las demas ciudades
        for (int i = 0; i < ciudades.size(); i++) {
            for (int j = 0; j < ciudades.size(); j++) {
                if (i > j) {

                    // Mira si el origen y el destino son iguales a los indicados
                    if ((origen.equals(grafo.getNombre(i).toLowerCase())
                            && destino.equals(grafo.getNombre(j).toLowerCase()))
                            || (destino.equals(grafo.getNombre(i).toLowerCase())
                                    && origen.equals(grafo.getNombre(j).toLowerCase()))) {

                        // Muestra el camino más corto
                        System.out.println("\nEl camino mas corto de " + grafo.getNombre(i) + " a " + grafo.getNombre(j)
                                + " es: \n" + grafo.caminocorto(i, j) + " y con: " + grafo.pesominimo(i, j)
                                + " kms \n");
                    }

                }
            }
        }

    }

    /**
     * Indica cual es el centro del grafo
     * 
     * @return void
     * @param -
     */
    public void ciudadCentroGrafo() {
        vista.printLn("\n :: Ciudad central ::\n");
        System.out.println("La ciudad central es " + grafo.centro() + "\n");

    }

    /**
     * Modifica cualquier ruta del archivo de rutas
     * 
     * @return void
     * @param -
     */
    public void modificarRuta() throws IOException {

        Boolean estado = true;

        while (estado) {

            Writer output;
            Writer output2;

            vista.printLn("\n :: MODIFICAR RUTA ::\n");
            vista.printLn("1. Reportar interrupcion de trafico ");
            vista.printLn("2. Ingresar nueva conexion");
            vista.printLn("3. Regresar");

            Integer opcion = scInt.nextInt();

            // Modificar ruta por interrupcuón en el trafico
            if (opcion == 1) {


                output2 = new BufferedWriter(new FileWriter(src));
                vista.printLn("-> Ingrese la ruta que desea reportar\n");

                // Mostrar las rutas existentes
                for (int i = 0; i < rutas.size(); i++) {
                    vista.printLn("    " + (i + 1) + " - " + rutas.get(i));
                }

                Integer ruta = sc.nextInt();

                // Reescribir el archivo con las rutas correctas
                int contador = 0;
                for (int i = 0; i < rutas.size(); i++) {

                    //Agregar todas menos la ruta indicada
                    if (i != (ruta - 1)) {
                        output2.write(rutas.get(i));
                    }

                    // Validaciones para los saltos de linea
                    if (i != (ruta - 1)) {

                        if (ruta == rutas.size()) {
                            if (contador < rutas.size() - 2) {
                                output2.write("\n");
                            }
                        } else {
                            if (contador < rutas.size() - 1) {
                                output2.write("\n");
                            }
                        }
                    }

                    contador++;
                }

                vista.printLn("\n[OK] Ruta reportada correctamente\n");

                output2.close();


            // Ingresar una nueva ruta
            } else if (opcion == 2) {

                output = new BufferedWriter(new FileWriter(src, true));

                vista.printLn("\n-> Ingrese la ciudad de origen");
                String origen = sc.nextLine();

                vista.printLn("-> Ingrese la ciudad de destino");
                String destino = sc.nextLine();

                vista.printLn("-> Ingrese el la distancia en Km");
                String distancia = sc.nextLine();

                // Agregar la ruta al arhivo de rutas
                output.append("\n" + origen + " " + destino + " " + distancia);
                output.close();
                vista.printLn("\n[OK] Ruta agregada correctamente");

            } else if (opcion == 3) {
                estado = false;
            }

            // Vuelve a generar el grafo con los cambios
            generarGrafo();
        }

    }


     /**
	 *  Genera un nuevo grafo
	 * @return  void
	 * @param -
	 */
    public void generarGrafo() {

        // Lee el archivo
        try {
            archivo = new File(src);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            System.out.println("[!] No se encontro el archivo");
        }

        // Recorrer archivo
        try {

            // Guardar cada ciudad en una lista
            ciudades = new ArrayList<>();
            rutas = new ArrayList<>();
            String linea;
            while ((linea = br.readLine()) != null) {

                String[] temp = linea.split(" ");

                // Agregar cada ciudad a una lista
                if (!ciudades.contains(temp[0])) {
                    ciudades.add(temp[0]);
                }

                if (!ciudades.contains(temp[1])) {
                    ciudades.add(temp[1]);
                }

                // Guarda cada ruta en una lista
                rutas.add(linea);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        // Crear nodos
        grafo = new Grafo(ciudades.size());

        // Agregar ciudades al grafo
        for (int i = 0; i < ciudades.size(); i++) {
            grafo.ingresarNombre(i, ciudades.get(i));
        }

        for (int i = 0; i < ciudades.size(); i++) {
            for (int j = 0; j < ciudades.size(); j++) {

                if (i < j) {

                    // Recorrer las ciudades y ver si tienen una ruta
                    Boolean tieneRuta = false;
                    for (int j2 = 0; j2 < rutas.size(); j2++) {

                        String[] datos = rutas.get(j2).split(" ");

                        // System.out.println(datos[0] + " - " + datos[1] + " - "+ datos[2]);
                        if ((datos[0].equals(ciudades.get(i)) && datos[1].equals(ciudades.get(j)))
                                || (datos[1].equals(ciudades.get(i)) && datos[0].equals(ciudades.get(j)))) {

                            grafo.ingresarArco(i, j, Integer.parseInt(datos[2]));
                            tieneRuta = true;
                        }

                    }

                    if (!tieneRuta) {
                        grafo.ingresarArco(i, j, 10000);
                    }

                }

            }
        }

        grafo.calcular();

    }

}
