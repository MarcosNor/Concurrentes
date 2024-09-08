//package unam.fc.concurrent.practica2;


//Programa 6: Contador con ExecutorService, Callables y Futures

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ColaConcurrenteFuture {

    ColaSecuencial queue;
	static int counter = 0;
	Integer arrayRes[];
	
	private static ColaSecuencial increment(ColaSecuencial queue, int i) { //Ahora executor recibe un objeto Callable y no Runnable
        if(i % 4 == 0) {
            queue.enq("a");
        }
        else if (i % 4 == 1) {
            queue.enq("b");
        } else {
            queue.deq();
        }

        return queue;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        ColaSecuencial queue = new ColaSecuencial();
		List<Future<ColaSecuencial>> futures = new ArrayList<Future<ColaSecuencial>>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
			for(int i = 0; i < 300; i++) {
				final int ntask=i;
	//			executor.execute(() -> increment(ntask)); //Callable al estilo lambda, esta notacion se usa en interfaces
				futures.add(executor.submit(() -> increment(queue, ntask)));
			}
			System.out.println("Termino la tarea: " + futures.get(0).isDone());
			executor.shutdown();
			
		
		try{			
			for (int i = 0; i < futures.size(); i++) {
	            while(!futures.get(i).isDone());
	            ColaSecuencial result = futures.get(i).get();
	            System.out.printf("\n Result: " + i);
                //result.print();//Podemos obtener todos los resultados de cada Callable
	            //Si ejecutamos futures.get(i).get(); despues de add, forzamos que cada tarea termine antes de ejecutar otra
	            
        }
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		
		try{
			Thread.sleep(1800);// Delay para esperar que todas las tareas terminen
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		System.out.println("Cola final: ");
        queue.print();
	}

}