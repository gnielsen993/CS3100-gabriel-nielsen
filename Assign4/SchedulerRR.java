import java.util.LinkedList;

public class SchedulerRR extends SchedulerBase implements Scheduler {
    Logger logger;
    LinkedList<Process> readyQueue;
    int timeQuantum;
    int timeCounter;

    public SchedulerRR(Logger logger, int timeQuantum) {
        this.logger = logger;
        this.readyQueue = new LinkedList<>();  
        this.timeQuantum = timeQuantum;
        this.timeCounter = 0;
    }

    @Override
    public void notifyNewProcess(Process p) {
        readyQueue.add(p);
    }

    @Override
    public Process update(Process cpu) {
        if (cpu != null) {
            timeCounter++;

            if (cpu.isBurstComplete()) {
                logger.log("Process " + cpu.getName() + " burst complete");
                contextSwitches++;

                if (cpu.isExecutionComplete()) {
                    logger.log("Process " + cpu.getName() + " execution complete");
                } else {
                    readyQueue.add(cpu);  
                }

                cpu = null;
                timeCounter = 1;
            } else if (timeCounter >= timeQuantum) {
                logger.log("Time quantum completed for process " + cpu.getName());
                readyQueue.add(cpu);  
                cpu = null;
                contextSwitches++;
                timeCounter = 1;
            }
        }

        if (cpu == null && !readyQueue.isEmpty()) {
            cpu = (Process) readyQueue.removeFirst();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
        }

        return cpu;
    }
        
}