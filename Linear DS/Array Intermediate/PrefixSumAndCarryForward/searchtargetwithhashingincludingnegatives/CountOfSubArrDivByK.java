package searchtargetwithhashingincludingnegatives;

// Link: https://leetcode.com/problems/subarray-sums-divisible-by-k/
// Link: https://leetcode.com/problems/continuous-subarray-sum/description/

import java.util.HashMap;

/*
* concept: Prefix Sum of subarray from index i to j: (ps[i] - ps[j]) % k == 0
* (ps[i] - ps[j]) % k == 0 => Find: ps[i] % k == ps[j] % k
 * */
public class CountOfSubArrDivByK {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        // HashMap<Integer, Integer> countRemOfK = new HashMap<>();
        // countRemOfK.put(0, 1); // empty array is also a subarray
        int[] countRemOfK = new int[k];
        countRemOfK[0] = 1; // empty prefix — handles subarrays divisible by K starting from index 0
        int countSubArr = 0;
        int currPrefixSum = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += nums[i];
            int remainderOfK = ((currPrefixSum % k) + k) % k;
            // if(countRemOfK.containsKey(remainderOfK)){
            //     countSubArr += countRemOfK.get(remainderOfK);
            // }

            countSubArr += countRemOfK[remainderOfK];
            countRemOfK[remainderOfK]++; // countRemOfK.put(remainderOfK, countRemOfK.getOrDefault(remainderOfK, 0) + 1);
        }
        return countSubArr;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        long currCumPrefixSum = 0L;
        for(int i = 0; i < nums.length; i++){
            currCumPrefixSum += nums[i];
            if(map.containsKey(currCumPrefixSum % k) && i - map.get(currCumPrefixSum % k) >= 2){
                return true;
            }
            if(!map.containsKey(currCumPrefixSum % k))
                map.put(currCumPrefixSum % k, i);
        }
        return false;
    }
}
