package basic;

// Link: https://leetcode.com/problems/maximum-product-subarray/description/
public class KadaneMaxProductSubArr {

    public int maxProduct(int[] nums) {
        int minBeforeCurrentIndex = nums[0];
        int maxBeforeCurrentIndex = nums[0];
        int globalMax = nums[0];
        for(int i = 1; i < nums.length;i++){
            int minAtCurrentIndex = Math.min(nums[i], Math.min(nums[i]*minBeforeCurrentIndex, nums[i]*maxBeforeCurrentIndex));
            int maxAtCurrentIndex = Math.max(nums[i], Math.max(nums[i]*minBeforeCurrentIndex, nums[i]*maxBeforeCurrentIndex));
            globalMax = Math.max(globalMax, maxAtCurrentIndex);
            minBeforeCurrentIndex = minAtCurrentIndex;
            maxBeforeCurrentIndex = maxAtCurrentIndex;
        }
        return globalMax;
    }
}
