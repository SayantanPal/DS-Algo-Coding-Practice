public class SharedResource {

    public synchronized void accessResource(){
        try {
            Thread.sleep(200); // Simulate some work with sleep
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " accessed the shared resource.");
    }
}
