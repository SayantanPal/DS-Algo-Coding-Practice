package basic;

import java.util.HashMap;
import java.util.Map;

// Link: https://www.naukri.com/code360/problems/longest-subset-zero-sum_920321?leftPanelTabValue=PROBLEM
// Constraint: Array members can be negative as well
public class LongestLengthSubSequenceWithTargetSumIncludingNegatives {

    public int lenOfLongestSubarr(int[] arr, int k) {
        // code here

        Map<Integer, Integer> map = new HashMap<>();

        int targetSum = k;

        int cumulativeSum = 0;
        int maxLength = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){

            cumulativeSum += arr[i];

            if(!map.containsKey(cumulativeSum))
                map.put(cumulativeSum, i);


            // map.containsKey(targetSum) cannot replace below logic here because
            // there might be multiple existence of target Sum,
            // but only 1st time encountered length is stored in hashMap
            // even if we remove the check of first time occurance of target sum in map, then also this fails
            // because there might be longer length occurance of target sum previously in hashmap which might be overriden by smaller length subseq of target Sum later
            // because we donot know if the smaller length of target sum will occur before or after larger length of target sum and vice versa
            if(cumulativeSum == targetSum){
                maxLength = Math.max(maxLength, i+1);
            }

            if(map.containsKey(cumulativeSum - targetSum)){ // cumulativeSum - targetSum is the index whose element contains the starting index of forming the target
                maxLength = Math.max(maxLength, i - map.get(cumulativeSum - targetSum));
            }
        }

        return maxLength;
    }

    // sliding window works only when all +ve nos
    public int lenOfLongestSubarr2(int[] arr, int target) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];           // expand
            while (sum >= target) {       // shrink while valid
                minLen = Math.min(minLen, right - left + 1);
                sum -= arr[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
