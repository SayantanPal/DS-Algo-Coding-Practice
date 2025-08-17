public class SR2 {

    public synchronized void m1(SR1 sr1){
        System.out.println(Thread.currentThread().getName() + " acquired lock on SR2:m1");
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on SR1:m2");
        sr1.m2();
    }
    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName() + " acquired lock on SR2:m2");
    }
}
