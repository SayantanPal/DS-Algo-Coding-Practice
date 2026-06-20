package classic;

import java.util.ArrayList;

/*
* Problem Description

You are given an integer array A of size N.
You have to perform B operations.
* In one operation, you can remove either the leftmost or the rightmost element of the array A.
Find and return the maximum possible sum of the B elements that were removed after the B operations.

* NOTE: Suppose B = 3, and array A contains 10 elements, then you can:

Remove 3 elements from front and 0 elements from the back, OR
Remove 2 elements from front and 1 element from the back, OR
Remove 1 element from front and 2 elements from the back, OR
Remove 0 elements from front and 3 elements from the back.
*
*
* Problem Constraints
1 <= N <= 105
1 <= B <= N
-103 <= A[i] <= 103
*
* Output Format: Return an integer denoting the maximum possible sum of elements you removed.
* */
// Link: https://www.interviewbit.com/problems/pick-from-both-sides/
public class PickFromBothSides {
    public int solve(int[] A, int B) {
        int n = A.length;
        int[] prefixSumLeftToRight = new int[n];
        int[] prefixSumRightToLeft = new int[n];

        prefixSumLeftToRight[0] = A[0];
        for (int i = 1; i < n; i++) {
            prefixSumLeftToRight[i] = prefixSumLeftToRight[i - 1] + A[i];
        }

        prefixSumRightToLeft[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            prefixSumRightToLeft[i] = prefixSumRightToLeft[i + 1] + A[i];
        }

        int maxSum = Integer.MIN_VALUE;
        maxSum = Math.max(maxSum, prefixSumLeftToRight[B - 1]);

        for (int i = 1; i < B; i++) {
            int a = i;
            int b = B - i;
            maxSum = Math.max(maxSum, prefixSumLeftToRight[a - 1] + prefixSumRightToLeft[(n - 1) - b + 1]);
        }
        maxSum = Math.max(maxSum, prefixSumRightToLeft[(n - 1) - B + 1]);

        return maxSum;
    }

    public int solve_v2(ArrayList<Integer> A, int B) {
        int n = A.size();
        int k = B;
        int[] prefixSum = new int[n];
        prefixSum[0] = A.get(0);
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + A.get(i);
        }
        int maxSum = Integer.MIN_VALUE;

        // pick nothing from left + pick last k from right
        if(k == n){
            return prefixSum[n - 1]; // all elements picked, return all sum
        }else if(k == 0){ // no elements picked, return 0
            return 0;
        }
        maxSum = Math.max(maxSum, prefixSum[n - 1] - prefixSum[n - 1 - k]);
        for(int i = 1; i < k; i++){
            int pickFromLeft = i;
            int pickFromRight = k - pickFromLeft;

            // when pickFromLeft is picked from left and pickFromRight is picked from right
            // left part sum between index 0 -> index (pickFromLeft - 1)
            // right part sum between index (n - pickFromRight) -> index (n - 1)
            int sumFromLeft = prefixSum[pickFromLeft - 1];
            int sumFromRight = prefixSum[n - 1] - prefixSum[n - 1 - pickFromRight];
            maxSum = Math.max(maxSum, sumFromLeft + sumFromRight);
        }
        // pick last k from left + pick nothing from right
        maxSum = Math.max(maxSum, prefixSum[k - 1]);

        return maxSum;
    }
}
