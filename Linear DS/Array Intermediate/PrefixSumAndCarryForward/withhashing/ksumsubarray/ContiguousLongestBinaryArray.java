package withhashing.ksumsubarray;

import java.util.HashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/contiguous-array/
public class ContiguousLongestBinaryArray {

    public int findMaxLength(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) nums[i] = -1;
        }

        int targetSum = 0;

        Map<Integer, Integer> lookUp = new HashMap<>();
        int maxLen = 0, currPrefixSum = 0;

        // in case if currPrefixSum matches K, then 0 is another sum pair
        lookUp.put(0, -1);

        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            if(lookUp.containsKey(currPrefixSum - targetSum)){
                // if from ith index to jth index, the prefix sum diff matches k, then elements between index i+1 to jth index equals target sum
                maxLen = Math.max(maxLen, i - lookUp.get(currPrefixSum - targetSum));// or (i - (lookUp.get(currPrefixSum - targetSum) + 1 ) + 1 )
            }
            // donot update duplicate currprefix Sum with higher index in case if lower index already present to have naturally more longer length
            if(!lookUp.containsKey(currPrefixSum)){
                lookUp.put(currPrefixSum, i);
            }
        }
        return maxLen;
    }
}
