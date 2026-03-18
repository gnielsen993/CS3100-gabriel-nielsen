import java.util.LinkedList;

/**
 * First-Come, First-Served (FCFS) scheduling algorithm implementation.
 * This scheduler non-preemptively selects processes in the order they arrive.
 * @author Gabriel
 */
public class SchedulerFCFS extends SchedulerBase implements Scheduler {
    private final Logger logger;
    private final LinkedList<Process> readyQueue;

    /**
     * Constructor for SchedulerFCFS.
     * @param logger Logger instance for logging scheduling events.
     */
    public SchedulerFCFS(Logger logger) {
        this.logger = logger;
        this.readyQueue = new LinkedList<>();  
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
            cpu = readyQueue.removeFirst();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
            
        }

        return cpu;  
    }

    
}