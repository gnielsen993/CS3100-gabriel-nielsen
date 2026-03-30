public class TaskFIFO implements Runnable {
	private final int[] sequence;
	private final int memorySize;
	private final int[] pageFaults;

	public TaskFIFO(int[] sequence, int memorySize, int[] pageFaults) {
		this.sequence = sequence;
		this.memorySize = memorySize;
		this.pageFaults = pageFaults;
		
	}

	@Override
	public void run() {
		if (memorySize == 0) {
			pageFaults[memorySize] = sequence.length;
			return;
		}

		int[] memory = new int[memorySize];
		for (int i = 0; i < memorySize; i++) {
			memory[i] = -1;
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
				replaceIndex = (replaceIndex + 1) % memorySize;
			}
		}

		pageFaults[memorySize] = faultCount;
	}
}
