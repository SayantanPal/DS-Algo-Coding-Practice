import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainDriverExecSrvDemoCallableCyclicBarrier {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> result = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        // Improper use of CyclicBarrier can easily cause deadlocks if threads are not enough.
        // If you have fewer threads than the number of parties at the barrier, the program can deadlock.
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
            System.out.println("All tasks have reached the barrier, proceeding to final task.");
        });
        for(int i = 1; i <= 10; i++){
            int finalI = i;
            result.add(executorService.submit(() -> {
                try {
                    int r = factorial(finalI);
                    System.out.println(Thread.currentThread().getName() +" is executing factorial of "+ finalI);
//                    System.out.println("Factorial of " + finalI + " is: " + r);
                    return r;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    cyclicBarrier.await();
                }
            }));
        }

        // blocking call to wait for the result

        executorService.shutdown();

        // when we are focused on selective result, we can directly access that particular future from the list
        // but we need to use cyclic barrier here to ensure all the tasks are completed before we access the result
        // if we are interested in the execution of all the tasks, we can use get() for all callable tasks and cyclic barrier is not required
        try {
            System.out.println("Factorial of 5 is: " + result.get(4).get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
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
