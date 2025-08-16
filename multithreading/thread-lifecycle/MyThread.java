public class MyThread extends Thread{

    @Override
    public void run(){
        try {
            Thread.sleep(2000); // Simulate some work with sleep
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        System.out.println("1 - " + myThread.getState());
        myThread.start(); // Start the thread
        System.out.println("2 - " + myThread.getState());
        System.out.println("3 - " + myThread.getState());

        Thread.sleep(100);
        System.out.println("3 - " + myThread.getState());

        myThread.join();
        System.out.println("4 - " + myThread.getState());
    }
}
