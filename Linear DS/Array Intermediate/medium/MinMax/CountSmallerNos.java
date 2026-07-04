package medium.MinMax;

import java.util.Arrays;

// Link: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/description/
public class CountSmallerNos {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] nums2 = nums.clone();
        Arrays.sort(nums2);// Arrays.sort(nums2, Collections.reverseOrder());

        int[] lookUp = new int[101];
        int[] result = new int[nums2.length];

        lookUp[nums2[0]] = 0;
        for(int i = 1; i < nums2.length; i++){
            if(nums2[i] != nums2[i - 1]){
                lookUp[nums2[i]] = i;
            }
        }

        for(int i = 0; i < nums.length; i++){
            result[i] = lookUp[nums[i]];
        }
        return result;
    }
}
