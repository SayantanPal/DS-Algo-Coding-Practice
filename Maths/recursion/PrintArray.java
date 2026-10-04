package recursion;

public class PrintArray {
    public void printArr(int[] A, int i, int N){
        if(i == N){
            System.out.println(A[i] + " ");
            return;
        }
        System.out.print(A[i] + " ");
        printArr(A, i + 1, N);
    }

    public void printArr(int[] A, int n){
        if(n < 0){ return; } // if(n == 0){  System.out.print(A[0] + " "); return;}
        printArr(A, n - 1);
        System.out.print(A[n] + " ");
    }

    public void solve(int[] A) {
        printArr(A, A.length - 1);
        System.out.println();
        printArr(A, 0, A.length - 1);
    }
}
