package classic;

// Link: https://leetcode.com/problems/maximum-average-subarray-i/
public class MaxAvgSubArr {

    // Using Variable Length Sliding Window
    public double findMaxAverage(int[] nums, int k) {
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

    // Using Fixed Length sliding window - faster
    // to-do
}
