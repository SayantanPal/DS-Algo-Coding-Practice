package classic.withhashing.longestuniformsubstrafterreplacement;

// Link: https://leetcode.com/problems/max-consecutive-ones-iii/
public class LongestMaxConsecutiveOnesWithKReplacement {
    public int longestOnes(int[] nums, int k) {
        int[] freq = new int[2];
        int n = nums.length;
        int left = 0, right = 0;
        int maxFreqOfOne = 0;
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
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
