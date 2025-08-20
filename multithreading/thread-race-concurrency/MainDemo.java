public class MainDemo {


/*
* Thread race/Data Race -
* Lets say I have two thread t1 & t2 both accessing AtomicLong variable count.
    Lets say thread t1 reads count and thread t2 was busy doing counter.increment() operation.
    In such a scenario, either all of t2 has executed or none of it.
    * Either thread t1 will win the race (it sees the initial value),
    * or thread t2 will win the race (thread t1 sees the final value.)
    * Unless you have provided some explicit means to force the threads to access the variable in a particular order,
    * then there is no way to know who will win the race.

* The situation you have described is called a "data race." Either thread A will win the race (it sees the initial value), or thread B will win the race (thread A sees the final value.) Unless you have provided some explicit means to force the threads to access the variable in a particular order, then there is no way to know who will win the race.

    "Atomicity" means that thread t1 will either see the data as it was before the atomic operation,
    * or it will see it after the operation is complete.
    * Thread t1 can never see it in a half-way-done state.

    A) Thread t1 see the previous value of count (because count is not yet updated)
    Or
    B) Thread t1 will wait until Thread t2 completes the operation and see the latest value.
* */

    public static void main(String[] args) {

        Counter counter = new Counter(); // Create a shared counter object
        MyThreadRace t1 = new MyThreadRace(counter); // Create first thread
        MyThreadRace t2 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t3 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t4 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t5 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t6 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t7 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t8 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t9 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t10 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t11 = new MyThreadRace(counter); // Create second thread
        MyThreadRace t12 = new MyThreadRace(counter); // Create second thread
//        MyThreadRace t13 = new MyThreadRace(counter); // Create second thread
//        MyThreadRace t14 = new MyThreadRace(counter); // Create second thread


        t1.start(); // Start the first thread
        t2.start(); // Start the second thread
        t3.start(); // Start the third thread
        t4.start(); // Start the fourth thread
        t5.start(); // Start the fifth thread
        t6.start(); // Start the sixth thread
        t7.start(); // Start the seventh thread
        t8.start(); // Start the eighth thread
        t9.start(); // Start the ninth thread
        t10.start(); // Start the tenth thread
        t11.start(); // Start the eleventh thread
        t12.start(); // Start the twelfth thread
//        t13.start(); // Start the thirteenth thread
//        t14.start(); // Start the fourteenth thread
        try{
            t1.join(); // Wait for the first thread to finish
            t2.join(); // Wait for the second thread to finish
            t3.join(); // Wait for the third thread to finish
            t4.join(); // Wait for the fourth thread to finish
            t5.join(); // Wait for the fifth thread to finish
            t6.join(); // Wait for the sixth thread to finish
            t7.join(); // Wait for the seventh thread to finish
            t8.join(); // Wait for the eighth thread to finish
            t9.join(); // Wait for the ninth thread to finish
            t10.join(); // Wait for the tenth thread to finish
            t11.join(); // Wait for the eleventh thread to finish
            t12.join(); // Wait for the twelfth thread to finish
//            t13.join(); // Wait for the thirteenth thread to finish
//            t14.join(); // Wait for the fourteenth thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle any interruption exceptions
        }

        System.out.println("Final counter value: " + counter.getCount()); // Print the final value of the counter
    }
}
