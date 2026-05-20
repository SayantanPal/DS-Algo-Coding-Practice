package basic.uniqueduplicatemajority;

import java.util.HashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/majority-element/description/
public class MajorityElementNBy2BoyerMooreVotingAlgo {


    //Overkill - HashMap
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> lookUp = new HashMap<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            lookUp.put(nums[i], lookUp.getOrDefault(nums[i], 0) + 1);
            if(lookUp.get(nums[i]) >= (n/2) + 1){
                return nums[i];
            }
        }
        return -1;
    }


    // Optimized - Boyer Moore Voting Algorithm
    // It works because there is a guarantee that an element with occurrence at least or min. (n/2 + 1) exists
    // Ex - [1,2,1,3,1,4,1] -> [Reset, Decrement, Reset, Decrement, Reset, Decrement, Reset]
    // [1,2,1,3,1,4] is not valid input example
    public int majorityElement(int[] nums) {
        int candidate = -1, count = 0;

        for(int num: nums){
            if(count == 0){ // reset count and candidate
                count = 1;
                candidate = num;
            } else{
                if(num == candidate){
                    count++;
                }else{
                    count--;
                }
            }
        }

        return candidate;
    }
}
