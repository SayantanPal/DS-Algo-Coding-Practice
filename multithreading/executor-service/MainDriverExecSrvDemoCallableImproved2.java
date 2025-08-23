import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDriverExecSrvDemoCallableImproved2 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executorService);
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            result.add(executorService.submit(() -> {
                try {
//                    System.out.println("Factorial of " + finalI + " is: " + factorial(finalI));
                    return factorial(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        // blocking call to wait for the result
        // result.get() is a blocking call,
        // so the main thread waits for each factorial to complete (3 seconds) before submitting the next task.
        // As a result, you're essentially running tasks sequentially from the main thread’s perspective.
        // .get() does wait, but only for the individual task’s completion
        // results are always printed sequentially, even if execution is parallel.
        // Because  tasks are running concurrently, but weprinting results sequentially as they finish, in order because we are doing .get(i-1)
        for(int i = 1; i <= 10; i++) {
            try {
                System.out.println("Factorial of " + i + " is: " + completionService.take());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        try {
            if (executorService.awaitTermination(15, TimeUnit.SECONDS)) {
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
