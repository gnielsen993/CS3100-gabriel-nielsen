public class TaskLRU implements Runnable {
	private final int[] sequence;
	private final int memorySize;
	private final int[] pageFaults;

	public TaskLRU(int[] sequence, int memorySize, int[] pageFaults) {
		this.sequence = sequence;
		this.memorySize = memorySize;
		this.pageFaults = pageFaults;
	}

	@Override
	public void run() {

		int[] lastUsed = new int[memorySize];

		if (memorySize == 0) {
			pageFaults[memorySize] = sequence.length;
			return;
		}

		int[] memory = new int[memorySize];
		for (int i = 0; i < memorySize; i++) {
			memory[i] = -1;
			lastUsed[i] = -1;
		}
		int faultCount = 0;

		for (int i = 0; i < sequence.length; i++) {
			int page = sequence[i];
			boolean hit = false;

			for (int j = 0; j < memorySize; j++) {
				if (memory[j] == page) {
					hit = true;
					lastUsed[j] = i;
					break;
				}
			}

			if (!hit) {
				faultCount++;
				int lruIndex = 0;
				for (int j = 1; j < memorySize; j++) {
					if (lastUsed[j] < lastUsed[lruIndex]) {
						lruIndex = j;
					}
				}
				memory[lruIndex] = page;
				lastUsed[lruIndex] = i;
			}
		}

		pageFaults[memorySize] = faultCount;
	}

}
