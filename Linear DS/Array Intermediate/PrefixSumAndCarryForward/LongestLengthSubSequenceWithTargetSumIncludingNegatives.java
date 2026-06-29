import java.util.HashMap;
import java.util.Map;

// Link: https://www.naukri.com/code360/problems/longest-subset-zero-sum_920321?leftPanelTabValue=PROBLEM
//       https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
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

    public int longestSubarrayV2(int[] arr, int k) {
        Map<Integer, Integer> prefixSum = new HashMap<>();

        // either use technique a or b
        prefixSum.put(0, -1); // technique a
        // OR
        //  technique b inside loop
        /*
        if(cumulativeSum == targetSum){
            maxLength = Math.max(maxLength, i+1);
        }*/

        int currPrefixSum = 0;
        int maxLen = 0;
        for(int i = 0; i < arr.length; i++){
            currPrefixSum += arr[i];
            // rightPrefixSum - leftPrefixSum = target sum k
            // you cannot scan right index because at ith step, future is not visited or traced out yet - only left sum is traced out and becomes past history to refer
            // so, leftPrefixSum = rightPrefixSum - target sum k
            if(prefixSum.containsKey(currPrefixSum - k)){
                // subarray ranges from index (prefixSum.get(currPrefixSum - k) + 1) to i
                maxLen = Math.max(maxLen, i - (prefixSum.get(currPrefixSum - k) + 1) + 1);
            }

            if (!prefixSum.containsKey(currPrefixSum)) { // if it contains curr prefix sum at much lower indexes, then do not overwrite so that greedily algorithm can pick the same sum achieved from compartively lower index to get longer length
                prefixSum.put(currPrefixSum, i);
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
