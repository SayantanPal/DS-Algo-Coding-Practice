package classic;

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
public class FindPrefixSumForQueries {

    public int[] sumOfEvenIndexedElements(int[] A, int[][] B) {
        int n = A.length;
        int[] prefixSum = new int[n];

        prefixSum[0] = A[0]; // index 0 is even index since mathematically 0 is even

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

    public int[] sumOfOddIndexedElements(int[] A, int[][] B) {
        int n = A.length;
        int[] prefixSumOddIndex = new int[n];

        prefixSumOddIndex[0] = 0; // index 0 is even index since mathematically 0 is even
        for(int i = 1; i < n; i++){
            prefixSumOddIndex[i] = prefixSumOddIndex[i - 1] + ((i % 2 == 0) ? 0 : A[i]);
        }

        int q = B.length;
        int[] answer = new int[q];

        for(int i = 0; i < q; i++){
            int L = B[i][0];
            int R = B[i][1];

            answer[i] = prefixSumOddIndex[R] - (L == 0 ? 0 : prefixSumOddIndex[L - 1]);
        }

        return answer;
    }

    public int[] sumOfEvenElements(int[] A, int[][] B) {

        int n = A.length;
        int[] prefixSumEvenIndex = new int[n];
        prefixSumEvenIndex[0] = (A[0] % 2 == 0 ? 1 : 0);
        for(int i = 1; i < n; i++){
            prefixSumEvenIndex[i] = prefixSumEvenIndex[i - 1] + ((A[i] % 2 == 0) ? 1 : 0);
        }

        int q = B.length;
        int[] answer = new int[q];

        for(int i = 0; i < q; i++){
            int L = B[i][0];
            int R = B[i][1];

            answer[i] = prefixSumEvenIndex[R] - (L == 0 ? 0 : prefixSumEvenIndex[L - 1]);
        }

        return answer;
    }
}
