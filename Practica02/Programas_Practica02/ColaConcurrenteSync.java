

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ColaConcurrenteSync
 * Cola concurrente usando Synchronized para evitar condiciones de carrera
 *
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

public class ColaConcurrenteSync {

    private Nodo head;
	private Nodo tail;
	public ColaConcurrenteSync() {
		this.head  = new Nodo("hnull");
	    this.tail  = new Nodo("tnull");
	    this.head.next = this.tail;
	}
	public synchronized Callable<Boolean> enqSync(String x) {
        return () -> {
            Nodo newNode = new Nodo(x);
            if (this.head.next == this.tail) {
                newNode.next = this.tail;
                this.head.next = newNode;
            } else {
                Nodo last = this.tail.next;
                newNode.next = this.tail;
                last.next = newNode;
            }
            this.tail.next = newNode;
            return true;
        };
    }
	public synchronized Callable<String> deqSync() {
        return () -> {
            if (this.head.next == this.tail) {
                return "empty";
            }
            Nodo first = this.head.next;
            this.head.next = first.next;
            return first.item;
       };
	}
	public synchronized void printSync() {
		System.out.println("Print ");
        Nodo pred = this.head;
        Nodo curr = pred.next;
        System.out.println(pred.item);
        while (curr.item != "tnull") {
          pred = curr;
          curr = curr.next;
          System.out.println(pred.item);
        }
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        ColaConcurrenteSync queue = new ColaConcurrenteSync();
		List<Future<?>> futures = new ArrayList<Future<?>>();
		ExecutorService executor = Executors.newFixedThreadPool(4);

        futures.add(executor.submit(queue.enqSync("a")));
        futures.add(executor.submit(queue.enqSync("b")));
        futures.add(executor.submit(queue.deqSync()));
        System.out.println("Termino la tarea: " + futures.get(0).isDone());
        executor.shutdown();

        for (int i = 0; i < futures.size(); i++) {
            while(!futures.get(i).isDone());
            System.out.printf("\n Result: " + i + " is " + futures.get(i).get());
        }

		try{
			Thread.sleep(1800);// Delay para esperar que todas las tareas terminen
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("\nCola final: ");
        queue.printSync();
	}

}

