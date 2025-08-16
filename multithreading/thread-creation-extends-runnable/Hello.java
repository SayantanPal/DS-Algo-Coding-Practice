public class Hello {

    public static void main(String[] args) {

        Thread world = new World();

        // Way 1: Using a class that implements Runnable
        Thread worldRunnable = new Thread(new WorldRunnable());

        // Way 2: Using a class that implements Runnable via Anonymous class
        Thread worldRunnable2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;)
                    System.out.println("World Runnable 2");
            }
        });

        // Way 3: Using a class that implements Runnable via Lambda
        Thread worldRunnable3 = new Thread(() -> {
                for(;;)
                    System.out.println("World Runnable 3");
        });

        world.start();
        worldRunnable.start();
        worldRunnable2.start();
        worldRunnable3.start();

        for(;;)
            System.out.println("Hello");
    }
}
