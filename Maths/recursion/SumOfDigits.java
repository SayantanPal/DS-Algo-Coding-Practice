package recursion;

public class SumOfDigits {

    public int sumOfDigitsRec(int A){
        if(A == 0) return 0;
        return (A % 10) + sumOfDigitsRec(A / 10);
    }
    public int solve(int A) {
        return sumOfDigitsRec(A);
    }

}
