import java.util.concurrent.atomic.AtomicInteger;

public class PiWorker extends Thread {
    private TaskQueue taskQueue;
    private ResultTable resultTable;
    private AtomicInteger completedDigits;

    public PiWorker(TaskQueue taskQueue, ResultTable resultTable, AtomicInteger completedDigits) {
        this.taskQueue = taskQueue;
        this.resultTable = resultTable;
        this.completedDigits = completedDigits;
    }

    public void run() {
        Bpp bpp = new Bpp();

        while (true) {
            Integer digitPosition = taskQueue.dequeue();
            if (digitPosition == null) {
                break;
            }
            int digitValue = bpp.getDecimal(digitPosition) / 100000000;
            resultTable.addResult(digitPosition, digitValue);

            int completed = completedDigits.incrementAndGet();
            if (completed % 10 == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
    }
}