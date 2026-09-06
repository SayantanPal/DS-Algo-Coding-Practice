package basic.subarrays;

/*
* Heads and Tails
Problem Description

You are given a binary string A of length N, which represents the results of a series of coin tosses. 0 represents tails and 1 represents heads. You want to
find the length of the longest streak of consecutive heads.


Problem Constraints
1 <= N <= 105

String A contains only characters '0' and '1'.

Input Format: Only argument is a string A.
Output Format: Return an integer denoting the length of the longest streak of consecutive heads.


Example Input
Input 1: A = "101110"

Input 2: A = "110101"



Example Output
Output 1: 3
Output 2: 2


Example Explanation
Explanation 1:

The longest streak of consecutive head in A is = "101110" is highlighted.
Explanation 2:

The longest streak of consecutive head in A is = "110101" is highlighted.


* */

public class LengthOfLongestConsecutiveOnes {

    public int solve(String A) {
        int maxLen = 0, currLen = 0;
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i) == '1'){
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            }else{
                currLen = 0;
            }
        }
        return maxLen;
    }
}
