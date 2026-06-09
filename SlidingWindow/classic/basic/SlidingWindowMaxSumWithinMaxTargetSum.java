package classic.basic;

/*
*
*
* */
public class SlidingWindowMaxSumWithinMaxTargetSum {
    public int maxWithinTargetSum(int[] nums, int target) {
        int n = nums.length;
        int currSum = 0;
        int left = 0;
        int maxSum = 0;
        for(int right = 0; right < n; right++){
            currSum+= nums[right];
            while(currSum > target){
                currSum -= nums[left++];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
