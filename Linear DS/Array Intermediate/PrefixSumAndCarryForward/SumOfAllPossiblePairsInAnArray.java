import java.util.Arrays;

/*
* Given an array of integers A, calculate the sum of A [ i ] % A [ j ] for all possible i, j pairs.
* Return sum % (10^9 + 7) as an output.
*
* */
public class SumOfAllPossiblePairsInAnArray {
    public int solve(int[] A) {
        Arrays.sort(A);
        int maxElem = A[0];
        for(int i = 1; i < A.length; i++){
            maxElem = Math.max(maxElem, A[i]);
        }

        int[] prefixSum = new int[maxElem + 1];
        int[] count = new int[maxElem + 1];
        for(int i = 0; i < A.length; i++){
            prefixSum[A[i]] += A[i];
            count[A[i]] += 1;
        }

        int[] originalCount = Arrays.copyOf(count, count.length);

        for(int i = 1; i < prefixSum.length; i++){
            prefixSum[i] += prefixSum[i - 1];
            count[i] += count[i - 1];
        }

        long sum = 0;
        for(int i = 0; i < A.length; i++){
            if(i > 0 && A[i] == A[i-1]) continue;
            // originalCount[d] -> how many times A[i] itself appears in the array (for multiplying contributions)
            sum += (long)prefixSum[A[i] - 1] * originalCount[A[i]];
            sum %= 1000000007;
            // TO-DO right part for A[j] > A[i] where j > i
            // sum += (rangeSum - (long)A[i] * multiplier * rangeCount) * originalCount[A[i]];
            for(int multiplier = 1; A[i] * multiplier <= maxElem; multiplier++){
                int L = A[i] * multiplier;
                int R = Math.min(A[i]*(multiplier + 1)  - 1, maxElem);
                // all possible numbers falling/lying between [A[i] * multiplier , A[i] * (multiplier + 1) ) => [A[i] * multiplier , A[i] * (multiplier + 1) - 1)
                long rangeSum = prefixSum[R] - prefixSum[L - 1];
                long rangeCount = count[R] - count[L - 1];
                sum += (rangeSum - (long)A[i] * multiplier * rangeCount) * originalCount[A[i]];
                sum %= 1000000007;
            }
        }
        return (int)sum;
    }
}
