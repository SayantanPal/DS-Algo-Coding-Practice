package medium.majorityelement;

/*
* N/3 Repeat Number
*
* Problem Description

You're given a read-only array of N integers. Find out if any integer occurs more than N/3 times in the array in linear time and constant additional space.
If so, return the integer. If not, return -1.

If there are multiple solutions, return any one.

Note: Read-only array means that the input array should not be modified in the process of solving the problem
*
* Problem Constraints
1 <= N <= 7*105
1 <= A[i] <= 109

Input Format: The only argument is an integer array A.
Output Format: Return an integer.

*
* Example Input & Output
Input 1: [1 2 3 1 1]
* Output 1: 1
* Explanation 1: 1 occurs 3 times which is more than 5/3 times.
*
* Input 2: [1 2 3]
* Output 2: -1
*
* Explanation 2: No element occurs more than 3 / 3 = 1 times in the array.
* */
// Link:
public class MajorityElementNBy3ExtensionOfBoyerMooreVotingAlgo {
    public int repeatedNumber(int[] A) {
        int candidate1 = -1, count1 = 0;
        int candidate2 = -1, count2 = 0;

        for(int i = 0; i < A.length; i++){
            if(A[i] == candidate1) count1++;
            else if(A[i] == candidate2) count2++;
            else if(count1 == 0) {
                candidate1 = A[i];
                count1 = 1;
            }
            else if(count2 == 0) {
                candidate2 = A[i];
                count2 = 1;
            }
            else{
                count1--;
                count2--;
            }
        }

        // Second Pass : To find actually the frequency and
        // which one is the majority among 2 candidates
        count1 = 0; count2 = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == candidate1) count1++;
            else if(A[i] == candidate2) count2++;
        }

        if(count1 > A.length/3){
            return candidate1;
        }else if(count2 > A.length/3){
            return candidate2;
        }
        return -1;
    }
}
