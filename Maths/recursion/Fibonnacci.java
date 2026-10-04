package recursion;

public class Fibonnacci {
    public int fib(int A){
        if(A <= 1) return A;
        return fib(A - 1) + fib(A - 2);
    }
    public int findAthFibonacci(int A) {
        return fib(A);
    }
}
