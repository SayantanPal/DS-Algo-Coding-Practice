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
// Link: https://www.naukri.com/code360/problems/remove-k-corner-elements_2105451?leftPanelTabValue=PROBLEM
public class PickFromBothSidesVariant2 {
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
}
