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
// Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
// Link: https://www.naukri.com/code360/problems/remove-k-corner-elements_2105451?leftPanelTabValue=PROBLEM
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

    static int kCornerElements(int n, int k, ArrayList<Integer> arr) {
        // Write your code here.
        int[] prefixSum = new int[n];
        prefixSum[0] = arr.get(0);
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + arr.get(i);
        }
        int maxSum = 0;
        // pick nothing from left + pick last k from right
        if(k == n) return 0; // all elements picked, return sum 0
        else if(k == 0) return prefixSum[n - 1]; // no elements picked return all remaining sum
        maxSum = Math.max(maxSum, prefixSum[n - 1 - k]);
        for(int i = 1; i < k; i++){
            int pickFromLeft = i;
            int pickFromRight = k - pickFromLeft;

            // when pickFromLeft is picked from left and pickFromRight is picked from right
            // middle sum between index pickFromLeft -> index (n - 1 - pickFromRight)
            maxSum = Math.max(maxSum, prefixSum[n - pickFromRight - 1] -  prefixSum[pickFromLeft - 1]);
        }
        // pick last k from left + pick nothing from right
        maxSum = Math.max(maxSum, prefixSum[n - 1] - prefixSum[k - 1]);

        return maxSum;
    }

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] prefixSumPoints = new int[n];
        prefixSumPoints[0] = cardPoints[0];
        for(int i = 1; i < n ; i++){
            prefixSumPoints[i] = prefixSumPoints[i - 1] + cardPoints[i];
        }
        if(n == k) return prefixSumPoints[n - 1];
        int maxScore = Integer.MIN_VALUE;
        maxScore = Math.max(maxScore, prefixSumPoints[n - 1] - prefixSumPoints[n - 1 - k]);
        for(int i = 1; i < k; i++){
            int pickFromLeft = i;
            int pickFromRight = k - pickFromLeft;
            maxScore = Math.max(maxScore, prefixSumPoints[pickFromLeft - 1] + prefixSumPoints[n - 1] - prefixSumPoints[n - 1 - pickFromRight]);
        }
        maxScore = Math.max(maxScore, prefixSumPoints[k - 1]);
        return maxScore;
    }
}
