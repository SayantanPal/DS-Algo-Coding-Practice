// his method removes a key-value pair from the map.
public class EvenOddThreading {
    int counter = 1;
    int max = 10;

    public synchronized void printOdd() throws InterruptedException {
        while (counter < max) {
            if (counter % 2 == 0) wait();
            System.out.println("Odd: " + counter++);
            notify();
        }
    }
    public synchronized void printEven() throws InterruptedException {
        while (counter < max) {
            if (counter % 2 != 0) wait();
            System.out.println("Even: " + counter++);
            notify();
        }
    }
}