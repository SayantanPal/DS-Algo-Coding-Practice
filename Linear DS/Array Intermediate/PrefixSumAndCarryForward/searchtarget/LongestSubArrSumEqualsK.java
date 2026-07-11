package searchtarget;

import java.util.HashMap;

public class LongestSubArrSumEqualsK {
    public static int getLongestSubarray(int []nums, int k) {
        // Write your code here.
        HashMap<Integer, Integer> lookUp = new HashMap<>();
        int currSum = 0;
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++){
            currSum += nums[i];
            if(!lookUp.containsKey(currSum))
                lookUp.put(currSum, i);

            if(currSum == k)
                maxLen = Math.max(maxLen, i + 1);
            else{
                int remSum = currSum - k;
                if(lookUp.containsKey(remSum))
                    maxLen = Math.max(maxLen, i - lookUp.get(remSum));
            }
        }
        return maxLen;
    }

    public static int getLongestSubarray_v2(int []nums, int k) {
        // Write your code here.
        HashMap<Integer, Integer> lookUp = new HashMap<>();
        int currPrefixSum = 0;
        int maxLen = 0;
        lookUp.put(0, -1); //  to acheive sum of k when index starts of beginning where the other pair is currPrefixSum - k = 0 ie currPrefixSum = k
        for(int i = 0; i < nums.length; i++){
            currPrefixSum += nums[i];
            if(lookUp.containsKey(currPrefixSum - k)){
                maxLen = Math.max(maxLen, i - lookUp.get(currPrefixSum - k));
            }
            if(!lookUp.containsKey(currPrefixSum)){
                lookUp.put(currPrefixSum, i);
            }
        }
        return maxLen;
    }
}
