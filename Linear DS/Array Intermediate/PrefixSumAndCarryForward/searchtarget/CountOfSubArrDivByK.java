package searchtarget;

// Link: https://leetcode.com/problems/subarray-sums-divisible-by-k/

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
}
