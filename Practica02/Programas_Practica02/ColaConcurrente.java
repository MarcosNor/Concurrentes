import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ColaConcurrente
 * Creación de una pool de hilos para pasarles un Runnable de la cola secuencial
 *
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

public class ColaConcurrente {


	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(4);
        ColaSecuencial queue = new ColaSecuencial();
		for(int i = 0; i < 3; i++) {
			executor.execute(new MyRunnable(i, queue));
		}
		executor.shutdown();
		
	try{
		Thread.sleep(1800);// Delay para esperar que todas las tareas terminen
	}catch(InterruptedException e) {
		System.out.println(e);
	}
        queue.print();
        
	}

}

class MyRunnable  implements Runnable{
	int nTask = 0;
    ColaSecuencial queue;
	MyRunnable(int i, ColaSecuencial queue){
		this.nTask = i;
        this.queue = queue;
	}
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
        if(this.nTask % 3 == 0) {
            this.queue.enq("a");
        }
        else if (this.nTask % 3 == 1) {
            this.queue.enq("b");
        } else {
            this.queue.deq();
        }
		System.out.println("Running " + threadName + " Task " + this.nTask);
	}
			
}
