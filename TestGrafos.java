import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestGrafos {


    Controlador controlador = new Controlador(null);
    
    /*
     * Test para agrear un arco
     */
    @Test
    public void agregarArco() {
        Grafo grafo = new Grafo(5);
       
        try {
            grafo.ingresarArco(0,0,5);
            assertEquals(true, true);

        } catch (Exception e) {
            assertEquals(true, false);
        }   
    }


    /*
     * Test para agrear un nodo
     */
    @Test
    public void agregarNodo() {
        Grafo grafo = new Grafo(5);
       
        try {
            grafo.ingresarNombre(0,"Ciudad ejemplo");
            assertEquals(true, true);

        } catch (Exception e) {
            assertEquals(true, false);
        }

        
    }


      /*
     * Algoritmo de floyd
     */
    @Test
    public void algoritmoFloyd() {
        Grafo grafo = new Grafo(5);
       
        try {
            assertEquals("Antigua", grafo.centro());

        } catch (Exception e) {
            assertEquals(true, false);
        }

        
    }

   
}
    