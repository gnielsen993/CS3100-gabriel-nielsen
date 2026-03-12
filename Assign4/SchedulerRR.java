import java.util.LinkedList;

/**
 * Round Robin (RR) scheduling algorithm implementation.
 * This scheduler preemptively cycles through processes in the ready queue based on a fixed time quantum.
 * @author Gabriel
 */
public class SchedulerRR extends SchedulerBase implements Scheduler {
    private final Logger logger;
    private final LinkedList<Process> readyQueue;
    private final int timeQuantum;
    private int timeCounter = 0;

    /**
     * Constructor for SchedulerRR.
     * @param logger Logger instance for logging scheduling events.
     * @param timeQuantum The fixed time quantum for each process.
     */
    public SchedulerRR(Logger logger, int timeQuantum) {
        this.logger = logger;
        this.readyQueue = new LinkedList<>();  
        this.timeQuantum = timeQuantum;
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
        if (cpu != null) {
            timeCounter++;

            if (cpu.isBurstComplete()) {
                contextSwitches++;

                if (cpu.isExecutionComplete()) {
                    logger.log("Process " + cpu.getName() + " execution complete");
                } else {
                    logger.log("Process " + cpu.getName() + " burst complete");
                    readyQueue.add(cpu);
                }

                cpu = null;
                timeCounter = 0;
            } else if (timeCounter >= timeQuantum) {
                logger.log("Time quantum complete for process " + cpu.getName());
                readyQueue.add(cpu);
                cpu = null;
                contextSwitches++;
                timeCounter = 0;
            }
        }

        if (cpu == null && !readyQueue.isEmpty()) {
            cpu = readyQueue.removeFirst();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
        }

        return cpu;
    }
        
}