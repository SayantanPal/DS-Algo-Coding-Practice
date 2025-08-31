public class CounterVolatile { // Shared Resource
    public volatile int count = 0;

    public void increment() {
        this.count++; // Increment the count and return the new value
    }

    public int getCount() {
        return this.count;
    }

    public int setCount(int count) {
        return this.count = count;
    }
}
