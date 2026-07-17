package searchtargetwithhashingincludingnegatives;


import java.util.HashMap;
/*
* Given an array of integers nums and an integer k.
* A continuous subarray is called nice if there are k odd numbers on it.
Return the number of nice sub-arrays.
*
* */
// Link: https://leetcode.com/problems/count-number-of-nice-subarrays/
public class CountOfSubArrHavingKOdd {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> prefixSumLookUp = new HashMap<>(); //int[] prefixSumLookUp = new int[5000000001];

        prefixSumLookUp.put(0, 1); // 0 odd numbers seen so far before starting to visit

        int currPrefixSum = 0;
        int countOdd = 0;
        int countSubArr = 0;
        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            if(nums[i]%2 != 0)
                countOdd++;
            if(prefixSumLookUp.containsKey(countOdd - k))//if(countOdd >= k)
                countSubArr += prefixSumLookUp.get(countOdd - k); //prefixSumLookUp[countOdd - k];
            prefixSumLookUp.put(countOdd, prefixSumLookUp.getOrDefault(countOdd, 0) + 1); //prefixSumLookUp[currPrefixSum] = countOdd;
        }
        return countSubArr;
    }
}
