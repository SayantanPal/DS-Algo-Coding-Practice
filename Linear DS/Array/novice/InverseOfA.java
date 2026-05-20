package novice;

/*
* You are given an integer array A. Now your task is to find the inverse of A.
Now, the inverse of the array is A will be an array in which we change the
positions of the values as their indices and indices as values.

So, array A = [2, 0, 1]
- Now 2 is at index 0. So, place 0 at index 2.
- 0 is at index 1. So, place 1 at index 0.
- 1 is at index 2. So, place 2 at index 1.

So, the inverse of A will be [1, 2, 0]
*
*
Problem Constraints
1 <= |A| <= 105
0 <= A[i] < |A| (All elements are distinct)
*
* Input Format: The first and the only argument is an array A.
* Output Format: Return an array which is the inverse of the given array A.
*
* Input 1: A = [2, 0, 1]
* Output 1: [1, 2, 0]
*
* Input 2: A = [3, 1, 0, 2]
* Output 2: [2, 1, 3, 0]
*
* */
public class InverseOfA {

    public int[] solve(int[] A) {
        int maxElem = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            maxElem = Math.max(maxElem, A[i]);
        }

        int[] inverseA = new int[maxElem + 1];
        for(int i = 0; i < A.length ; i++){
            inverseA[A[i]] = i;
        }

        return inverseA;
    }
}
