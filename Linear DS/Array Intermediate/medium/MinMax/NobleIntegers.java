package medium.MinMax;

import java.util.ArrayList;
import java.util.Collections;

/*
* Problem Description
 Given an integer array A, find if an integer p exists in the array such that the number of integers greater than p in the array equals to p.
*
* Problem Constraints
1 <= |A| <= 106
-109 <= Ai <= 109
*
* Input Format: First and only argument is an integer array A.
* Output Format: Return 1 if any such integer p is found else return -1.
*
*
Example Input
Input 1: A = [3, 2, 1, 3]
Output 1: 1
* Explanation 1: For integer 2, there are 2 greater elements in the array. So, return 1.
*
* Input 2: A = [1, 1, 3, 3]
* Output 2: -1
* Explanation 2: There is no such integer exists.
* */

// Link: https://www.interviewbit.com/problems/noble-integer/
public class NobleIntegers {
    public int solve(ArrayList<Integer> A) {
        Collections.sort(A, (x, y) -> (y - x));
        if(A.get(0) == 0) return 1;
        for(int i = 1; i < A.size(); i++){
            if(A.get(i) != A.get(i-1) && A.get(i) == i) return 1;
        }
        return -1;
    }
}
