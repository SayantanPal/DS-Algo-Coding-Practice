package classic;

/*
* Problem Description: Given an array, arr[] of size N, the task is to find the count of array indices such that removing an element from these indices makes the sum of even-indexed and odd-indexed array elements equal.
* */
// Pre-requisite: FindPrefixSumForQueries
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
}
