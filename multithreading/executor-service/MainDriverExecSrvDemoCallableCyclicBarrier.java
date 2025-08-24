import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDriverExecSrvDemoCallableCyclicBarrier {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        int totalTasks = 10;
        int batchSize = 3;

        for (int i = 1; i <= totalTasks; i += batchSize) {
            int currentBatchSize = Math.min(batchSize, totalTasks - i + 1); // handle last batch
            CyclicBarrier cyclicBarrier = new CyclicBarrier(currentBatchSize, () -> {
                System.out.println("Batch of " + currentBatchSize + " tasks reached the barrier.");
            });

            for (int j = 0; j < currentBatchSize; j++) {
                int taskNumber = i + j;
                result.add(executorService.submit(() -> {
                    try {
                        int r = factorial(taskNumber);
                        System.out.println(Thread.currentThread().getName() +
                                " executed factorial of " + taskNumber);
                        return r;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            // Handle barrier failure gracefully
                            System.err.println("Barrier broken for task " + taskNumber);
                        }
                    }
                }));
            }
        }

        executorService.shutdown();

        try {
            System.out.println("Factorial of 10 is: " + result.get(8).get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error getting result", e);
        }

        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static int factorial(int n) throws InterruptedException {
        Thread.sleep(3000); // Simulating a long computation
        int p = 1;
        for (int i = 1; i <= n; i++) {
            p *= i;
        }
        return p;
    }
}
