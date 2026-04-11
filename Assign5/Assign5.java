import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Simulates page replacement algorithms (FIFO, LRU, MRU) 
 * on randomly generated page reference sequences, then summarizes the results
 * 
 * @author Gabriel Nielsen
 */
public class Assign5 {

	private static final int MAX_PAGE_REFERENCE = 250;
	private static final int SEQUENCE_LENGTH = 1000;
	private static final int NUM_SIMULATIONS = 1000;
	private static final int MAX_FRAMES = 100;
	private static final int POOL_TIMEOUT_MINUTES = 10;

	/**
	 * Executes the simulations and prints the results. 
     * 
     * @param args  not used
     * @throws InterruptedException if the thread pool is interrupted while waiting for tasks to complete
     * @author Gabriel Nielsen
	 */
	public static void main(String[] args) throws InterruptedException {
		int[][] fifoFaults = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];
		int[][] lruFaults = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];
		int[][] mruFaults = new int[NUM_SIMULATIONS][MAX_FRAMES + 1];

		long startTime = System.currentTimeMillis();
		ExecutorService pool = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors());

		for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
			int[] sequence = generateSequence(SEQUENCE_LENGTH, MAX_PAGE_REFERENCE);
			for (int frames = 1; frames <= MAX_FRAMES; frames++) {
				pool.submit(new TaskFIFO(sequence, frames, MAX_PAGE_REFERENCE, fifoFaults[sim]));
				pool.submit(new TaskLRU(sequence, frames, MAX_PAGE_REFERENCE, lruFaults[sim]));
				pool.submit(new TaskMRU(sequence, frames, MAX_PAGE_REFERENCE, mruFaults[sim]));
			}
		}

		pool.shutdown();
		pool.awaitTermination(POOL_TIMEOUT_MINUTES, TimeUnit.MINUTES);

		long elapsedMillis = System.currentTimeMillis() - startTime;
		System.out.println("Simulation took " + elapsedMillis + " ms");

		summarize(fifoFaults, lruFaults, mruFaults);
		detectAnomaly("FIFO", fifoFaults);
		detectAnomaly("LRU", lruFaults);
		detectAnomaly("MRU", mruFaults);
	}

	/**
	 * Generates a random page reference sequence.
	 *
	 * @param length           length of the sequence to generate
	 * @param maxPageReference maximum (inclusive) page reference value; the
	 *                         minimum page reference is 1
	 * @return a newly allocated array of random page references
	 * @author Gabriel Nielsen
	 */
	private static int[] generateSequence(int length, int maxPageReference) {
		Random random = new Random();
		int[] sequence = new int[length];
		for (int i = 0; i < length; i++) {
			sequence[i] = random.nextInt(maxPageReference) + 1;
		}
		return sequence;
	}

	/**
	 * Counts the number of times each algorithm has the minimum page fault count
     * 
     * @param fifoFaults  page fault counts for FIFO indexed [sim][frames]
     * @param lruFaults   page fault counts for LRU indexed [sim][frames]
     * @param mruFaults   page fault counts for MRU indexed [sim][frames]
     * @author Gabriel Nielsen
	 */
	private static void summarize(int[][] fifoFaults, int[][] lruFaults, int[][] mruFaults) {
		int fifoWins = 0;
		int lruWins = 0;
		int mruWins = 0;

		for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
			for (int frames = 1; frames <= MAX_FRAMES; frames++) {
				int minFaults = Math.min(fifoFaults[sim][frames],
						Math.min(lruFaults[sim][frames], mruFaults[sim][frames]));
				if (fifoFaults[sim][frames] == minFaults) {
					fifoWins++;
				}
				if (lruFaults[sim][frames] == minFaults) {
					lruWins++;
				}
				if (mruFaults[sim][frames] == minFaults) {
					mruWins++;
				}
			}
		}

		System.out.printf("%nFIFO min PF : %d%n", fifoWins);
		System.out.printf("LRU min PF  : %d%n", lruWins);
		System.out.printf("MRU min PF  : %d%n", mruWins);
	}

	/**
	 * Detects and reports instances of Belady's Anomaly,
     * 
     * @param algorithmName name of the algorithm being analyzed (for reporting purposes)
     * @param faults        page fault counts indexed [sim][frames]
     * @author Gabriel Nielsen
	 */
	private static void detectAnomaly(String algorithmName, int[][] faults) {
		System.out.printf("%nBelady's Anomaly Report for %s%n", algorithmName);
		int anomalyCount = 0;
		int maxDifference = 0;

		for (int sim = 0; sim < NUM_SIMULATIONS; sim++) {
			for (int frames = 1; frames < MAX_FRAMES; frames++) {
				int previousFaults = faults[sim][frames];
				int currentFaults = faults[sim][frames + 1];
				if (currentFaults > previousFaults) {
					int difference = currentFaults - previousFaults;
					if (difference > maxDifference) {
						maxDifference = difference;
					}
					anomalyCount++;
					System.out.printf("    detected - Previous %d : Current %d (%d)%n",
							previousFaults, currentFaults, difference);
				}
			}
		}
		System.out.printf("     Anomaly detected %d times with a max difference of %d%n",
				anomalyCount, maxDifference);
	}
}
