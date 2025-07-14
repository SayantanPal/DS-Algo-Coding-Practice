import java.util.LinkedList;
import java.util.Queue;

public class SimpleProducerConsumer {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

// Producer
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                queue.offer(i);
                System.out.println("Produced: " + i);
            }
        }).start();
// Consumer
        new Thread(() -> {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            while (!queue.isEmpty()) {
                System.out.println("Consumed: " + queue.poll());
            }
        }).start();
    }
}
