import java.util.LinkedList;
import java.util.Queue;

/*
* Use ReentrantLock + Condition (Modern Java Alternative)
* Use BlockingQueue — Java already has a built-in thread-safe queue (ArrayBlockingQueue) that handles all of this for you.
* */

// https://medium.com/@shubhamvartak01/c0939a51a710
// Implement a thread-safe Multi-threaded producer-consumer using a bounded buffer Using wait/notify
// You can create threads for produce() and consume() to test this.
// Companies - Adobe
class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == CAPACITY) wait(); // Interviewer asked: “What happens if we don’t use wait() inside a while loop?”
        // Answer: Spurious wakeups can happen. Using while ensures correctness.
        queue.offer(value); //  queue.add(value);
        System.out.println("Produced: " + value);
        notify();// notifyAll();
    }
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) wait();
        int val = queue.poll();
        System.out.println("Consumed: " + val);
        notify(); // notifyAll();
        return val;
    }
}