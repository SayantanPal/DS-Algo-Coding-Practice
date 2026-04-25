package withhashing;

import java.util.HashMap;
import java.util.Map;

// Link:
public class AllPossibleSubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> lookUp = new HashMap<>();
        lookUp.put(0, 1);
        int result = 0;
        int cumulative_current_sum = 0;
        for(int num: nums){
            cumulative_current_sum += num;
            // 2 pairs - LHS Pair:(cumulative_current_sum - k) and RHS Pair: cumulative_current_sum
            // between these 2 pairs the sum exists
            if(lookUp.containsKey(cumulative_current_sum - k)){
                // count of such sum formation between (cumulative_current_sum - k) and cumulative_current_sum
                // depends on count of subarray or how many subarray that can form (cumulative_current_sum - k)
                // for each cumulative_current_sum(RHS pair), there can be multiple such (cumulative_current_sum - k)(LHS pairs)
                // count of subarrays look like
                //  [(cumulative_current_sum - k)_1 -> cumulative_current_sum], [(cumulative_current_sum - k)_2 -> cumulative_current_sum], [(cumulative_current_sum - k)_3 -> cumulative_current_sum] and so on and so forth...
                result+= lookUp.get(cumulative_current_sum - k);
            }

            // curr_prefix_sum is just the qualifier to denote that curr_prefix_sum - k has the potential to be the other pair for the sum k
            // also track curr_prefix_sum as RHS Pair at current state in case if it becomes eligible LHS Pair for some other pair
            // say for k = 4, LHS curr_prefix_sum - k can be 3 and curr_prefix_sum can be 7 and
            // this 7 can also be LHS curr_prefix_sum - k when curr_prefix_sum = 11 where again the k = 4
            lookUp.put(cumulative_current_sum, lookUp.getOrDefault(cumulative_current_sum, 0) + 1);
        }
        return result;
    }
}
