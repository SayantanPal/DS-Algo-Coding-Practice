package classic.withhashing.longestsubstrwithoutrepeatingchar;

//Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
public class MaxSumOfDistinctSubArrWithKElemLenWindow {
    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        int[] freq = new int[100001];
        long maxSum = 0;
        int distinctCount = 0, left = 0;
        for(int right = 0; right < nums.length; right++){
            if(freq[nums[right]] == 0){
                distinctCount++;
            }
            freq[nums[right]]++;
            sum += nums[right];
            while(right - left + 1 > k){
                freq[nums[left]]--;
                if(freq[nums[left]] == 0){
                    distinctCount--;
                }
                sum -= nums[left];
                left++;
            }

            if(distinctCount == k){
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
