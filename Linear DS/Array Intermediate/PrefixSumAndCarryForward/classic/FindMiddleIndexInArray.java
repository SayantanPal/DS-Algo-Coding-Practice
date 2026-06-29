package classic;

/*
* Given a 0-indexed integer array nums, find the leftmost middleIndex (i.e., the smallest amongst all the possible ones).

A middleIndex is an index where nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1].
If middleIndex == 0, the left side sum is considered to be 0. Similarly, if middleIndex == nums.length - 1, the right side sum is considered to be 0.
Return the leftmost middleIndex that satisfies the condition, or -1 if there is no such index.
*
* */
// Link: https://leetcode.com/problems/find-the-middle-index-in-array/
public class FindMiddleIndexInArray {

    public int findMiddleIndex(int[] nums) {
        if(nums.length == 1) return 0;
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        // for M = 0th index, L = 1st index to R = (n - 1)th index
        if(prefixSum[nums.length - 1] - prefixSum[0] == 0)
            return 0;

        // for M = 1..(n-2)th index
        for(int i = 1; i < nums.length - 1; i++){
            // 0 -> i - 1 | i + 1 -> n - 1
            if(prefixSum[i - 1] == prefixSum[nums.length - 1] - prefixSum[i]){
                return i;
            }
        }
        // for for M = last index n - 1, L = 0th index to R = (n - 2)th index
        if(prefixSum[nums.length - 2] == 0)
            return nums.length - 1;

        return -1;
    }
}
