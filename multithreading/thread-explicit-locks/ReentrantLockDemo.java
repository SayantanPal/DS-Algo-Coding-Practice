import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* allows the same thread to repeatedly acquire the same lock multiple times on the resource again without blocking itself/without causing a deadlock.
 * */
public class ReentrantLockDemo {

    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        try {
            lock.lock(); // Acquire the lock
            System.out.println("Outer method");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    public void innerMethod(){
        try{
            lock.lock();
            System.out.println("Inner method");
        }finally {
            lock.unlock(); // Release the lock
        }
    }
}
