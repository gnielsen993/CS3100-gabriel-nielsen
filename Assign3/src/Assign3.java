import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Assign3 {
    public static void main(String[] args) throws InterruptedException {

        int cores = Runtime.getRuntime().availableProcessors();

        TaskQueue taskQueue = new TaskQueue();
        ResultTable resultTable = new ResultTable();
        AtomicInteger completedDigits = new AtomicInteger(0);

        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
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
        for (int i = 1; i <= 1000; i++) {
            System.out.print(results.get(i));
        }

        System.out.println();
        System.out.println("Pi computation took " + (endTime - startTime) + " ms");

    
    }
}