/*
You are given an array of integers A of size N.
The value of a subarray is defined as the BITWISE OR (or AND, or XOR)
of all elements in it.
Return the sum of value of all subarrays of A, modulo 10^9 + 7.
*/
public class SubArrayORANDXOR {

    // When does a bit remain set in OR? At least one element in the subarray has it set.
    // Strategy: Count subarrays where all bits are 0 (using run-length of consecutive 0s), subtract from total.

    public int subarraySumOR(int[] A) {
        int n = A.length;
        long MOD = 1000000007;
        long totalSubarrays = (long) n * (n + 1) / 2;
        long answer = 0;
        for (int bitPos = 0; bitPos < 32; bitPos++) {
            long zeroSubarrays = 0;
            long runLength = 0;
            for (int j = 0; j < n; j++) {
                if ((A[j] & (1 << bitPos)) == 0) {
                    runLength++;
                    zeroSubarrays += runLength;
                } else {
                    runLength = 0;
                }
            }
            long setSubarrays = totalSubarrays - zeroSubarrays;
            answer += setSubarrays * (1L << bitPos);
            answer %= MOD;
        }
        return (int) answer;
    }

    // When does a bit remain set in AND? All elements in the subarray have it set.
    // Strategy: Count run-length of consecutive 1s directly.
    public int subarraySumAND(int[] A) {
        int n = A.length;
        long MOD = 1000000007;
        long answer = 0;
        for (int bitPos = 0; bitPos < 32; bitPos++) {
            long oneSubarrays = 0;
            long runLength = 0;
            for (int j = 0; j < n; j++) {
                if ((A[j] & (1 << bitPos)) != 0) {
                    runLength++;
                    oneSubarrays += runLength;
                } else {
                    runLength = 0;
                }
            }
            answer += oneSubarrays * (1L << bitPos);
            answer %= MOD;
        }
        return (int) answer;
    }

    // When does a bit remain set in XOR? Odd number of elements have it set
    // Strategy: Use prefix parity counting. A subarray [i..j] has odd set bits iff prefix[j] and prefix[i-1] have opposite parity (one even, one odd).
    // Track how many previous prefix counts were even vs odd. At each index, pair with opposite parity prefixes.
    /*
    * XOR Dry Run Example
        Array bits at one position: [1, 1, 0, 1, 0]
        Start: evenCount=1, oddCount=0, oddSubarrays=0
        j=0: prefixOnes=1 (odd) → oddSubarrays += evenCount=1 → 1, oddCount=1
        j=1: prefixOnes=2 (even) → oddSubarrays += oddCount=1 → 2, evenCount=2
        j=2: prefixOnes=2 (even) → oddSubarrays += oddCount=1 → 3, evenCount=3
        j=3: prefixOnes=3 (odd) → oddSubarrays += evenCount=3 → 6, oddCount=2
        j=4: prefixOnes=3 (odd) → oddSubarrays += evenCount=3 → 9, oddCount=3
        Total oddSubarrays = 9 (verified by brute force)
    *
    * */
    public int subarraySumXOR(int[] A) {
        int n = A.length;
        long MOD = 1000000007;
        long answer = 0;
        for (int bitPos = 0; bitPos < 32; bitPos++) {
            long oddSubarrays = 0;
            long oddCount = 0, evenCount = 1; // evenCount=1 for empty prefix (0 ones = even)
            int prefixOnes = 0;
            for (int j = 0; j < n; j++) {
                if ((A[j] & (1 << bitPos)) != 0) prefixOnes++;
                if (prefixOnes % 2 == 0) {
                    oddSubarrays += oddCount; // even - odd = odd
                    evenCount++;
                } else {
                    oddSubarrays += evenCount; // odd - even = odd
                    oddCount++;
                }
            }
            answer += oddSubarrays * (1L << bitPos);
            answer %= MOD;
        }
        return (int) answer;
    }

}
