package immediatelargerorsmalleronleftorright;

public class DiffOfMaxMinOfAllPossibleSubarrays {

    public int findMinMaxDiffOfAllPossibleSubarrays(int[] A) {
        int MOD = 1000000007;

        // Summation of (Max - Min) of all subarrays
        // = for each element A[i],
        // find (total Contribution of A[i] where A[i] is max - total contribution of A[i] where A[i] is min)

        // to find where A[i] is maximum in subarray
        int[] prevImmediateGreaterOnLeftArr = PreviousImmediateGreaterNoToTheLeft.previous_immediate_greater_number_index_to_the_left(A);
        int[] nextImmediateGreaterOnRightArr = NextImmediateGreaterNumberToTheRight.next_immediate_greater_number_index_to_the_right(A);


        // to find where A[i] is minimum in subarray
        int[] prevImmediateSmallerOnLeftArr = PreviousImmediateSmallerNoToTheLeft.previous_immediate_smaller_number_index_to_the_left(A);
        int[] nextImmediateSmallerOnRightArr = NextImmediateSmallerNumberToTheRight.next_immediate_smaller_number_index_to_the_right(A);

        long sumOfContribution = 0;
        for(int i = 0; i < A.length; i++){
            int l1 = prevImmediateGreaterOnLeftArr[i];
            int r1 = nextImmediateGreaterOnRightArr[i];

            // elements (l1, r1) ie [l1 + 1, r1 - 1] are the indexes where elements are all smaller than or equal to A[i] ie A[i] is largest in such
            // (l1 + 1)th index to ith index on left has all elem smaller than or equal to A[i]
            int noOfStartingPoints1 = i - (l1 + 1) + 1; // i - l

            // ith index to (r1 - 1)th index on right has all elem smaller than or equal to A[i]
            int noOfEndingPoints1 = (r1 - 1) - i + 1; // r - i
            // A[i] is greatest ie max in noOfStartingPoints*noOfEndingPoints subarray
            long cntOfSubArrMax = (long)noOfStartingPoints1 * noOfEndingPoints1;

            int l2 = prevImmediateSmallerOnLeftArr[i];
            int r2 = nextImmediateSmallerOnRightArr[i];

            // elements (l1, r1) ie [l1 + 1, r1 - 1] are the indexes where elements are all smaller than or equal to A[i] ie A[i] is largest in such
            // (l1 + 1)th index to ith index on left has all elem smaller than or equal to A[i]
            int noOfStartingPoints2 = i - (l2 + 1) + 1; // i - l

            // ith index to (r1 - 1)th index on right has all elem smaller than or equal to A[i]
            int noOfEndingPoints2 = (r2 - 1) - i + 1; // r - i
            // A[i] is greatest ie max in noOfStartingPoints*noOfEndingPoints
            long cntOfSubArrMin = (long)noOfStartingPoints2 * noOfEndingPoints2;

            // since cntOfSubArrMax - cntOfSubArrMin can be either +ve or -ve
            // technique -> % MOD + MOD) % MOD for negative Modulus without the negative sign
            long countOfSubArraysToContribute = ((cntOfSubArrMax - cntOfSubArrMin) % MOD + MOD) % MOD;
            long individualContribution = (countOfSubArraysToContribute * A[i]) % MOD;
            sumOfContribution = (sumOfContribution + individualContribution) % MOD;
        }
        return (int)sumOfContribution;
    }
}
