import java.util.concurrent.atomic.AtomicInteger;

/**
 * Worker thread class responsible for computing individual digits of Pi. Each worker thread continuously retrieves digit positions from the task queue, computes the corresponding digit of Pi using the Bpp class, and stores the result in the result table. The worker threads also keep track of the number of completed digits and print progress updates every 10 digits.
 * @author Gabriel Nielsen
 */
public class PiWorker extends Thread {
    private final TaskQueue taskQueue;
    private final ResultTable resultTable;
    private final AtomicInteger completedDigits;
    private final static int PROGRESS_INTERVAL = 10; // Interval for printing progress updates

    /**
     * Constructor for PiWorker initializes the task queue, result table, and completed digits counter.
     * @param taskQueue the shared task queue from which the worker will retrieve digit positions to compute
     * @param resultTable the shared result table where the worker will store computed digit values 
     * @param completedDigits the shared atomic integer that keeps track of the number of completed digit computations across all worker threads
     */
    public PiWorker(TaskQueue taskQueue, ResultTable resultTable, AtomicInteger completedDigits) {
        this.taskQueue = taskQueue;
        this.resultTable = resultTable;
        this.completedDigits = completedDigits;
    }

    /**
     * The run method continuously retrieves digit positions from the task queue, computes the corresponding digit of Pi using the Bpp class, and stores the result in the result table. 
     * It also updates the completed digits counter and prints progress updates every 10 digits.
     */
    @Override
    public void run() {
        Bpp bpp = new Bpp();

        while (true) {
            Integer digitPosition = taskQueue.dequeue();
            if (digitPosition == null) {
                break;
            }
            
            int value = bpp.getDecimal(digitPosition);
            String stringValue = Integer.toString(value);
            int digitValue = Character.getNumericValue(stringValue.charAt(0));

            resultTable.addResult(digitPosition, digitValue);

            int completed = completedDigits.incrementAndGet();
            if (completed % PROGRESS_INTERVAL == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
    }
}