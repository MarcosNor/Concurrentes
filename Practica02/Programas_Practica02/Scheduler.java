import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Scheduler
 *
 * @author Luis Gerardo Estrada García (319013832)
 * @author Dulce Julieta Mora Hernández (319236448)
 * @author Marcos Julián Noriega Rodríguez (319284061)
 */

public class Scheduler {

    public static void main(String[] args) {
        ExecutorService executorTarea = Executors.newFixedThreadPool(6);

        for (int i = 0; i < 26; i++) {
            executorTarea.execute(new Tarea(i));
        }

        executorTarea.shutdown();
    }
}
