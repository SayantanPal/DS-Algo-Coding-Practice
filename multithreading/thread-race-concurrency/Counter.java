public class Counter { // Shared Resource
    private int count = 0;

    public void increment() {
        this.count++; // Increment the count and return the new value
    }

    public int getCount() {
        return this.count;
    }
}
