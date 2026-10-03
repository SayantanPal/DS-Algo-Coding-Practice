package classic.variablesizeslidingwindow;

import java.util.Arrays;

// Link: https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/
public class FreqOfMostFreqMinElem {

    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long currWindowSum = 0L;
        long maxFreq = Long.MIN_VALUE, minElem = Long.MAX_VALUE;
        for(int right = 0; right < nums.length; right++){
            currWindowSum += nums[right];
            while( ((right - left + 1)*(long)nums[right]) - currWindowSum > k){
                currWindowSum -= nums[left];
                left++;
            }
            if(right - left + 1 > maxFreq){
                maxFreq = right - left + 1;
                minElem = nums[right];
            }else if(right - left + 1 == maxFreq){
                if(nums[right] < minElem){
                    minElem = nums[right];
                }
            }
        }
        //return new int[]{(int)maxFreq, (int)minElem};
        return (int)maxFreq;
    }
}
