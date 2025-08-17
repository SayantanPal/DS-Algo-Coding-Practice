public class DeadlockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        // Deadlock
        //      - a. when one thread when already acquires 1 empty lock
        //      - b. while acquiring that lock, it tries to acquire another lock which is already acquired by another thread

        Thread t1 = new Thread(() -> {
            // empty lock 1
            synchronized(lock1){
                // Thread 1 acquired lock
                System.out.println(Thread.currentThread().getName() + " acquired lock1");
                try{ // simulate doing some work
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " trying to acquire lock2 while holding lock1");
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " acquired lock2");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() ->{
            // empty lock 2
             synchronized(lock2){
                 // Thread 2 acquired lock
                 System.out.println(Thread.currentThread().getName() + " acquired lock2");
                 try{ // simulate doing some work
                     Thread.sleep(1000);
                 } catch(InterruptedException e){
                     e.printStackTrace();
                 }
                 System.out.println(Thread.currentThread().getName() + " trying to acquire lock1 while holding lock2");
                 synchronized (lock1){
                     System.out.println(Thread.currentThread().getName() + " acquired lock1");
                 }
             }
        }, "Thread-2");

//        t1.start();
//        t2.start();

        SR1 sr1 = new SR1();
        SR2 sr2 = new SR2();

        Thread t3 = new Thread(() -> {
            sr1.m1(sr2);
        }, "Thread-3");
        Thread t4 = new Thread(() -> {
            sr2.m1(sr1);
        }, "Thread-4");

        t3.start();
        t4.start();

    }
}
