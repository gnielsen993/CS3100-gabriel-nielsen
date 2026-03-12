import java.util.LinkedList;

public class SchedulerFCFS extends SchedulerBase implements Scheduler {
    Logger logger;
    LinkedList<Process> readyQueue;

    public SchedulerFCFS(Logger logger) {
        this.logger = logger;
        this.readyQueue = new LinkedList<>();  
    }

    @Override
    public void notifyNewProcess(Process p) {
        readyQueue.add(p);  
    }

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
            cpu = (Process) readyQueue.removeFirst();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
            
        }

        return cpu;  
    }

    
}