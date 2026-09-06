package classic;
/*
* Problem Description

Given an array, arr[] of size N, the task is to find the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.

Problem Constraints
1 <= N <= 105
-105 <= A[i] <= 105
Sum of all elements of A <= 109


Input Format
First argument contains an array A of integers of size N

Output Format
Return the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.


Example Input
Input 1: A = [2, 1, 6, 4]
Input 2: A = [1, 1, 1]


Example Output
Output 1: 1
Output 2: 3


Example Explanation
Explanation 1:
Removing arr[1] from the array modifies arr[] to { 2, 6, 4 } such that, arr[0] + arr[2] = arr[1].
Therefore, the required output is 1.
Explanation 2:

Removing arr[0] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Removing arr[1] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Removing arr[2] from the given array modifies arr[] to { 1, 1 } such that arr[0] = arr[1]
Therefore, the required output is 3.

*
* */

/*
* Problem Description: Given an array, arr[] of size N, the task is to find the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.
* */
// Pre-requisite: FindPrefixSumForQueries
// Link: https://leetcode.com/problems/ways-to-make-a-fair-array/
public class FindSpecialIndex {

    public int solve(int[] A) {

        int n = A.length;
        int[] prefixSumEvenIndex = new int[n];
        int[] prefixSumOddIndex = new int[n];

        prefixSumEvenIndex[0] = A[0];
        prefixSumOddIndex[0] = 0;

        for(int i = 1; i < n; i++){
            prefixSumEvenIndex[i] = prefixSumEvenIndex[i - 1] + ((i % 2 == 0) ? A[i] : 0);
            prefixSumOddIndex[i] = prefixSumOddIndex[i - 1] + ((i % 2 == 0) ? 0 : A[i]);
        }

        int sumOfOdd = 0, sumOfEven = 0, counter = 0;

        for(int i = 0; i < n; i++){

            if(i == 0){
                sumOfOdd = prefixSumEvenIndex[n - 1] - prefixSumEvenIndex[(i + 1) - 1];
                sumOfEven = prefixSumOddIndex[n - 1] - prefixSumOddIndex[(i + 1) - 1];
            }else{
                sumOfOdd = (prefixSumOddIndex[i - 1] - 0) + (prefixSumEvenIndex[n - 1] - prefixSumEvenIndex[(i + 1) - 1]);
                sumOfEven = (prefixSumEvenIndex[i - 1] - 0) + (prefixSumOddIndex[n - 1] - prefixSumOddIndex[(i + 1) - 1]);
            }

            if(sumOfOdd == sumOfEven) counter++;
        }

        return counter;
    }

    public int solve2(int[] A) {

        int n = A.length;

        int[] evenIndxPrefixSum = new int[n];
        int[] oddIndxPrefixSum = new int[n];

        evenIndxPrefixSum[0] = A[0];
        oddIndxPrefixSum[0] = 0;

        for(int i = 1; i < n; i++){
            if(i%2 == 0){
                evenIndxPrefixSum[i] = evenIndxPrefixSum[i - 1] + A[i];
                oddIndxPrefixSum[i] = oddIndxPrefixSum[i - 1];
            }else{
                evenIndxPrefixSum[i] = evenIndxPrefixSum[i - 1];
                oddIndxPrefixSum[i] = oddIndxPrefixSum[i - 1] + A[i];
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(i == 0){
                if(oddIndxPrefixSum[n - 1] - oddIndxPrefixSum[i] == evenIndxPrefixSum[n - 1] - evenIndxPrefixSum[i]){
                    count++;
                }
            }else{
                if(evenIndxPrefixSum[i - 1] + (oddIndxPrefixSum[n - 1] - oddIndxPrefixSum[i]) == oddIndxPrefixSum[i - 1] + (evenIndxPrefixSum[n - 1] - evenIndxPrefixSum[i])){
                    count++;
                }
            }
        }
        return count;
    }
}
