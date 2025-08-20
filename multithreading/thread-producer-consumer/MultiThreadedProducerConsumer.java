import java.util.LinkedList;
import java.util.Queue;

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

class MultiThreadedProducerConsumer {
    // bounded shared buffer used by both producers and consumers
    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) throws InterruptedException {
        // If the queue is full, the producer waits until a consumer consumes something.
        // Interviewer asked: “What happens if we don’t use wait() inside a while loop?”
        while (queue.size() == CAPACITY) wait();  // start wait and release lock when full and wait/keep on releasing the lock until not empty
        // Answer: Spurious wakeups can happen. Using while ensures correctness.
        queue.offer(value); //  queue.add(value);
        System.out.println("Produced: " + value);
        if(queue.size() == 1) // one time notifying is enough anytime when the control needs to be transferred(i.e., any waiting thread needs to be woken up) after releasing the lock
            notify();// notifyAll(); // If the queue was empty before adding, notify a waiting consumer.
    }
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) wait(); // If the queue is empty, start wait and release lock and keep on waiting & releasing the lock until not full(or until producer adds soemthing to make it full)
        int value = queue.poll(); // Retrieves and removes the head of the queue.
//        Thread.sleep(1000); // Simulate some processing time
        System.out.println("Consumed: " + value);
        if(queue.size() == 4) // one time notifying is enough anytime when the control needs to be transferred(i.e., any waiting thread needs to be woken up) after releasing the lock
            notify(); // notifyAll();
        return value;
    }
}