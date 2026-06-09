package classic.fixedslidingwindow;

// Link: https://leetcode.com/problems/maximum-average-subarray-i/
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

    // Using Prefix Sum: TC: O(N + N - K + 2); SC = O(N)
    public long maxSumUsingPrefixSum(int[] A, int K) {
        int n = A.length;
        long[] prefixSum = new long[n];

        prefixSum[0] = A[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + A[i];
        }

        long maxSum = 0;
        maxSum = Math.max(maxSum, prefixSum[0 + K - 1] - 0);
        for(int i = 1; i < n - K + 1; i++){
            maxSum = Math.max(maxSum, prefixSum[i + K - 1] - prefixSum[i - 1]); // (i == 0 ? 0 : prefixSum[i - 1])
        }
        return maxSum;
    }

    // Using Fixed Sliding Window: TC: O( K + ( (N - 1) - K + 1) = O(N); SC = O(1)
    public long maxSumUsingFixedSlidingWindow(int[] A, int K) {
        int n = A.length;
        long currSum = 0;
        for(int i = 0; i < K; i++){
            currSum += A[i];
        }

        long maxSum = 0;
        maxSum = Math.max(maxSum, currSum);

        for(int i = K; i < n; i++){
            currSum += A[i] - A[i - K];
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    // Using Fixed Length sliding window - faster: TC: O( K + ( (N - 1) - K + 1) = O(N); SC = O(1)
    // to-do
    public double findMaxAverageUsingFixedSlidingWindow(int[] nums, int k) {
        double sum = 0;
        double maxSum = -1000000.0; //Double.NEGATIVE_INFINITY;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        // Math.max() call with doubles has overhead
        if(sum > maxSum) maxSum = sum; //maxSum = Math.max(maxSum, sum);
        for(int i = k; i < nums.length; i++){
            sum += nums[i] - nums[i - k];
            // Math.max() call with doubles has overhead
            if(sum > maxSum) maxSum = sum; //maxSum = Math.max(maxSum, sum);
        }
        return maxSum/k; //Only divide to double at the return
    }


}
