public class ThreadDemo {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount(100d);
        Runnable runnable = () -> {
            sbi.withdraw(50d);
        };

        Thread t1 = new Thread(runnable, "Thread-1");
        Thread t2 = new Thread(runnable, "Thread-2");

        t1.start();
        t2.start();
    }
}
