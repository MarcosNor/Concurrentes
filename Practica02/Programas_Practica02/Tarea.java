import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Tarea
 *
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

public class Tarea implements Runnable {
    int tiempoTarea;
    int task;
    static final Semaphore smphre = new Semaphore(3);
    static final Lock[] locks = new ReentrantLock[6];

    public Tarea(int i) {
        this.task = i;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        long id = currentThread.getId();
        int p = (int) (id % 6);

        try {
            locks[p].lock();  //Candado para que solo se pueda ejecutar una tarea

            smphre.acquire();  // El semaforo pide ejecutar la tarea

            System.out.println("Running Thread " + p + " task: " + this.task);

            switch (p) {
                case 0:
                    this.tiempoTarea = 500;
                    break;
                case 1:
                    this.tiempoTarea = 2000;
                    break;
	    case 2:
		this.tiempoTarea = 500;
		break;
                default:
                    this.tiempoTarea = 3000;
            }

            try {
                Thread.sleep(this.tiempoTarea);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            System.out.println("Thread " + p + " finished task: " + this.task + " in " + this.tiempoTarea + "ms");

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            smphre.release();
            locks[p].unlock();
        }
    }

    // Inicializamos los candados
    static {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }
    }
}
