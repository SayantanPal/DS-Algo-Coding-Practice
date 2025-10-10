/*
* Use ReentrantLock + Condition (Modern Java Alternative)
* Use BlockingQueue — Java already has a built-in thread-safe queue (ArrayBlockingQueue) that handles all of this for you.
* */

// https://medium.com/@shubhamvartak01/c0939a51a710
// Implement a thread-safe Multi-threaded producer-consumer using a bounded buffer Using wait/notify
// You can create threads for produce() and consume() to test this.
// Companies - Adobe

// Both threads access the shared resource (the queue) in a thread-safe manner.
// A Producer thread (or threads) generates data and puts it into a shared resource (a queue in this case) and doesn’t add to the queue when it’s full (to avoid overflowing).
// A Consumer thread (or threads) takes data from the shared resource and processes it, and doesn’t try to remove from an empty queue (to avoid underflow).
// wait(): The calling thread releases the lock and waits to be notified
// notify(): Wakes up one of the threads waiting on this object's monitor (likely a consumer).

/*
* If you use if instead of while, you're assuming that: Once you're notified, the condition you were waiting for is guaranteed to be true.
But this assumption is wrong because of:

🌀 Spurious Wakeups:
======================
Threads can sometimes wake up without being notified (due to OS scheduling, JVM behavior, etc.).
If you're using if, the thread will continue even though the prod cond. (queue.size() < CAPACITY) / cons. cond. !queue.isEmpty()) might still be false, leading to:
Producer adding to a full queue → overflow.
Consumer reading from an empty queue → null or exception.
That’s why we always re-check the condition after waking up, using a while loop.
* */

// Note:
// The other waiting thread will never wake up until and unless the notifying thread releases the lock,
// even if the notify is called while acquiring the lock
// Lock is only released when the thread starts waiting using wait() method.

// WHEN non-waiting state - acquired lock
    // adding or not adding notify doesnot make any sense

// WHEN waiting (explictly wait()) - released lock
    // donot add notify - other thread will not wake up/start
        // P.N.: (but for the very first time, when the thread is waiting for CPU time but not using explicitly wait(), then the thread will get 1st turn even if not notified to wake up by other thread)
    // add notify - other thread will wake up/start

class MultiThreadedProducerConsumerBasic {
    // bounded shared buffer used by both producers and consumers
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait();
        }
        data = value;
        available = true;
        System.out.println("Produced: " + data);
        notify();
    }
    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        System.out.println("Consumed: " + data);
        available = false;
        notify();
    }
}