package novice;

/*
* Problem Description
Given an array A, check if it is sorted in non-decreasing order or not.
Non-decreasing order means that the numbers in a sequence can stay the same or increase, but they cannot decrease.


Problem Constraints
1 <= Ai <= 109
1 <= |A| <= 105

Input Format
The first and only argument contains an integer array A.

Output Format
Return 1 if array is sorted else return 0.
*
* Input 1: A = [1, 2, 2]
* Output 1: 1
*
* Input 2: A = [1, 2, 1]
* Output 2: 0
*
* */

public class ArrayInIncreasingOrder {
    public int solve(int[] A) {
        for(int i = 1; i < A.length; i++){
            if(A[i] < A[i - 1]){
                return 0;
            }
        }
        return 1;
    }
}
