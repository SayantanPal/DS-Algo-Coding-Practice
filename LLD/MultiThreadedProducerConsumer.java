import java.util.LinkedList;
import java.util.Queue;

// https://medium.com/@shubhamvartak01/c0939a51a710
// Implement a thread-safe Multi-threaded producer-consumer using a bounded buffer Using wait/notify
// You can create threads for produce() and consume() to test this.
class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY) wait();
        queue.offer(value);
        System.out.println("Produced: " + value);
        notify();
    }
    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) wait();
        int val = queue.poll();
        System.out.println("Consumed: " + val);
        notify();
    }
}