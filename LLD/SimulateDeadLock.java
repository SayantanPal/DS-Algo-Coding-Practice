// Define Resources: Identify at least two shared resources, such as files, database connections, or memory objects.
// Create Two or More Threads: Have multiple threads that will try to acquire locks on these resources.
/*Locking Order:
Ensure that the threads acquire the locks in a different order:
Thread 1 locks Resource A, then tries to lock Resource B.
Thread 2 locks Resource B, then tries to lock Resource A.
*/

// A. Simulate Delay: Introduce a delay (e.g., using sleep or a long operation) between acquiring the first lock and attempting the second lock. This increases the likelihood of the deadlock occurring.
/* B. Circular Wait: Ensure that the threads are waiting for each other to release the locks:
Thread 1 waits for Resource B while holding Resource A.
        Thread 2 waits for Resource A while holding Resource B.
*/
// C. No Preemption: Ensure that locks cannot be forcibly released. Once a thread acquires a lock, it holds it until the operation is complete.

public class SimulateDeadLock {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (lock2) {
                    System.out.println("Thread 1 done");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (lock1) {
                    System.out.println("Thread 2 done");
                }
            }
        });
        t1.start();
        t2.start();
    }
}