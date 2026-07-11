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
        int cum_curr_sum = 0;
        for(int num: nums){
            cum_curr_sum += num;
            if(lookUp.containsKey(cum_curr_sum - k)){
                result+= lookUp.get(cum_curr_sum - k);
            }
            lookUp.put(cum_curr_sum, lookUp.getOrDefault(cum_curr_sum, 0) + 1);
        }
        return result;
    }
}
