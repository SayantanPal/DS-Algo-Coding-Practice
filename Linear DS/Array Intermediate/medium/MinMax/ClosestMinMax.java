package medium.MinMax;
/*
* Problem Description
Given an array A, find the size of the smallest subarray such that it contains at least one occurrence of the maximum value of the array
and at least one occurrence of the minimum value of the array.
*
* Input Format
First and only argument is vector A

Output Format
Return the length of the smallest subarray which has at least one occurrence of minimum and maximum element of the array
*
* Example Input

Input 1:
A = [1, 3, 2]
Input 2:

A = [2, 6, 1, 6, 9]
Example Output

Output 1: 2
Output 2: 3
* */

public class ClosestMinMax {
    public int solve(int[] A) {
        int n = A.length;
        int maximum = Integer.MIN_VALUE, minimum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            maximum = Math.max(maximum, A[i]);
            minimum = Math.min(minimum, A[i]);
        }

        // System.out.println("Min: "+ minimum);
        // System.out.println("Max: "+ maximum);

        int lastMin = -1, lastMax = -1;
        int smallestLen = n + 1;
        for(int i = 0; i < n; i++){
            if(A[i] == maximum){
                lastMax = i;
            }
            if(A[i] == minimum){
                lastMin = i;
            }
            if(lastMax != -1 && lastMin != -1){
                smallestLen = Math.min(smallestLen, Math.abs(lastMin - lastMax) + 1);
            }
        }
        return smallestLen;
    }
}
