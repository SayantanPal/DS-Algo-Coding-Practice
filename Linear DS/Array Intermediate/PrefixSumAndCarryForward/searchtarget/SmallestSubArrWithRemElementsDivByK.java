package searchtarget;

import java.util.HashMap;

/*
*Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
A subarray is defined as a contiguous block of elements in the array.
*
* */

// Link: https://leetcode.com/problems/make-sum-divisible-by-p/description/
public class SmallestSubArrWithRemElementsDivByK {

    public int minSubarray(int[] nums, int k) {
        int n = nums.length;
        long totalSum = 0;
        for(int i = 0; i < n; i++) totalSum+= nums[i];
        int target = (int)(totalSum % k);
        if (target == 0) return 0;


        HashMap<Integer, Integer> indexForPrevPair = new HashMap<>(); //int[] indexForPrevPair = new int[p];
        int minLen = n;
        // Arrays.fill(indexForPrevPair, -2);
        indexForPrevPair.put(0, -1); // indexForPrevPair[0] = -1; // empty prefix — handles subarrays divisible by K starting from index 0
        long currSum = 0;
        for(int i = 0; i < n; i++){
            currSum += nums[i];
            int rangedCurrSum = (int)(currSum % k);
            int normalizedRemainder = ( ( (rangedCurrSum - target) % k) + k) % k; // modular equation with proper negative normalization
            if(indexForPrevPair.containsKey(normalizedRemainder))
                minLen = Math.min(minLen, i - indexForPrevPair.get(normalizedRemainder));
            indexForPrevPair.put(rangedCurrSum, i); // indexForPrevPair[rangedCurrSum] = i;
        }
        return minLen == n ? -1 : minLen;
    }
}
