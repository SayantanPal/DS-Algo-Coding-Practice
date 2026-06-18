package medium.MinMax;

import java.util.ArrayList;

/*
* Given an integer array A containing N distinct integers, you have to find all the leaders in array A. An element is a leader if it is strictly greater than all the elements to its right side.
NOTE: The rightmost element is always a leader.

* Problem Constraints
1 <= N <= 105
1 <= A[i] <= 108

Input Format: There is a single input argument which a integer array A
Output Format: Return an integer array denoting all the leader elements of the array.
*
* Example Input

Input 1: A = [16, 17, 4, 3, 5, 2]
Input 2: A = [5, 4]

* Example Output
Output 1: [17, 2, 5]
Output 2: [5, 4]
* */
public class LeadersInArray {

    public ArrayList<Integer> findArrayLeaders(ArrayList<Integer> A) {
        int n = A.size();
        ArrayList<Integer> leaders = new ArrayList<>();
        int maximum = A.get(n - 1);
        leaders.add(maximum);

        for(int i = n - 2; i >= 0; i--){
            if(A.get(i) > maximum){
                maximum = A.get(i);
                leaders.add(maximum);
            }
        }
        return leaders;
    }
}
