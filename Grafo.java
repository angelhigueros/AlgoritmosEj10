/**
 * @author Angel Higueros - 20460
 * @version 1.0
 */

public final class Grafo {

	// Propiedades del grafo
	private int nnodos;

	private int nodos[][][];

	private String nombres[];

	Grafo(int n) {
		this.nnodos = n;
		this.nodos = new int[nnodos][nnodos][2];
		this.nombres = new String[nnodos];
	}

	/**
	 * Ingresa una nueva arista al grafo
	 * 
	 * @return void
	 * @param numero nodo, numero arista, peso
	 */
	public void ingresarArco(int n1, int n2, int peso) {
		this.nodos[n1][n2][0] = peso;
		this.nodos[n2][n1][0] = peso;
		this.nodos[n1][n2][1] = n1;
		this.nodos[n2][n1][1] = n2;
	}

	/**
	 * Asigna un nombre a un nodo
	 * 
	 * @return void
	 * @param numero nodo, nombre del nodo
	 */
	public void ingresarNombre(int nodo, String nombre) {
		this.nombres[nodo] = nombre;
	}

	/**
	 * Regresa el nombre del centro del grafo
	 * 
	 * @return void
	 * @param nombre del centro
	 */
	public String centro() {
		return getNombre(0);
	}

	/**
	 * Realiza los calculos para ordenar el grafo
	 * 
	 * @return void
	 * @param -
	 */
	public void calcular() {
		int i, j, k;
		for (i = 0; i < this.nnodos; i++) {
			for (j = 0; j < this.nnodos; j++) {
				for (k = 0; k < this.nnodos; k++) {
					if (this.nodos[i][k][0] + this.nodos[k][j][0] < this.nodos[i][j][0]) {
						this.nodos[i][j][0] = this.nodos[i][k][0] + this.nodos[k][j][0];
						this.nodos[i][j][1] = k;
					}
				}
			}
		}
	}

	/**
	 * Regresa el peso el nodo de menor peso segun los parametros
	 * 
	 * @return nodo
	 * @param numero nodo, des
	 */
	public int pesominimo(int org, int des) {
		return this.nodos[org][des][0];
	}

	/**
	 * Calucula el camino mas corto entre dos nodos
	 * 
	 * @return camino a recorrer
	 * @param numero nodo, nombre del nodo
	 */
	public String caminocorto(int org, int des) {
		String cam;
		if (org == des) {
			cam = "->" + nombres[org];
		} else {
			cam = caminocorto(org, this.nodos[org][des][1]) + "->" + nombres[des];
		}
		return cam;
	}

	/**
	 * Regresa el nombre del nodo indicado
	 * 
	 * @return nombre del nodo
	 * @param numero nodo
	 */
	public String getNombre(int nodo) {
		return this.nombres[nodo];
	}

}