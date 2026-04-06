import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Assign5 {

    static final int MAX_PAGE_REFERENCE = 250;
    static final int SEQUENCE_LENGTH = 1000;
    static final int NUM_SIMULATIONS = 1000;
    static final int MAX_FRAMES = 100;

    public static void main(String[] args) throws InterruptedException {

        int[][] fifoFaults = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];
        int[][] lruFaults  = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];
        int[][] mruFaults  = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];

        long start = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
            int[] sequence = generateSequence(SEQUENCE_LENGTH, MAX_PAGE_REFERENCE);
            for (int frames = 1; frames <= MAX_FRAMES; frames++) {
                pool.submit(new TaskFIFO(sequence, frames, fifoFaults[sim]));
                pool.submit(new TaskLRU(sequence, frames, lruFaults[sim]));
                pool.submit(new TaskMRU(sequence, frames, mruFaults[sim]));
            }
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Simulation took " + (System.currentTimeMillis() - start) + " ms");
        
        summarize(fifoFaults, lruFaults, mruFaults);
        detectAnomaly("FIFO", fifoFaults);
        detectAnomaly("LRU", lruFaults);
        detectAnomaly("MRU", mruFaults);
    }

    private static int[] generateSequence(int length, int maxRef) {
        Random rand = new Random();
        int[] seq = new int[length];
        for (int i = 0; i < length; i++) {
            seq[i] = rand.nextInt(maxRef) + 1;
        }
        return seq;
    }

    private static void summarize(int[][] fifo, int[][] lru, int[][] mru) {
        int fifoWins = 0, lruWins = 0, mruWins = 0;

        for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
            for (int frames = 1; frames <= MAX_FRAMES; frames++) {
                int min = Math.min(fifo[sim][frames], Math.min(lru[sim][frames], mru[sim][frames]));
                if (fifo[sim][frames] == min) fifoWins++;
                if (lru[sim][frames] == min) lruWins++;
                if (mru[sim][frames] == min) mruWins++;
            }
        }

        System.out.printf("%nFIFO min PF : %d%n", fifoWins);
        System.out.printf("LRU min PF  : %d%n", lruWins);
        System.out.printf("MRU min PF  : %d%n", mruWins);
    }

    private static void detectAnomaly(String name, int[][] faults) {
        System.out.printf("%nBelady's Anomaly Report for %s%n", name);
        int count = 0;
        int maxDiff = 0;

        for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
            for (int frames = 1; frames < MAX_FRAMES; frames++) {
                if (faults[sim][frames + 1] > faults[sim][frames]) {
                    int diff = faults[sim][frames + 1] - faults[sim][frames];
                    if (diff > maxDiff) maxDiff = diff;
                    count++;
                    System.out.printf("    detected - Previous %d : Current %d (%d)%n", faults[sim][frames], faults[sim][frames + 1], diff);
                }
            }
        }
        System.out.printf("     Anomaly detected %d times with a max difference of %d%n", count, maxDiff);
    }


}
