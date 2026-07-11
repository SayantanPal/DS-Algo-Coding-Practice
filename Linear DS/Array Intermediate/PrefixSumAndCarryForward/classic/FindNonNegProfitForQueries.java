package classic;

/*
* You are working on a project to analyze profit for a given set of days. You have been given an array A with profit for N days.
* You also have Q queries represented by a 2D array B of size Qx2. Each query consists of two integers B[i][0] and B[i][1].
For every query, your task is to find the count of non-negative profit in the range from A[B[i][O]] to A[B[i][1]].
*
* Problem Constraints
|A|= N |B= Q
1<= N, Q <= 105
-109 <= A[i] <= 10°
0 <= B[i][0] <= B[U][1] <= N-1
*
Input Format: First argument A, is an array Second argument B, is a matrix
*
Output Format: Return an array.
*
* Example Input:
Input 1:
A = [1, -1, 0]
B = [[0, 2],
     [1, 2]]

* A = [-1, -2]
B = [[0, 0],
    [1, 1]]

* Example Output:
* Output 1: [2, 1]
* Output 2: [0, 0]
*
* Example Explanation
For Input 1:
Consider 0-based indexing:
Number of non-negative elements from [0, 2] is 2.
Number of non-negative elements from [1, 2] is 1.
For Input 2:
Number of non-negative elements from [0, 0] is 0.
Number of non-negative elements from [1, 1] is 0.
* */
public class FindNonNegProfitForQueries {
    public int[] solve(int[] A, int[][] B) {
        int n = A. length;
        int [] prefixCountPosProfit = new int[n];
        if (A[0] >= 0)
            prefixCountPosProfit [0] = 1;
        for(int i = 1; i < n; i++) {
            if (A[i] > 0) {
                prefixCountPosProfit[1] = prefixCountPosProfit[i - 1] + 1;
            } else {
                prefixCountPosProfit[1] = prefixCountPosProfit[i - 1];
            }
        }
        int q = B.length;
        int [] answers = new int [q];
        for (int i = 0; 1 < q; i++) {
            int l = B[i][0];
            int r = B[i][1];
            if (l == 0) {
                answers[i] = prefixCountPosProfit[r];
            } else {
                answers[1] = prefixCountPosProfit[r] - prefixCountPosProfit[l - 1];
            }
        }
        return answers;
    }
}
