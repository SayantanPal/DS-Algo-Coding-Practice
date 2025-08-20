public class MainDriver {

    public static void main(String[] args) throws InterruptedException {
        MultiThreadedProducerConsumer pc = new MultiThreadedProducerConsumer(); // shared resopurce
        MultiThreadedProducerConsumerBlockingQ pcbq = new MultiThreadedProducerConsumerBlockingQ(); // shared resopurce

        Thread producerThread = new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                try {
                    pcbq.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Producer Thread");

        Thread consumerThread = new Thread(() -> {
            for(int i = 1; i <= 10; i++){
                try {
                    pcbq.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Consumer Thread");

        // Start both threads
        // The producer will produce 15 items and the consumer will consume 15 items.
        producerThread.start();
        consumerThread.start();
    }
}
