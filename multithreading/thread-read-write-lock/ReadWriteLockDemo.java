public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter readWriteCounter = new ReadWriteCounter();

        Runnable readTask = () -> {
            for(int i = 0; i < 9; i++){
                System.out.println(Thread.currentThread().getName() + " read: " + readWriteCounter.getCount());
            }
        };

        Runnable writeTask = () -> {
            for(int j = 0; j < 9; j++){
                readWriteCounter.increment();
                System.out.println(Thread.currentThread().getName() + " incremented.");
            }
        };

        Thread readThread1 = new Thread(readTask, "Thread-1");
        Thread readThread2 = new Thread(readTask, "Thread-2");
        Thread writeThread = new Thread(writeTask, "Thread-3");

        writeThread.start();
        readThread1.start();
        readThread2.start();

        writeThread.join();
        readThread1.join();
        readThread2.join();
    }
}
