import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Main class for computing the first 1000 digits of Pi using multiple threads.
 * It initializes the task queue, result table, and worker threads, and manages the overall computation process.
 * @author Gabriel Nielsen
 */
public class Assign3 {

    private static final int NUM_DIGITS = 1000;

    /**
     * The main method initializes the task queue with digit positions, starts the worker threads to compute the digits of Pi, and collects the results in a result table. It also measures the time taken for the computation and prints the results.
     * @param args command-line arguments (not used)
     * @throws InterruptedException if any thread is interrupted while waiting for the worker threads to finish
     */
    public static void main(String[] args) throws InterruptedException {

        int cores = Runtime.getRuntime().availableProcessors();

        TaskQueue taskQueue = new TaskQueue();
        ResultTable resultTable = new ResultTable();
        AtomicInteger completedDigits = new AtomicInteger(0);

        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= NUM_DIGITS; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        for (int digit : digits) {
            taskQueue.enqueue(digit);
        }

        long startTime = System.currentTimeMillis();

        PiWorker[] workers = new PiWorker[cores];
        for (int i = 0; i < cores; i++) {
            workers[i] = new PiWorker(taskQueue, resultTable, completedDigits);
            workers[i].start();
        }

        for (int i = 0; i < cores; i++) {
            workers[i].join();
        }

        long endTime = System.currentTimeMillis();

        HashMap<Integer, Integer> results = resultTable.getResults();
        System.out.println("\n");
        System.out.print("3.");
        for (int i = 1; i <= NUM_DIGITS; i++) {
            System.out.print(results.get(i));
        }

        System.out.println();
        System.out.println("Pi computation took " + (endTime - startTime) + " ms");

    
    }
}