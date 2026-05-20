package basic;

public class KadaneContiguousConsecutiveSubArrayWithMaxSum {

    // arr: input array
    // Function to find the sum of contiguous subarray with maximum sum.
    int maxSubarraySum(int[] arr) {
        int n = arr.length;
        if(n == 0) return 0;
        if(n == 1) return arr[0];
        // Your code here
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            currSum += arr[i];
            maxSum = Math.max(maxSum, currSum);
            if(currSum < 0) currSum = 0;
        }
        return maxSum;
    }
}
