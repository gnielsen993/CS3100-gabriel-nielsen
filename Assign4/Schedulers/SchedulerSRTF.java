import java.util.PriorityQueue;

/**
 * Shortest Remaining Time First (SRTF) scheduling algorithm implementation.
 * This scheduler preemptively selects the process with the shortest remaining burst time.
 * @author Gabriel
 */
public class SchedulerSRTF extends SchedulerBase implements Scheduler {
    private final Logger logger;
    private final PriorityQueue<Process> readyQueue;

    /**
     * Constructor for SchedulerSRTF.
     * @param logger Logger instance for logging scheduling events.
     */
    public SchedulerSRTF(Logger logger) {
        this.logger = logger;
        this.readyQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getRemainingBurst(), p2.getRemainingBurst()));
    }

    /**
     * Notifies the scheduler of a new process arrival.
     * @param process The new process to be added to the ready queue.
     */
    @Override
    public void notifyNewProcess(Process process) {
        readyQueue.add(process);
    }

    /**
     * Updates the scheduler's state and returns the process to be executed.
     * @param cpu The currently running process.
     * @return The process to be executed next.
     */
    @Override
    public Process update(Process cpu) {
        if (cpu != null && readyQueue.peek() != null && readyQueue.peek().getRemainingBurst() < cpu.getRemainingBurst()) {
            logger.log("Preemptively removed: " + cpu.getName());
            readyQueue.add(cpu);  
            cpu = null;
            contextSwitches++;
        }

        if (cpu != null && cpu.isBurstComplete()) {
            logger.log("Process " + cpu.getName() + " burst complete");
            contextSwitches++;

            if (cpu.isExecutionComplete()) {
                logger.log("Process " + cpu.getName() + " execution complete");
            } else {
                readyQueue.add(cpu);  
            }

            cpu = null;
        }

        if (cpu == null && !readyQueue.isEmpty()) {
            cpu = readyQueue.poll();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
            
        }

        return cpu;  
    }
}