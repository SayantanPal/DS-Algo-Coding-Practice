public class SR1 {

    public synchronized void m1(SR2 sr2){
        System.out.println(Thread.currentThread().getName() + " acquired lock on SR1:m1");
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on SR2:m2");
        sr2.m2();
    }
    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + " acquired lock on SR2:m2");
    }
}
