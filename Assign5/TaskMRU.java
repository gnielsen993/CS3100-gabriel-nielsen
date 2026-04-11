/**
 * Runnable task that simulates MRU (Most Recently Used) page replacement
 * 
 * @author Gabriel Nielsen
 */
public class TaskMRU implements Runnable {

	private static final int EMPTY_FRAME = -1;
	private static final int EMPTY_TIMESTAMP = Integer.MAX_VALUE;

	private final int[] sequence;
	private final int maxMemoryFrames;
	private final int maxPageReference;
	private final int[] pageFaults;

	/**
	 * Constructs an MRU simulation task.
	 *
	 * @param sequence          randomly generated sequence of page references
	 * @param maxMemoryFrames   number of frames of memory available
	 * @param maxPageReference  maximum page reference value possible in the sequence
	 * @param pageFaults        output array; the resulting fault count is stored
	 *                          at index {@code maxMemoryFrames}
	 * @author Gabriel Nielsen
	 */
	public TaskMRU(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
		this.sequence = sequence;
		this.maxMemoryFrames = maxMemoryFrames;
		this.maxPageReference = maxPageReference;
		this.pageFaults = pageFaults;
	}

	/**
	 * Executes the MRU page replacement simulation
	 * 
	 * @author Gabriel Nielsen
	 */
	@Override
	public void run() {
		if (maxMemoryFrames == 0) {
			pageFaults[maxMemoryFrames] = sequence.length;
			return;
		}

		int[] memory = new int[maxMemoryFrames];
		int[] lastUsed = new int[maxMemoryFrames];
		for (int i = 0; i < maxMemoryFrames; i++) {
			memory[i] = EMPTY_FRAME;
			lastUsed[i] = EMPTY_TIMESTAMP;
		}

		int faultCount = 0;

		for (int i = 0; i < sequence.length; i++) {
			int page = sequence[i];
			boolean hit = false;

			for (int j = 0; j < maxMemoryFrames; j++) {
				if (memory[j] == page) {
					hit = true;
					lastUsed[j] = i;
					break;
				}
			}

			if (!hit) {
				faultCount++;
				int victimIndex = 0;
				for (int j = 1; j < maxMemoryFrames; j++) {
					if (lastUsed[j] > lastUsed[victimIndex]) {
						victimIndex = j;
					}
				}
				memory[victimIndex] = page;
				lastUsed[victimIndex] = i;
			}
		}

		pageFaults[maxMemoryFrames] = faultCount;
	}
}
