import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


// Use BlockingQueue — Java already has a built-in thread-safe queue (ArrayBlockingQueue) that handles all of this for you.
// // 👉 The interviewer was checking if he knew modern concurrency utilities instead of just wait() and notify().
public class MultiThreadedProducerConsumerBlockingQ {
    private final int CAPACITY = 5;
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

    // No need to notify, BlockingQueue handles it internally

    public void produce(int value) throws InterruptedException {
        queue.put(value); // blocks if full
        System.out.println("Produced: " + value);
    }

    public int consume() throws InterruptedException {
        int value = queue.take(); // blocks if empty
        System.out.println("Consumed: " + value);
        return value;
    }
}
