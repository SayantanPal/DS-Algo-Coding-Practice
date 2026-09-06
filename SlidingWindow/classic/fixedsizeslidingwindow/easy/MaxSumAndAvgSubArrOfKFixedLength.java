package classic.fixedsizeslidingwindow.easy;

/*
* Subarray with given sum and length
*
* Problem Description

Given an array A of length N. Also given are integers B and C.
Return 1 if there exists a subarray with length B having sum C and 0 otherwise

Problem Constraints
1 <= N <= 105
1 <= A[i] <= 104
1 <= B <= N
1 <= C <= 109

Input Format
First argument A is an array of integers.
The remaining arguments B and C are integers

Output Format
Return 1 if such a subarray exist and 0 otherwise


Example Input
Input 1:

A = [4, 3, 2, 6, 1]
B = 3
C = 11

*
Input 2:
A = [4, 2, 2, 5, 1]
B = 4
C = 6

* Example Output
Output 1: 1
Output 2: 0


Example Explanation
Explanation 1: The subarray [3, 2, 6] is of length 3 and sum 11.
Explanation 2: There are no such subarray.
*
*
* */

import java.util.ArrayList;

// Link: https://leetcode.com/problems/maximum-average-subarray-i/
// Link: https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k
// Link: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
public class MaxSumAndAvgSubArrOfKFixedLength {

    // Using Variable Length Sliding Window
    public double findMaxAverageUsingVarSlidingWindow(int[] nums, int k) {
        int left = 0, right = 0;
        double sum = 0;
        double maxSum = -1000000.0; //Double.NEGATIVE_INFINITY;
        while(right < nums.length){
            while(right - left + 1 > k){
                sum -= nums[left];
                left++;
            }
            sum += nums[right];
            if(right - left + 1 == k){
                maxSum = Math.max(maxSum, sum);
            }
            right++;
        }
        return maxSum/k;
    }

    public long maxSumUsingPrefixSum_v1(int[] A, int K) {
        int n = A.length;
        long[] prefixSum = new long[n];

        prefixSum[0] = A[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        long maxSum = -1000000; //Double.NEGATIVE_INFINITY;
        int i = 0;
        int j = K - 1;

        // calculate for each pair of indices
        // here N-k iterations
        while (j < n) {
            maxSum = Math.max(maxSum, prefixSum[j] - (i == 0 ? 0 : prefixSum[i - 1]));
            i++;
            j++;
        }

        return maxSum;
    }

    // Using Prefix Sum: TC: O(N + N - K); SC = O(N)
    public long maxSumUsingPrefixSum_v2(int[] A, int K) {
        int n = A.length;
        long[] prefixSum = new long[n];

        prefixSum[0] = A[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        long maxSum = -1000000; //Double.NEGATIVE_INFINITY;
        maxSum = Math.max(maxSum, prefixSum[0 + K - 1] - 0);
        for(int i = 1; i < n - K + 1; i++){
            maxSum = Math.max(maxSum, prefixSum[i + K - 1] - prefixSum[i - 1]); // (i == 0 ? 0 : prefixSum[i - 1])
        }

        return maxSum;
    }

    // Using Fixed Sliding Window: TC: O( K + ( (N - 1) - K + 1) = O(N); SC = O(1)
    public long maxSumUsingFixedSlidingWindow_v1(int[] A, int K) {
        int n = A.length;
        long currSum = 0;
        int i = 0;
        int j = K - 1;
        for(int k = i; k <= j; k++){
            currSum += A[k];
        }

        long maxSum = -1000000;
        j++;
        i++;

        // here N-k iterations
        while (j < n) {
            currSum += A[j] - A[i - 1];
            maxSum = Math.max(maxSum, currSum);
            i++;
            j++;
        }
        return maxSum;
    }

    // Using Fixed Sliding Window: TC: O( K + ( (N - 1) - K + 1) = O(N); SC = O(1)
    public long maxSumUsingFixedSlidingWindow_v2(int[] A, int K) {
        int n = A.length;
        long currSum = 0;
        for(int i = 0; i < K; i++){
            currSum += A[i];
        }

        long maxSum = -1000000;
        maxSum = Math.max(maxSum, currSum);

        for(int i = K; i < n; i++){
            currSum += A[i] - A[i - K];
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }


    // Using Fixed Length sliding window - faster: TC: O( K + ( (N - 1) - K + 1) = O(N); SC = O(1)
    // to-do
    public double findMaxAverageUsingFixedSlidingWindow_v2(int[] nums, int k) {
        double sum = 0;
        double maxSum = -1000000.0; //Double.NEGATIVE_INFINITY;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        // Math.max() call with doubles has overhead
        if(sum > maxSum) maxSum = sum; //maxSum = Math.max(maxSum, sum);
        for(int i = k; i < nums.length; i++){
            sum += nums[i] - nums[i - k];
            // length of subarray currently being scanned is [i - k + 1 to i]
            // Math.max() call with doubles has overhead
            if(sum > maxSum) maxSum = sum; //maxSum = Math.max(maxSum, sum);
        }
        return maxSum/k; //Only divide to double at the return
    }

    public int findSubArrWithLeastAvg(int[] nums, int k) {
        long sum = 0;
        int n = nums.length;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        // double minAvg = sum/B;
        long minSum = sum;
        int startingIndex = 0;
        for(int i = k; i < n; i++){
            sum += nums[i] - nums[i - k];
            // double avg = sum/B;
            // if(avg < minAvg){
            //     minAvg = avg;
            //     startingIndex = i;
            // }
            if(sum < minSum){ // if sum is less, avg is also less since window size is constant k
                startingIndex = i - k + 1;
                minSum = sum;
            }
        }
        return startingIndex;
    }

    public int doesSumCOfWindowSizeBExist(ArrayList<Integer> A, int B, int C) {
        int currSum = 0;
        for(int i = 0; i < B; i++){
            currSum += A.get(i);
        }
        if(currSum == C) return 1;
        for(int i = B; i < A.size(); i++){
            currSum += A.get(i) - A.get(i - B);
            if(currSum == C) return 1;
        }
        return 0;
    }
}
