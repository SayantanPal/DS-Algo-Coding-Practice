package searchtargetwithhashingincludingnegatives;

import java.util.Arrays;
/*
* Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:

its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:

A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
* */
// Link: https://leetcode.com/problems/continuous-subarray-sum/description/
public class IsSubArrSumEqualsK {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if(n < 2) return false;

        int[] lookUpRem = new int[k];//HashMap<Integer, Integer> lookUpRem = new HashMap<>();
        Arrays.fill(lookUpRem, -2);   // -2 = "not seen" sentinel
        lookUpRem[0] = -1; // lookUpRem.put(0, -1); // empty prefix — handles subarrays divisible by K starting from index 0
        int countSubArr = 0;
        int currPrefixSum = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += nums[i];
            int remainder = ((currPrefixSum % k) + k) % k;
            if(lookUpRem[remainder] != -2){//if(lookUpRem.containsKey(remainder)){
                if(i - lookUpRem[remainder] >= 2){//if(i - lookUpRem.get(remainder) >= 2){
                    return true;
                }
            }
            if(lookUpRem[remainder] == -2){// if(!lookUpRem.containsKey(remainder)){
                lookUpRem[remainder] = i; //lookUpRem.put(remainder, i);
            }
        }
        return false;
    }
}
