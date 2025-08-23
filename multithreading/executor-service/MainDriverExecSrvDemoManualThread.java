import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainDriverExecSrvDemoManualThread {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        long startTime = System.currentTimeMillis();
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            threads[i - 1] = new Thread(() -> {
                try {
                    System.out.println("Factorial of " + finalI + " is: " + factorial(finalI));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, "Thread " + i);

            threads[i - 1].start();
        }

        // blocking call to wait for all threads to finish
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
    }


    public static int factorial(int n) throws InterruptedException {
        Thread.sleep(1000); // Simulating a long computation
        int p = 1;
        for(int i = 1; i <= n; i++){
            p *= i;
        }
        return p;
    }
}
