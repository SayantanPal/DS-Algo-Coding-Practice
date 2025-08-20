import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadedProducerConsumerUsingCondLock {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();   // For producers
    private final Condition notEmpty = lock.newCondition();  // For consumers

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == CAPACITY) {
                notFull.await(); // start wait and release lock when full and wait/keep on releasing the lock until not empty
            }
            queue.offer(value);
            System.out.println("Produced: " + value);
            notEmpty.signal(); // Wake up one waiting consumer
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await(); // start wait and release lock when empty and wait/keep on releasing the lock until not full
            }
            int val = queue.poll();
            System.out.println("Consumed: " + val);
            notFull.signal(); // Wake up one waiting producer
            return val;
        } finally {
            lock.unlock();
        }
    }
}

