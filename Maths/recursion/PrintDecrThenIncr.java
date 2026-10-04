package recursion;

public class PrintDecrThenIncr {

    public void printDecThenInc(int A){
        if(A == 0) return;
        System.out.print(A + " ");
        printDecThenInc(A - 1);
        System.out.print(A + " ");
    }
    public void DecThenInc(int A) {
        printDecThenInc(A);
        System.out.println();
    }
}
