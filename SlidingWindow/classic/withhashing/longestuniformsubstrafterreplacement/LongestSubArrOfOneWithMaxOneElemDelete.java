package classic.withhashing.longestuniformsubstrafterreplacement;

// Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
public class LongestSubArrOfOneWithMaxOneElemDelete {
    public int longestSubarray(int[] nums) {
        int[] freq = new int[2];

        int left = 0, right = 0;
        int n = nums.length;
        int k = 1;
        int maxFreqOfOne = Integer.MIN_VALUE;
        int maxLen = 0;

        while(right < n){
            freq[nums[right]]++;
            maxFreqOfOne = Math.max(maxFreqOfOne, freq[1]);
            int windowSize = right - left + 1;
            while(windowSize - maxFreqOfOne > k){
                windowSize--;
                freq[nums[left]]--;
                left++;
            }
            // if all 1, then also delete 1 element
            maxLen = Math.max(maxLen, (right - left + 1) - 1);
            right++;
        }
        return maxLen;
    }
}
