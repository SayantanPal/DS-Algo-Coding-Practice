package searchtargetwithhashingincludingnegatives.allpossiblesubarrsumequalsk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/subarray-sum-equals-k/
// link: https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
public class AllPossibleSubArrSumEqualsK {

    public int subarraySum(ArrayList<Integer> nums, int k) {
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

    public int subarraySum_v2(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();

        // Initialize the map with 0 to handle subarrays that sum to 'k' from the start of the array.
        prefixSumMap.put(0, 1);
        int countSubArrays = 0;
        int currPrefixSum = 0;
        for(int num: nums){
            // Update the running prefix sum by adding the current number
            currPrefixSum += num;

            // If a subarray with sum 'k' exists, increment 'count' by the number of times it has been found.
            // subarrays having pairs at star and end terminals - Pair => LHS:(currPrefixSum - k) and RHS: currPrefixSum
            // between these pair the sum exists
            if(prefixSumMap.containsKey(currPrefixSum - k)){
                // count of such sum formation between (currPrefixSum - k) and currPrefixSum
                // depends on count of subarray or how many subarray that can form (currPrefixSum - k)
                // for each currPrefixSum(RHS pair), there can be multiple such (currPrefixSum - k)(LHS pairs)
                // count of subarrays look like
                //  [(currPrefixSum - k)_1 -> currPrefixSum], [(currPrefixSum - k)_2 -> currPrefixSum], [(currPrefixSum - k)_3 -> currPrefixSum] and so on and so forth...
                countSubArrays+= prefixSumMap.get(currPrefixSum - k);
            }

            // Store the 'currPrefixSum' value in the hash map.
            // curr_prefix_sum is just the qualifier to denote that curr_prefix_sum - k has the potential to be the other pair for the sum k
            // also track curr_prefix_sum as RHS Pair at current state in case if it becomes eligible LHS Pair for some other pair
            // say for k = 4, LHS curr_prefix_sum - k can be 3 and curr_prefix_sum can be 7 and
            // this 7 can also be LHS curr_prefix_sum - k when curr_prefix_sum = 11 where again the k = 4
            prefixSumMap.put(currPrefixSum, prefixSumMap.getOrDefault(currPrefixSum, 0) + 1);
        }
        return countSubArrays;
    }
}
