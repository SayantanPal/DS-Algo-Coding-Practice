package basic;

// Link: https://leetcode.com/problems/running-sum-of-1d-array/description/
public class RunningSumOf1DArray {
    public int[] runningSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefixSum[i]  = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }

    public int[] runningSum_v2(int[] nums) {
        int[] prefixSum = new int[nums.length];
        int currPrefixSum = 0;
        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            prefixSum[i] = currPrefixSum;
        }
        return prefixSum;
    }
}
