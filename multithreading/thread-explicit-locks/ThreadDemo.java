public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
//        BankAccount sbi = new BankAccount(100d);
//        Runnable runnable = () -> {
//            sbi.withdraw(50d);
//        };
//
//        Thread t1 = new Thread(runnable, "Thread-1");
//        Thread t2 = new Thread(runnable, "Thread-2");
//
//        t1.start();
//        t2.start();

        ReentrantLockDemo rld = new ReentrantLockDemo();
        rld.outerMethod(); // Call the outer method which uses a reentrant lock

//        ReentrantLockDemo2 rld2 = new ReentrantLockDemo2();
//        rld2.outerMethod(); // Call the outer method which uses a reentrant lock

    }
}
