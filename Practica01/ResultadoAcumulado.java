/**
 * ResultadoAcumulado
 * 
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

public class ResultadoAcumulado {
	private int count=0, num;

	public ResultadoAcumulado(int num) {
		this.num = num;
	}

	public int increment(int num) {
		return this.count += num;
	}
}
