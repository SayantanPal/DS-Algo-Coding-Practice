package classic;

// Link: https://leetcode.com/problems/minimum-size-subarray-sum/
public class ShortestLengthSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int minLen = nums.length + 1;
        if(nums.length == 0) return 0;
        int sum = 0;
        while(left <= right && right < nums.length){
            sum += nums[right];
            while(sum >= target){
                minLen = Math.min(minLen, right - left + 1);
                sum-=nums[left];
                left++;
            }
            right++;
        }
        return (minLen == nums.length + 1) ? 0 : minLen;
    }
}
