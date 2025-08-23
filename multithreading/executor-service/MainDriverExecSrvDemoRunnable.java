import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainDriverExecSrvDemoRunnable {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            executorService.submit(() -> {
                try {
                    System.out.println("Factorial of " + finalI + " is: " + factorial(finalI));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();

        // blocking call to wait for all threads to finish
        //All 10 tasks are submitted immediately and the executor handles up to 3 at a time concurrently.
        //The main() thread only waits at the end with awaitTermination.
        try {
            if(executorService.awaitTermination(15, TimeUnit.SECONDS)){
                System.out.println("All tasks completed");
            } else {
                System.out.println("Timeout reached before completion");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
//        while(true){
//            // wait for all tasks to finish
//            if(executorService.isTerminated()){
//                System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
//                break;
//            }
//        }
    }

    public static int factorial(int n) throws InterruptedException {
        Thread.sleep(3000); // Simulating a long computation
        int p = 1;
        for(int i = 1; i <= n; i++){
            p *= i;
        }
        return p;
    }
}
