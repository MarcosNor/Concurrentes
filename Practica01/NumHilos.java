/**
 * NumHilos
 * Programa para mostrar el número de hilos que tiene nuestro equipo
 * 
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

 public class NumHilos {
    public static void main(String[] args) {
        System.out.println("Número de hilos disponibles: " + Runtime.getRuntime().availableProcessors());
    }
}