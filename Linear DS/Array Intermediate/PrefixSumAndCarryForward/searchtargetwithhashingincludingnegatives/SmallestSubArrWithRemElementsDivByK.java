package searchtargetwithhashingincludingnegatives;

import java.util.HashMap;

/*
*Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
A subarray is defined as a contiguous block of elements in the array.
*
* */

// Link: https://leetcode.com/problems/make-sum-divisible-by-p/description/
// constraint: length of smallest subarray that can be removed can be 0
// constraint: whole array cannot be removed
// constraint: array elements can be negative
public class SmallestSubArrWithRemElementsDivByK {

    public int minSubarray(int[] nums, int k) {
        int n = nums.length;
        long totalSum = 0;
        for(int i = 0; i < n; i++) totalSum+= nums[i];
        int rangedTotalSum = (int)(totalSum % k);
        if (rangedTotalSum == 0) return 0; // length of smallest subarray that can be removed can be 0


        HashMap<Integer, Integer> indexForPrevPair = new HashMap<>(); //int[] indexForPrevPair = new int[p];
        int minLen = n;
        // Arrays.fill(indexForPrevPair, -2);
        indexForPrevPair.put(0, -1); // indexForPrevPair[0] = -1; // empty prefix — handles subarrays divisible by K starting from index 0
        long currPrefixSum = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += nums[i];
            int rangedCurrSum = (int)(currPrefixSum % k);
            int normalizedRemainder = ( ( (rangedCurrSum - rangedTotalSum) % k) + k) % k; // modular equation with proper negative normalization
            if(indexForPrevPair.containsKey(normalizedRemainder))
                minLen = Math.min(minLen, i - indexForPrevPair.get(normalizedRemainder));
            indexForPrevPair.put(rangedCurrSum, i); // indexForPrevPair[rangedCurrSum] = i;
        }
        return minLen == n ? -1 : minLen; // whole array cannot be removed
    }
}
