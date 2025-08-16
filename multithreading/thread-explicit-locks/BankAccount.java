import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private double balance;
    private final Lock lock = new ReentrantLock();

    public BankAccount(double principleBalance) {
        this.balance = principleBalance;
    }

    public void withdraw(double amount) {
        try {
            //  lock.lock(); // wait to Acquire the lock indefinitely
              if(lock.tryLock()) { // Try to acquire the lock instantly if critical section is free
//                lock.tryLock(10000, TimeUnit.MILLISECONDS); // Try to acquire the lock for a definite amount of time
//            lock.tryLock(11000, TimeUnit.MILLISECONDS);
                  System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
                  if (amount <= balance) {
                      System.out.println(Thread.currentThread().getName() + " sees balance as " + amount + " and started withdrawing " + amount);
                      Thread.sleep(10000);
                      balance -= amount;
                  } else {
                      System.out.println("Insufficient funds for withdrawal of " + amount + "." + Thread.currentThread().getName() + " tried to withdraw " + amount + " but balance is only " + balance);
                  }
              }else {
                      System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later!!!");
              }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            lock.unlock(); // Always release the lock in a finally block
        }
        System.out.println(Thread.currentThread().getName() + " completed withdrawal. New balance: " + balance);
    }

    public synchronized void withdraw2(double amount)  {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (amount <= balance) {
                System.out.println("Withdrawing " + amount);
                Thread.sleep(10000);
                balance -= amount;
            } else {
                System.out.println("Insufficient funds for withdrawal of " + amount);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " completed withdrawal. New balance: " + balance);
    }
}