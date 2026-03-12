import java.util.PriorityQueue;

/**
 * Shortest Job First (SJF) scheduling algorithm implementation.
 * This scheduler non-preemptively selects the process with the shortest burst time.
 * @author Gabriel
 */
public class SchedulerSJF extends SchedulerBase implements Scheduler {
    private final Logger logger;
    private final PriorityQueue<Process> readyQueue;

    /**
     * Constructor for SchedulerSJF.
     * @param logger Logger instance for logging scheduling events.
     */
    public SchedulerSJF(Logger logger) {
        this.logger = logger;
        this.readyQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getBurstTime(), p2.getBurstTime()));
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