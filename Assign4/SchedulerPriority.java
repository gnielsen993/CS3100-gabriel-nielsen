import java.util.PriorityQueue;

public class SchedulerPriority extends SchedulerBase implements Scheduler {
    Logger logger;
    PriorityQueue<Process> readyQueue;

    public SchedulerPriority(Logger logger) {
        this.logger = logger;
        this.readyQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getPriority(), p2.getPriority()));
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
            cpu = readyQueue.poll();
            logger.log("Scheduled: " + cpu.getName());
            contextSwitches++;
            
        }

        return cpu;  
    }
}