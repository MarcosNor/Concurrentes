public class DeterminanteConcurrenteRunnable {

    static int matriz[][] = { { 1, 2, 2 }, { 1, 0, -2 }, { 3, -1, 1 }};


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int num = 0;
        ResultadoAcumulado result = new ResultadoAcumulado(num);
        ThreadRunnable myRunnable1 = new ThreadRunnable(result, matriz[0][0], matriz[1][1], matriz[2][2]);
        ThreadRunnable myRunnable2 = new ThreadRunnable(result, matriz[1][0], matriz[2][1], matriz[0][2]);
        ThreadRunnable myRunnable3 = new ThreadRunnable(result, matriz[2][0], matriz[0][1], matriz[1][2]);
        ThreadRunnable myRunnable4 = new ThreadRunnable(result, -matriz[2][0], -matriz[1][1], -matriz[0][2]);
        ThreadRunnable myRunnable5 = new ThreadRunnable(result, -matriz[1][0], -matriz[0][1], -matriz[2][2]);
        ThreadRunnable myRunnable6 = new ThreadRunnable(result, -matriz[0][0], -matriz[2][1], -matriz[1][2]);
		Thread threadA = new Thread(myRunnable1);
        Thread threadB = new Thread(myRunnable2);
        Thread threadC = new Thread(myRunnable3);
        Thread threadD = new Thread(myRunnable4);
        Thread threadE = new Thread(myRunnable5);
        Thread threadF = new Thread(myRunnable6);
		threadA.start();
		threadB.start();
		threadC.start();
        threadD.start();
        threadE.start();
        threadF.start();
        long endTime = System.nanoTime();
        try{
            Thread.sleep(500);
        }catch(InterruptedException e) {}
		System.out.println("Program took " + (endTime - startTime) + "ns");
	}
}

class ThreadRunnable implements Runnable {

    int num1, num2, num3;
    ResultadoAcumulado result=null;

    public ThreadRunnable(ResultadoAcumulado result, int num1, int num2, int num3) {
        this.result = result;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    @Override
    public void run()
        {
            System.out.println(Thread.currentThread().getName()
                             + ", ejecutando método run(), resultado hasta el momento: " + this.result.increment(num1 * num2 * num3));
        }
}   
