package medium.MinMax;

import java.util.ArrayList;
import java.util.Collections;

/*
* John and Maths
Problem Description

John is a math enthusiast who loves to explore number patterns. One day, he came across an interesting problem: given an array A of size N with distinct elements, he wanted to know how many elements in the array were divisible by 7 and had at least 2 strictly greater elements.
Can you help John solve the problem?

Problem Constraints
1 <= A.length <= 105
1 <= A[i] <= 109

Input Format
The only argument is the array A.

Output Format
Return an Integer.


Example Input
Input 1: A = [8, 7, 11, 9, 14]
Input 2: A = [14, 7, 21, 45, 23]


Example Output
Output 1: 1
Output 2: 3


Example Explanation
Explanation 1:
In the array [8, 7, 11, 9, 14], 7 is the only element at position 1 which is divisible by 7 and has at least 2 elements strictly greater to it.
14 is also divisible by 7 but it has no element greater than itself.


*
* */
public class NobleJohnAndMaths {
    public int solve(ArrayList<Integer> A) {
        if(A.size() < 2) return 0;
        Collections.sort(A, (x, y) -> (y - x));
        int count = 0;
        for(int i = 2; i < A.size(); i++){
            if(A.get(i) != A.get(i - 1) && A.get(i) % 7 == 0){
                count++;
            }
        }
        return count;
    }
}
