package recursion;

public class PrintSeries {
    public void printOneToA(int A){
        if(A == 0) return;
        printOneToA(A - 1);
        System.out.print(A + " ");
    }

    public void reversePrint(int A){
        if(A == 0) return;
        System.out.print(A + " ");
        reversePrint(A - 1);
    }
    public void solve(int A) {
        printOneToA(A);
        reversePrint(A);
        System.out.println();
    }
}
