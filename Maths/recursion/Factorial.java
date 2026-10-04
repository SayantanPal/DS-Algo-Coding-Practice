package recursion;

public class Factorial {
    public int fact(int A){
        if(A <= 0) return 1;
        return A * fact(A - 1);
    }
    public int solve(int A) {
        return fact(A);
    }
}
