package searchtargetwithhashingincludingnegatives;

import java.util.Arrays;
/*
* Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
A subarray is a contiguous part of the array.
*
* */
// Link: https://leetcode.com/problems/binary-subarrays-with-sum/submissions/2064654078/
public class CountOfSubArrEqualsK {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        // arrays are possible when array elements are guranteed to be non-negative
        // in case if the array elem happens to be negative, then negative sum cannot be stored in array indexes
        int[] prefixSumLookUp = new int[n + 1]; // when array filled with all 1's, sum will overshoot the index
        prefixSumLookUp[0] = 1;
        int currPrefixSum = 0;
        int countSubArr = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += nums[i];
            if(currPrefixSum >= goal){ // eliminating the possibility of negative sum because all array elems in given prob are only positive
                countSubArr += prefixSumLookUp[currPrefixSum - goal];
            }
            prefixSumLookUp[currPrefixSum]++;
        }
        return countSubArr;
    }
}
