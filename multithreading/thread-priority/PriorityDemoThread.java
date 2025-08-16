public class PriorityDemoThread extends Thread{

    PriorityDemoThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread is running...");
        for(int i = 1; i <= 5; i++){
            for(int j = 0; j < 4; j++){
                System.out.println("Thread: " + Thread.currentThread().getName()
                        + " - Priority: " + Thread.currentThread().getPriority()
                        + " - Count: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
