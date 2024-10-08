/**
 * DeterminanteSecuencial
 * Programa para calcular el determinante de una matriz de forma secuencial
 * 
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

 public class DeterminanteSecuencial{
    static int determinante;
    static int n_prueba = 3;
    static int matriz_prueba[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};
    int num1, num2, num3, partial;
    
    // En vez de crear un hilo por cada diagonal de la matriz, hacemos la multiplicación directamente y las
    // guardamos en un int
    public static int determinanteMatriz3x3(int matriz[][], int n_prueba) {
        int result = 0;

        int calc1 = matriz[0][0] * matriz[1][1] * matriz[2][2];
        int calc2 = matriz[1][0] * matriz[2][1] * matriz[0][2];
        int calc3 = matriz[2][0] * matriz[0][1] * matriz[1][2];
        int calc4 = matriz[2][0] * matriz[1][1] * matriz[0][2];
        int calc5 = matriz[1][0] * matriz[0][1] * matriz[2][2];
        int calc6 = matriz[0][0] * matriz[2][1] * matriz[1][2];

        // Al final hacemos el calculo del determinante directamente.
        result = calc1 + calc2 + calc3 - calc4 - calc5 - calc6;

        return result;
    }

    public static void main(String[] args) {
		long startTime = System.nanoTime();
		determinante = determinanteMatriz3x3(matriz_prueba, n_prueba);
		long endTime = System.nanoTime();
		System.out.println("Program took " + (endTime - startTime) + "ns, result: " + determinante);
	}
	
}