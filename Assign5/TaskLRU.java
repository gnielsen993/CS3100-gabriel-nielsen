/**
 * Runnable task that simulates LRU (Least Recently Used) page replacement
 * 
 * @author Gabriel Nielsen
 */
public class TaskLRU implements Runnable {

	private static final int EMPTY_FRAME = -1;
	private static final int NEVER_USED = -1;

	private final int[] sequence;
	private final int maxMemoryFrames;
	private final int maxPageReference;
	private final int[] pageFaults;

	/**
	 * Constructs an LRU simulation task.
	 *
	 * @param sequence          randomly generated sequence of page references
	 * @param maxMemoryFrames   number of frames of memory available
	 * @param maxPageReference  maximum page reference value possible in the sequence
	 * @param pageFaults        output array; the resulting fault count is stored
	 *                          at index {@code maxMemoryFrames}
	 * @author Gabriel Nielsen
	 */
	public TaskLRU(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
		this.sequence = sequence;
		this.maxMemoryFrames = maxMemoryFrames;
		this.maxPageReference = maxPageReference;
		this.pageFaults = pageFaults;
	}

	/**
	 * Executes the LRU page replacement simulation. 
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
			lastUsed[i] = NEVER_USED;
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
					if (lastUsed[j] < lastUsed[victimIndex]) {
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
