/*
* Problem Description

Given two integers A and B, find the greatest possible positive integer M, such that A % M = B % M.

Problem Constraints:
1 <= A, B <= 109
A != B

Input Format:
The first argument is an integer A.
The second argument is an integer B.


Output Format:
Return an integer denoting the greatest possible positive M.
* */

/*
* if say, A>B,
*
* B = A - (A - B)
* => B % (A-B) = A%(A-B) - (A-B)%(A-B)
* => B % (A-B) = A%(A-B) - 0
* => A % (A-B) = B % (A-B) => The number is M = (A-B)
* */
public class LargestDivisorOfAandBWithEqualRemainder {
    public int solve(int A, int B) {
        return Math.abs(A - B);
    }
}
