import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadedProducerConsumerUsingCondLockRunnable {
//
//    public void produce(int value) throws InterruptedException {
//        lock.lock();
//        try {
//            while (queue.size() == CAPACITY) {
//                notFull.await(); // start wait and release lock when full and wait/keep on releasing the lock until not empty
//            }
//            queue.offer(value);
//            System.out.println("Produced: " + value);
//            notEmpty.signal(); // Wake up one waiting consumer
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public int consume() throws InterruptedException {
//        lock.lock();
//        try {
//            while (queue.isEmpty()) {
//                notEmpty.await(); // start wait and release lock when empty and wait/keep on releasing the lock until not full
//            }
//            int val = queue.poll();
//            System.out.println("Consumed: " + val);
//            notFull.signal(); // Wake up one waiting producer
//            return val;
//        } finally {
//            lock.unlock();
//        }
//    }

    public static void main(String[] args) {
        final Queue<Integer> queue = new LinkedList<>();
        final int CAPACITY = 5;

        final ReentrantLock lock = new ReentrantLock();

        final Condition notFull = lock.newCondition();   // For producers
        final Condition notEmpty = lock.newCondition();  // For consumers

        MultiThreadedProducerConsumerUsingCondLockRunnable pc = new MultiThreadedProducerConsumerUsingCondLockRunnable();

        Runnable producerRunnable = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                lock.lock();
                try {
                    while (queue.size() == CAPACITY) {
                        notFull.await(); // start wait and release lock when full and wait/keep on releasing the lock until not empty
                    }
                    queue.offer(i);
                    System.out.println("Produced: " + i);
                    notEmpty.signal(); // Wake up one waiting consumer
                } finally {
                    lock.unlock();
                }
                    Thread.sleep(300); // Simulate time taken to produce
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumerRunnable = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    lock.lock();
                    try {
                        while (queue.isEmpty()) {
                            notEmpty.await(); // start wait and release lock when empty and wait/keep on releasing the lock until not full
                        }
                        int val = queue.poll();
                        System.out.println("Consumed: " + val);
                        notFull.signal(); // Wake up one waiting producer
//                        return val;
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(800); // Simulate time taken to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producerRunnable);
        Thread consumerThread = new Thread(consumerRunnable);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

