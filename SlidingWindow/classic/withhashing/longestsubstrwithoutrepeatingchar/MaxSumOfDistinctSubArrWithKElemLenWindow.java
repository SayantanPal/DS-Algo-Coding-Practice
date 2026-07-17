package classic.withhashing.longestsubstrwithoutrepeatingchar;

//Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
public class MaxSumOfDistinctSubArrWithKElemLenWindow {
    public long maximumSubarraySum_v2(int[] nums, int k) {
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

    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        int[] freq = new int[100001];
        long maxSum = 0;
        int left = 0;
        for(int right = 0; right < nums.length; right++){
            freq[nums[right]]++;
            sum += nums[right];
            // either of the expansion can also keep on growing on these violating conditions if we dont shrink on encountering either of them
            // if we allow to grow or expand based on one cond while ignoring other, we loose out on some possibilies
            while(freq[nums[right]] > 1 || right - left + 1 > k){
                freq[nums[left]]--;
                sum -= nums[left];
                left++;
            }
            if(right - left + 1 == k){
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
