package classic.withhashing.longestsubstrwithoutrepeatingchar;

// Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
// Link: https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-subarrays-of-size-k
// Link: https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
// CONSTRAINT: 1 <= nums[i] <= 10^8
public class MaxSumOfDistinctSubArrWithKElemLenWindow {

    // Fixed Sliding window
    public Integer maximumSubarraySum_v3(int[] nums, Integer k) {
        // Your code goes here
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        maxSum = Math.max(maxSum, sum);

        for(int i = k; i < nums.length; i++){
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    // Variable sliding window
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
