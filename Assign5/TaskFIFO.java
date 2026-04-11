/**
 * Simulates the FIFO page replacement algorithm
 * 
 * @author Gabriel Nielsen
 */
public class TaskFIFO implements Runnable {

	private static final int EMPTY_FRAME = -1;

	private final int[] sequence;
	private final int maxMemoryFrames;
	private final int maxPageReference;
	private final int[] pageFaults;

	/**
	 * Constructs a FIFO simulation task.
	 *
	 * @param sequence          randomly generated sequence of page references
	 * @param maxMemoryFrames   number of frames of memory available
	 * @param maxPageReference  maximum page reference value possible in the sequence
	 * @param pageFaults        output array; the resulting fault count is stored
	 *                          at index {@code maxMemoryFrames}
	 * @author Gabriel Nielsen
	 */
	public TaskFIFO(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
		this.sequence = sequence;
		this.maxMemoryFrames = maxMemoryFrames;
		this.maxPageReference = maxPageReference;
		this.pageFaults = pageFaults;
	}

	/**
	 * Executes the FIFO page replacement simulation and writes the number of
	 * page faults
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
		for (int i = 0; i < maxMemoryFrames; i++) {
			memory[i] = EMPTY_FRAME;
		}

		int faultCount = 0;
		int replaceIndex = 0;

		for (int page : sequence) {
			boolean hit = false;
			for (int loaded : memory) {
				if (loaded == page) {
					hit = true;
					break;
				}
			}

			if (!hit) {
				faultCount++;
				memory[replaceIndex] = page;
				replaceIndex = (replaceIndex + 1) % maxMemoryFrames;
			}
		}

		pageFaults[maxMemoryFrames] = faultCount;
	}
}
