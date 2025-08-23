import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainDriverExecSrvDemoCallable {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            Future<Integer> result = executorService.submit(() -> {
                try {
//                    System.out.println("Factorial of " + finalI + " is: " + factorial(finalI));
                    return factorial(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            // blocking call to wait for the result
            // result.get() is a blocking call,
            // so the main thread waits for each factorial to complete (3 seconds) before submitting the next task - so curbing the async functionality
            // As a result, you're essentially running tasks sequentially from the main thread’s perspective.
            try {
                System.out.println("Factorial of " + i + " is: " + result.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

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
