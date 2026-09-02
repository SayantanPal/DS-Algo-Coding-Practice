package basic.subarrays;

/*
* Q. Decreasing dishes
Problem Description
* -----------------
Given an array of N positive integers representing the weights of ingredients in a dish.
Find the maximum possible sum of a subarray with decreasing weights.

Problem Constraints
* -----------------
1 <= N <= 10^5
0 <= A[i] <= 10^5
Sum of all elements of A <= 10^9
*
Input Format
* -----------------
First argument A is an array of integers.
Output Format
Return an integer denoting maximum possible sum of a subarray with strictly decreasing weight!
*
* */

// Constraint: All array elements are +ve
// Constraint: Subarray sums only for monotonically decreasing
public class MaxDescSubArrSumUsingKadane {

    public int maxDecreasingSubarraySum(int[] A) {
        if(A.length == 0) return 0;
        int currSum = A[0];
        int maxSum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                currSum += A[i];
            } else {
                currSum = A[i];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
