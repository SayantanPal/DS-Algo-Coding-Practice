package classic.fixedsizeslidingwindow;

import java.util.HashMap;

// Link: https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/
// Link: https://www.hellointerview.com/learn/code/sliding-window/maximum-sum-of-distinct-subarrays-with-length-k
// CONSTRAINT: 1 <= nums[i] <= 10^8
public class MaxSumOfKLenSubArrContainingOnlyKDistinctElem {

    // Fixed Sliding Window
    public long maximumSubarraySum_fixedSlidingWindow(int[] nums, int k) {
        long sum = 0;
        long maxSum = 0;
        HashMap<Integer, Long> map = new HashMap<>();
        for(int i = 0; i < k; i++){
            map.put(nums[i],map.getOrDefault(nums[i], 0L) + 1L);
            sum += nums[i];
        }
        if(map.size() == k){
            maxSum = Math.max(maxSum, sum);
        }

        for(int i = k; i < nums.length; i++){
            // Shrink the window
            if(map.containsKey(nums[i - k])){
                map.put(nums[i - k], map.get(nums[i - k]) - 1L);
                if(map.get(nums[i - k]) == 0L){
                    map.remove(nums[i - k]);
                }
            }
            // expand the window
            map.put(nums[i], map.getOrDefault(nums[i], 0L) + 1L);
            sum += nums[i] - nums[i - k];

            // when total keys in hashmap equals k distinct element count
            if(map.size() == k){
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public long maximumSubarraySum_fixedSlidingWindow_v2(int[] nums, int k) {
        long sum = 0;
        long maxSum = 0;
        // HashMap<Integer, Long> map = new HashMap<>();
        int[] freq = new int[100001];
        int freqSize = 0;
        for(int i = 0; i < k; i++){
            // map.put(nums[i],map.getOrDefault(nums[i], 0L) + 1L);
            if(freq[nums[i]] == 0){
                freqSize++;
            }
            freq[nums[i]]++;
            sum += nums[i];
        }
        if(freqSize == k){//if(map.size() == k){
            maxSum = Math.max(maxSum, sum);
        }

        for(int i = k; i < nums.length; i++){
            // if(map.containsKey(nums[i - k])){
            //     map.put(nums[i - k], map.get(nums[i - k]) - 1L);
            //     if(map.get(nums[i - k]) == 0L){
            //         map.remove(nums[i - k]);
            //     }
            // }
            if(freq[nums[i - k]] > 0){
                freq[nums[i - k]]--;
                if(freq[nums[i - k]] == 0){
                    freqSize--;
                }
            }
            // map.put(nums[i],map.getOrDefault(nums[i], 0L) + 1L);
            if(freq[nums[i]] == 0){
                freqSize++;
            }
            freq[nums[i]]++;
            sum += nums[i] - nums[i - k];
            if(freqSize == k){//if(map.size() == k){
                maxSum = Math.max(maxSum, sum);
            }
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
