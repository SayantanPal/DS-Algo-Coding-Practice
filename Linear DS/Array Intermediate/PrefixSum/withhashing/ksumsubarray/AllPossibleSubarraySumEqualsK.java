package withhashing.ksumsubarray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/subarray-sum-equals-k/
// link: https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
public class AllPossibleSubarraySumEqualsK {

    public int k_sum_subarrays(ArrayList<Integer> nums, int k) {
        int n = nums.size();
        int count = 0;
        // Populate the prefix sum array, setting its first element to 0.
        ArrayList<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(0);
        for (int i = 0; i < n; i++) {
            prefixSum.add(prefixSum.get(prefixSum.size() - 1) + nums.get(i));
        }
        // Loop through all valid pairs of prefix sum values to find all subarrays that sum to 'k'.
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= j; i++) {
                if (prefixSum.get(j) - prefixSum.get(i - 1) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        // Initialize the map with 0 to handle subarrays that sum to 'k' from the start of the array.
        prefixSumMap.put(0, 1);
        int countSubArrays = 0;
        int cumulative_current_sum = 0;
        for(int num: nums){
            // Update the running prefix sum by adding the current number
            cumulative_current_sum += num;

            // If a subarray with sum 'k' exists, increment 'count' by the number of times it has been found.
            // subarrays having pairs at star and end terminals - Pair => LHS:(cumulative_current_sum - k) and RHS: cumulative_current_sum
            // between these pair the sum exists
            if(prefixSumMap.containsKey(cumulative_current_sum - k)){
                // count of such sum formation between (cumulative_current_sum - k) and cumulative_current_sum
                // depends on count of subarray or how many subarray that can form (cumulative_current_sum - k)
                // for each cumulative_current_sum(RHS pair), there can be multiple such (cumulative_current_sum - k)(LHS pairs)
                // count of subarrays look like
                //  [(cumulative_current_sum - k)_1 -> cumulative_current_sum], [(cumulative_current_sum - k)_2 -> cumulative_current_sum], [(cumulative_current_sum - k)_3 -> cumulative_current_sum] and so on and so forth...
                countSubArrays+= prefixSumMap.get(cumulative_current_sum - k);
            }

            // Store the 'currPrefixSum' value in the hash map.
            // curr_prefix_sum is just the qualifier to denote that curr_prefix_sum - k has the potential to be the other pair for the sum k
            // also track curr_prefix_sum as RHS Pair at current state in case if it becomes eligible LHS Pair for some other pair
            // say for k = 4, LHS curr_prefix_sum - k can be 3 and curr_prefix_sum can be 7 and
            // this 7 can also be LHS curr_prefix_sum - k when curr_prefix_sum = 11 where again the k = 4
            prefixSumMap.put(cumulative_current_sum, prefixSumMap.getOrDefault(cumulative_current_sum, 0) + 1);
        }
        return countSubArrays;
    }
}
