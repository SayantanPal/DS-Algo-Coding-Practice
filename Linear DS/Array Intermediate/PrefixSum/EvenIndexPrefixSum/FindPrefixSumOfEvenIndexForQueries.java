package EvenIndexPrefixSum;

/*
* You are given:
    An integer array A representing the array of numbers of size N.
    A 2D array B of size Q, where each row B[i] contains two integers B[i][0] and B[i][1], representing the range [L, R].
*
* For each query in B, calculate the sum of elements in A at EVEN indices within the specified range [L, R] and return the results as an array.
*
* Input 1:
A = [2, 8, 3, 9, 15]
B = [ [1, 4],
      [0, 2],
      [2, 3] ]
      *
* Output 1: [18, 5, 3]
* Output 2: [0, 5]
* */
public class FindPrefixSumOfEvenIndexForQueries {

    public int[] sumOfEvenIndexedElements(int[] A, int[][] B) {
        int n = A.length;
        int[] prefixSum = new int[n];

        prefixSum[0] = A[0];

        for(int i = 1; i < n; i++){
            // if(i%2 == 0)
            //     prefixSum[i] = prefixSum[i - 1] + A[i];
            // else
            //     prefixSum[i] = prefixSum[i - 1];
            prefixSum[i] = prefixSum[i - 1] + ((i % 2 == 0) ? A[i] : 0);
        }

        int[] answer = new int[B.length];

        for(int i = 0; i < B.length; i++){
            int L = B[i][0];
            int R = B[i][1];

            // if(L == 0){
            //     answer[i] = prefixSum[R];
            // }else{
            // answer[i] = prefixSum[R] - prefixSum[L - 1];
            // }

            answer[i] = prefixSum[R] - (L == 0 ? 0 : prefixSum[L - 1]);
        }
        return answer;
    }
}
