import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {
    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        try{
            lock.lock();
            String parent = Thread.currentThread().getName();
            System.out.println(Thread.currentThread().getName() + " Locked the OuterMethod");
            Thread t1 = new Thread(() -> {
                System.out.println(parent + " - " + Thread.currentThread().getName() +" Inner Method Called");
                innerMethod(parent);
            }, "R1");
            Thread t2 = new Thread(() -> {
                System.out.println(parent + " - " + Thread.currentThread().getName() +" Inner Method Called");
                innerMethod(parent);
            }, "S1");
            t1.start();
            t2.start();
            Thread.sleep(5000);
//            innerMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            System.out.println(Thread.currentThread().getName() + " unlocked the OuterMethod");
            lock.unlock();
        }
    }

    public void innerMethod(String parent){
        try{
            lock.lock();
            System.out.println(parent + " - " + Thread.currentThread().getName() + " Locked the InnerMethod");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            System.out.println(parent + " - " + Thread.currentThread().getName() + " unlocked the InnerMethod");
            lock.unlock();
        }
    }


}
