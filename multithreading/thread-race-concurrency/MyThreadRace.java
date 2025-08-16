public class MyThreadRace extends Thread{

    private final Counter counter;

    MyThreadRace(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment(); // Increment the counter
        }
    }
}
