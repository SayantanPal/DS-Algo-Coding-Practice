import java.util.concurrent.locks.ReentrantLock;

public class StarvationFixedWithFairLock {
    // Fair lock: threads acquire lock in order of request
    private static final ReentrantLock lock = new ReentrantLock(true); // fairness = true

    public static void main(String[] args) {
        // Starving thread
        Thread starvingThread = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("✅\uD83D\uDD12" + Thread.currentThread().getName() + " Starving thread acquired the lock!");
//                    break;
                } finally {
                    System.out.println("✅\uD83D\uDD12" + Thread.currentThread().getName() + " Starving thread released the lock!");
                    lock.unlock();
                }
            }
        }, "StarvingThread");

        starvingThread.setPriority(Thread.MIN_PRIORITY); // Set high priority for the starving thread

        // Starving thread
        // Donot use tryLock() here, as it may not simulate starvation
//        Thread starvingThread = new Thread(() -> {
//            while (true) {
//                if (lock.tryLock()) {
//                    try {
//                        System.out.println("✅\uD83D\uDD12" + Thread.currentThread().getName() + " Starving thread acquired the lock!");
//                        break;
//                    } finally {
//                        System.out.println("✅\uD83D\uDD12" + Thread.currentThread().getName() + " Starving thread released the lock!");
//                        lock.unlock();
//                    }
//                } else {
//                    System.out.println("❌ " + Thread.currentThread().getName() + " Starving thread couldn't acquire the lock");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt();
//                        break;
//                    }
//                }
//            }
//        }, "StarvingThread");


        // Hogging threads
        Thread hoggingThread = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println("✅\uD83D\uDD12" + Thread.currentThread().getName() + " Hogging thread acquired the lock");
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
//                    break;
                } finally {
                    System.out.println("✅\uD83D\uDD13" + Thread.currentThread().getName() + " Hogging thread released the lock!");
                    lock.unlock();
                }
            }
        }, "HoggingThread-1");
        hoggingThread.setPriority(Thread.MAX_PRIORITY);
        hoggingThread.start();



        // Start the starving thread
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        starvingThread.start();
    }
}
