public class ThreadDaemon extends Thread{

    @Override
    public void run(){
        for(;;)
            System.out.println(Thread.currentThread().getName()+" is running");
    }

    public static void main(String[] args) {
        ThreadDaemon td = new ThreadDaemon();
        td.setDaemon(true); // Set the thread as a daemon thread
        td.start(); // Start the daemon thread
    }
}
