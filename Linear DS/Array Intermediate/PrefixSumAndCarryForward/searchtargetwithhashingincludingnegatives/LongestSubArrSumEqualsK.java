package searchtargetwithhashingincludingnegatives;

import java.util.HashMap;


// Link: https://www.naukri.com/code360/problems/longest-subset-zero-sum_920321?leftPanelTabValue=PROBLEM
//       https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
// Constraint: Array members can be negative as well
public class LongestSubArrSumEqualsK {
    public static int getLongestSubarray(int []nums, int k) {
        // Write your code here.
        HashMap<Integer, Integer> lookUp = new HashMap<>();
        int currPrefixSum = 0;
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            if(!lookUp.containsKey(currPrefixSum))
                lookUp.put(currPrefixSum, i);

            // map.containsKey(targetSum) cannot replace below logic here because
            // there might be multiple existence of target Sum,
            // but only 1st time encountered length is stored in hashMap
            // even if we remove the check of first time occurance of target sum in map, then also this fails
            // because there might be longer length occurance of target sum previously in hashmap which might be overriden by smaller length subseq of target Sum later
            // because we donot know if the smaller length of target sum will occur before or after larger length of target sum and vice versa
            if(currPrefixSum == k)
                maxLen = Math.max(maxLen, i + 1);
            else{
                int remSum = currPrefixSum - k;
                if(lookUp.containsKey(remSum))
                    maxLen = Math.max(maxLen, i - lookUp.get(remSum));
            }
        }
        return maxLen;
    }

    public static int getLongestSubarray_v2(int []nums, int k) {
        // Write your code here.
        HashMap<Integer, Integer> lookUp = new HashMap<>();

        // either use technique a or b
        lookUp.put(0, -1); //  technique a to acheive sum of k when index starts of beginning where the other pair is currPrefixSum - k = 0 ie currPrefixSum = k
        // OR
        //  technique b inside loop
        /*
        if(currPrefixSum == targetSum){
            maxLength = Math.max(maxLength, i+1);
        }*/

        int currPrefixSum = 0;
        int maxLen = 0;

        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            // rightPrefixSum - leftPrefixSum = target sum k
            // you cannot scan right index because at ith step, future is not visited or traced out yet - only left sum is traced out and becomes past history to refer
            // so, leftPrefixSum = rightPrefixSum - target sum k
            if(lookUp.containsKey(currPrefixSum - k)){
                // subarray ranges from index (prefixSum.get(currPrefixSum - k) + 1) to i
                maxLen = Math.max(maxLen, i - lookUp.get(currPrefixSum - k));
            }
            if(!lookUp.containsKey(currPrefixSum)){ // if it contains curr prefix sum at much lower indexes, then do not overwrite so that greedily algorithm can pick the same sum achieved from comparatively lower index to get longer length
                lookUp.put(currPrefixSum, i);
            }
        }
        return maxLen;
    }

    // sliding window works only when all +ve nos
    // Take violating example: 10, 5, 2, 7, 1, -10
    // once target becomes greater, window starts sqeezing out ignoring the possibility of future negative value which could have nullified higher curr cumulative sum value
    public int lenOfLongestSubarr2(int[] arr, int target) {
        int sum = 0;
        int maxLen = 0;
        int left = 0;
        for(int right = 0; right < arr.length; right++){
            sum += arr[right];
            while(sum > target){
                sum -= arr[left++];
            }
            if(sum == target){
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
}
