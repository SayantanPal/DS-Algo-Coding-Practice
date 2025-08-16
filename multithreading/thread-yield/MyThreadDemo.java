public class MyThreadDemo extends Thread{

    public MyThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0;i < 8;i++){
            System.out.println(Thread.currentThread().getName() + " - " + i);
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        MyThreadDemo t1 = new MyThreadDemo("Thread 1");
        MyThreadDemo t2 = new MyThreadDemo("Thread 2");

        t1.start();
        t2.start();
    }
}
