package searchtarget;

import java.util.HashMap;
import java.util.Map;

/*
 *
 *
 *
 * */
// Link: https://leetcode.com/problems/subarray-sum-equals-k/
public class CountOfSubArrEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> lookUp = new HashMap<>();
        lookUp.put(0, 1);
        int result = 0;
        int currPrefixSum = 0;
        for(int num: nums){
            currPrefixSum += num;
            if(lookUp.containsKey(currPrefixSum - k)){
                result+= lookUp.get(currPrefixSum - k);
            }
            lookUp.put(currPrefixSum, lookUp.getOrDefault(currPrefixSum, 0) + 1);
        }
        return result;
    }
}
