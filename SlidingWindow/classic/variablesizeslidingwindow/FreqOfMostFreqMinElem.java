package classic.variablesizeslidingwindow;

import java.util.Arrays;

/*
* Problem Description

Given an array of integers A of size N and an integer B.
In a single operation, any one element of the array can be increased by 1. You are allowed to do at most B such operations.
Find the number with the maximum number of occurrences and return an array C of size 2, where C[0] is the number of occurrences, and C[1] is the number with maximum occurrence.
If there are several such numbers, your task is to find the minimum one.

Problem Constraints:
1 <= N <= 105
-109 <= A[i] <= 109
0 <= B <= 109

Input Format:
The first argument given is the integer array A.
The second argument given is the integer B.

Output Format:
Return an array C of size 2, where C[0] is number of occurrence and C[1] is the number with maximum occurence.


Example Input:
Input 1:
 A = [3, 1, 2, 2, 1]
 B = 3

Input 2:
 A = [5, 5, 5]
 B = 3

Example Output

Output 1: [4, 2]
Output 2: [3, 5]


Example Explanation
*
Explanation 1:
Apply operations on A[2] and A[4]
 A = [3, 2, 2, 2, 2]
 Maximum occurence =  4
 Minimum value of element with maximum occurence = 2

* Explanation 2:
 A = [5, 5, 5]
 Maximum occurence =  3
 Minimum value of element with maximum occurence = 5
*
*
*
* */

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
