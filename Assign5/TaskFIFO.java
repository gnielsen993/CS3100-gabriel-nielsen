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
        for (int i = 0; i < sequence.length; i++) {
			int page = sequence[i];
			boolean pageFault = true;

			for (int j = 0; j < memorySize; j++) {
				if (pageFaults[j] == page) {
					pageFault = false;
					break;
				}
			}

			if (pageFault) {
				for (int j = 0; j < memorySize - 1; j++) {
					pageFaults[j] = pageFaults[j + 1];
				}
				
				pageFaults[memorySize - 1] = page;
			}
		}
	}
}
