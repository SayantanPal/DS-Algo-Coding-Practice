package recursion;

public class Power {
    public long pow(int A, int B){
        if(B == 0) return 1;
        long p = pow(A, B/2);
        return (B & 1) == 0 ? p*p : A*p*p;
    }

    // pow(A, B)
    public long power(int A, int B) {
        return pow(A, B);
    }

    // pow(A, B) % C
    //-10^9 <= A <= 109
    // 0 <= B <= 10^9
    // 1 <= C <= 10^9
    public int pow(int A, int B, int C) {
        if(A == 0) return 0;
        if(B == 0) return 1;
        A = ((A % C) + C) % C;


        long x = pow(A, B / 2, C) % C; // Max value of x is C - 1 ie 10^9 - 1
        long result;
        if(B % 2 == 0)
            result = (x * x) % C;
        else
            result = (x * x % C) * (A % C) % C;
        return (int) result;
    }
}
