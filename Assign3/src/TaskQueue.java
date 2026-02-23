import java.util.LinkedList;

/**
 * TaskQueue is a thread-safe class that manages a queue of digit positions for computing the digits of Pi. It provides methods to enqueue digit positions, dequeue them for processing by worker threads, and check if the queue is empty. The synchronized methods ensure that multiple worker threads can safely access and modify the queue without
 * @author Gabriel Nielsen
 */
public class TaskQueue {
    private final LinkedList <Integer> queue = new LinkedList<>();

    /**
     * Enqueues a digit position into the task queue. This method is synchronized to ensure thread safety when multiple worker threads are adding tasks to the queue.
     * @param piDigit the digit position to be added to the queue for computation
     */
    public synchronized void enqueue(int piDigit) {
        queue.addLast(piDigit);
    }

    /**
     * Dequeues a digit position from the task queue for processing by a worker thread. This method is synchronized to ensure thread safety when multiple worker threads are retrieving tasks from the queue. If the queue is empty, it returns null.
     * @return the digit position retrieved from the queue, or null if the queue is empty
     */
    public synchronized Integer dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    /**
     * Checks if the task queue is empty. This method is synchronized to ensure thread safety when multiple worker threads are checking the state of the queue.
     * @return true if the queue is empty, false otherwise
     */
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

