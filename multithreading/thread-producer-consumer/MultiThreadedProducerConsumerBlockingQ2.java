import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;

// 👉 The interviewer was checking if he knew modern concurrency utilities instead of just wait() and notify().
/*
class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Produced: " + i);
                queue.put(i); // waits if queue is full
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                Integer item = queue.take(); // waits if empty
                System.out.println("Consumed: " + item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
*/
public class MultiThreadedProducerConsumerBlockingQ2 {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Runnable producerRunnable = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Produced: " + i);
                    queue.put(i); // waits if queue is full
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumerRunnable = () ->{
            try {
                while (true) {
                    Integer item = queue.take(); // waits if empty
                    System.out.println("Consumed: " + item);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
//        Thread producer = new Thread(new Producer(queue));
//        Thread consumer = new Thread(new Consumer(queue));

        Thread producer = new Thread(producerRunnable);
        Thread consumer = new Thread(consumerRunnable);
        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
