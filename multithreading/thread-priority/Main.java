public class Main {
    public static void main(String[] args) {

        Thread t1 = new PriorityDemoThread("Thread 1");
        Thread t2 = new PriorityDemoThread("Thread 2");
        Thread t3 = new PriorityDemoThread("Thread 3");

        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.NORM_PRIORITY); // 5
        t3.setPriority(Thread.MAX_PRIORITY); // 10

        t1.start();
        t2.start();
        t3.start();
    }
}
