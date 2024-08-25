public class ResultadoAcumulado {
	private int count=0, num;

	public ResultadoAcumulado(int num) {
		this.num = num;
	}

	public int increment(int num) {
		return this.count += num;
	}
}
