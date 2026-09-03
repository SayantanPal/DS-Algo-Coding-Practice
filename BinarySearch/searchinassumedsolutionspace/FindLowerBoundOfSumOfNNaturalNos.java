package searchinassumedsolutionspace;
/*
* Problem Description
----------------------
Given an integer A representing the number of square blocks. The height of each square block is 1. The task is to create a staircase of max-height using these blocks.
The first stair would require only one block, and the second stair would require two blocks, and so on.
Find and return the maximum height of the staircase.

Problem Constraints: 0 <= A <= 109

Input Format: The only argument given is integer A.
Output Format: Return the maximum height of the staircase using these blocks.

Example Input
-------------
Input 1: A = 10
Input 2: A = 20

Example Output
-------------
Output 1: 4
Output 2: 5


Example Explanation
-------------------
Explanation 1: The stairs formed will have height 1, 2, 3, 4.
Explanation 2: The stairs formed will have height 1, 2, 3, 4, 5.
* */

public class FindLowerBoundOfSumOfNNaturalNos {

    public int solve(int A) {
        if(A <= 1) return A;
        int l = 1, r = A - 1;
        int lowerBound = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            long twoTimesA = (long)mid*(mid + 1);
            if(twoTimesA == 2*(long)A){
                return mid;
            }else if(twoTimesA > 2*(long)A){
                r = mid - 1; // move left
            }else if(twoTimesA < 2*(long)A){
                lowerBound = mid;
                l = mid + 1; // move right
            }
        }
        return lowerBound;
    }

    public int solve_v2(int A) {
        if(A <= 1) return A;
        int l = 1, r = A - 1;
        int lowerBound = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(mid + 1 == 2*A/mid){
                return mid;
            }else if(mid + 1 > 2*A/mid){
                r = mid - 1; // move left
            }else if(mid + 1 < 2*A/mid){
                lowerBound = mid;
                l = mid + 1; // move right
            }
        }
        return lowerBound;
    }
}
