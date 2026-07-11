package medium.MinMax;

import java.util.ArrayList;
import java.util.Collections;

/*
* Problem Description:
* John is a math enthusiast who loves to explore number patterns. One day, he came across an interesting problem:
* given an array A of size N with distinct elements, he wanted to know how many elements in the array were divisible by 7 and had at least 2 strictly greater elements.
Can you help John solve the problem?
*
* Problem Constraints
1<= A.length <= 10°
1 <= A[i] <= 10°
*
Input Format: The only argument is the array A.
Output Format: Return an Integer.
*
* Example Input
Input 1: A = [8, 7, 11, 9, 14]
Input 2: A = [14, 7, 21, 45, 23]
* */
public class TwoStrictlyGreaterElemDivBy7 {

    public int solve(ArrayList<Integer> A){
        if(A.size() < 2) return 0;
        Collections.sort(A, (x, y) -> (y - x)); //Arrays.sort(Arrays.stream( new int[]{} ).boxed().toArray( Integer[]::new ), Collections.reverseOrder());
        int count = 0;
        for(int i = 2; i < A.size(); i++){
            if(A.get(i) != A.get(i - 1) && A.get(i) % 7 == 0){
                count++;
            }
        }
        return count;
    }
}
