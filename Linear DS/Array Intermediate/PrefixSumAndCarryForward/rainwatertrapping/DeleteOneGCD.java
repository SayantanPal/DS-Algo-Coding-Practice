package rainwatertrapping;

/*
* Problem Description
You are given an integer array A of size N. You must remove exactly one element.
* Return the maximum possible gcd of the remaining N - 1 elements.
*
* Problem Constraints:
2 <= N <= 105
1 <= A[i] <= 109

Input Format:
The only argument is the integer array A.


Output Format:
Return a single integer, the maximum gcd after removing one element.
*
* */

public class DeleteOneGCD {
    public int gcd(int A, int B){
        if(A == 0) return B;
        return gcd(B % A, A);
    }
    public int solve(int[] A) {
        int n = A.length;
        int[] prefGCD = new int[n];
        int[] sufGCD = new int[n];

        prefGCD[0] = A[0];
        for(int i = 1; i < n; i++){
            prefGCD[i] = gcd(prefGCD[i - 1], A[i]);
        }

        sufGCD[n - 1] = A[n - 1];
        for(int i = n - 2; i >= 0; i--){
            sufGCD[i] = gcd(sufGCD[i + 1], A[i]);
        }

        int maxGCD = sufGCD[1];
        for(int i = 1; i < n - 1; i++){
            maxGCD = Math.max(maxGCD, gcd(prefGCD[i - 1], sufGCD[i + 1]));
        }
        maxGCD = Math.max(maxGCD, prefGCD[n - 2]);

        return maxGCD;
    }
}
