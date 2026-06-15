package classic.fixedslidingwindow;

/*
* Problem Description
Given an array of integers A and an integer B, find and return the minimum number of swaps required to bring all the numbers less than or equal to B together.
Note: It is possible to swap any two elements, not necessarily consecutive.
*
* Problem Constraints
* 1 <= length of the array <= 100000
  -109 <= A[i], B <= 109
*
* Example Input
Input 1:
 A = [1, 12, 10, 3, 14, 10, 5]
 B = 8
 *
Input 2:
 A = [5, 17, 100, 11]
 B = 20
 *
 *
Example Output
Output 1: 2
Output 2: 1
* */
public class MinimumSwaps {
    public int findMinSwaps(int[] A, int B) {

        int n = A.length;
        int noOfElemsLessOrEqB = 0;
        for(int i = 0; i < n; i++){
            if(A[i] <= B)
                noOfElemsLessOrEqB++;
        }

        // System.out.println("noOfElemsLessOrEqB: " + noOfElemsLessOrEqB);

        // To Keep noOfElemsLessOrEqB together, think of a sliding window of size noOfElemsLessOrEqB
        // where task is to find or count the no of elements greater than B to find out how many elements to swap
        // now for a fixed size window of length noOfElemsLessOrEqB, find which window has min no of such elements greater than B
        int minSwapCount = Integer.MAX_VALUE;
        int swapCount = 0;
        for(int i = 0; i < noOfElemsLessOrEqB; i++){
            if(A[i] > B){
                swapCount++;
            }
        }

        // System.out.println("swapCount: " + swapCount);
        minSwapCount = Math.min(minSwapCount, swapCount);
        for(int i = noOfElemsLessOrEqB; i < n; i++){
            // fixed window ranges from [i - noOfElemsLessOrEqB + 1 to i]
            // in every itr, i enters into window and expired elem with index i - noOfElemsLessOrEqB starts falling outside window
            if(A[i] > B){
                swapCount++;
            }
            if(A[i - noOfElemsLessOrEqB] > B){
                swapCount--;
            }
            // System.out.println("swapCount: " + swapCount);
            minSwapCount = Math.min(minSwapCount, swapCount);
        }
        return minSwapCount;
    }
}
