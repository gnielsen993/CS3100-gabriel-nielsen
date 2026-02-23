import java.util.LinkedList;

public class TaskQueue {
    private final LinkedList <Integer> queue = new LinkedList<>();

    public synchronized void enqueue(int piDigit) {
        queue.addLast(piDigit);
    }

    public synchronized Integer dequeue() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

