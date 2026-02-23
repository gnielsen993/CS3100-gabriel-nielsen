import java.util.concurrent.atomic.AtomicInteger;

public class PiWorker extends Thread {
    private final TaskQueue taskQueue;
    private final ResultTable resultTable;
    private final AtomicInteger completedDigits;

    public PiWorker(TaskQueue taskQueue, ResultTable resultTable, AtomicInteger completedDigits) {
        this.taskQueue = taskQueue;
        this.resultTable = resultTable;
        this.completedDigits = completedDigits;
    }

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
            if (completed % 10 == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
    }
}