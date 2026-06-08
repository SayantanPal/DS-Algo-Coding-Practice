package classic;

// Link: https://leetcode.com/problems/minimum-size-subarray-sum/description/
// Constraint: Array must contain all +ve (non -ve) numbers
public class SlidingWindowMinSizeWithMinTargetSum {

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(right < n){
            sum += nums[right];
            // till the time sum becomes < target, keep on shrinking the left window
            while(sum >= target){ // while left <= right along with sum >= target check is redundant because the moment left crosses( >= ) right, target goes from -ve to zero
                minLen = Math.min(minLen, right - left + 1); // track the all possible lengths for sum >= target to find out the min of them
                sum -= nums[left++]; // as and when window keeps on shrinking under cond. sum >= target, there are possibilities for more min length while satisfying same criteria
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
