package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/sum-of-subarray-ranges/description/
public class DiffOfMaxMinOfAllPossibleSubarrays {

    // For duplicates
    // with duplicates in the array, we need to handle double counting.
    // When equal elements exist, one side should use strict < and the other <= for the comparisons.
    // Currently all four helper functions use strict inequalities (<= and >= to pop), which means for duplicates, both copies think they're the max/min of the same subarray.
    // Make one direction (say left) strict and the other non-strict (e.g., left uses <=/>= to pop, right uses </> to pop).
    public static int[] previous_immediate_greater_number_index_to_the_left(int[] A) {
        Deque<Integer> prevImmediateGreaterOnLeftIndex = new ArrayDeque<>();

        int[] prevImmediateGreaterOnLeftArrIndex = new int[A.length]; // left wall

        for(int i = 0; i < A.length; i++){
            // Try to find the previous immediate greater element on left
            // Note down the index for previous immediate greater element on left as index of left wall
            while(!prevImmediateGreaterOnLeftIndex.isEmpty() && A[prevImmediateGreaterOnLeftIndex.peek()] < A[i]){
                prevImmediateGreaterOnLeftIndex.pop(); // pop out all in between larger or equal elements
            }
            prevImmediateGreaterOnLeftArrIndex[i] = prevImmediateGreaterOnLeftIndex.isEmpty() ? -1: prevImmediateGreaterOnLeftIndex.peek();
            prevImmediateGreaterOnLeftIndex.push(i);
        }
        return prevImmediateGreaterOnLeftArrIndex;
    }

    public static int[] next_immediate_greater_number_index_to_the_right(int[] A) {
        Deque<Integer> nextImmediateGreaterOnRightIndex = new ArrayDeque<>();

        int[] nextImmediateGreaterOnRightArrIndex = new int[A.length]; // right wall

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate greater element on right
            // Note down the index for next immediate greater element on right as index of right wall
            while(!nextImmediateGreaterOnRightIndex.isEmpty() && A[nextImmediateGreaterOnRightIndex.peek()] <= A[i]){
                nextImmediateGreaterOnRightIndex.pop(); // pop out all in between larger or equal elements
            }
            nextImmediateGreaterOnRightArrIndex[i] = nextImmediateGreaterOnRightIndex.isEmpty() ? A.length: nextImmediateGreaterOnRightIndex.peek();
            nextImmediateGreaterOnRightIndex.push(i);
        }

        return nextImmediateGreaterOnRightArrIndex;
    }

    public static int[] previous_immediate_smaller_number_index_to_the_left(int[] A) {
        Deque<Integer> prevImmediateSmallerOnLeftIndex = new ArrayDeque<>();

        // answer array stores index of left wall ie index of previous immediate smaller element on left of A[i]
        int[] prevImmediateSmallerOnLeftArrIndex = new int[A.length]; // left wall

        for(int i = 0; i < A.length; i++){
            // Try to find the previous immediate smaller element on left
            // Note down the index for previous immediate smaller element on left as index of left wall
            while(!prevImmediateSmallerOnLeftIndex.isEmpty() && A[prevImmediateSmallerOnLeftIndex.peek()] > A[i]){
                prevImmediateSmallerOnLeftIndex.pop(); // pop out all in between larger or equal elements
            }
            prevImmediateSmallerOnLeftArrIndex[i] = prevImmediateSmallerOnLeftIndex.isEmpty() ? -1: prevImmediateSmallerOnLeftIndex.peek();
            prevImmediateSmallerOnLeftIndex.push(i);
        }
        return prevImmediateSmallerOnLeftArrIndex;
    }

    public static int[] next_immediate_smaller_number_index_to_the_right(int[] A) {
        Deque<Integer> nextImmediateSmallerOnRightIndex = new ArrayDeque<>();

        // answer array stores index of right wall ie index of next immediate smaller element on right of A[i]
        int[] nextImmediateSmallerOnRightIndexArr = new int[A.length];

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate smaller element on right
            // Note down the index for next immediate smaller element on right as index of right wall
            while(!nextImmediateSmallerOnRightIndex.isEmpty() && A[nextImmediateSmallerOnRightIndex.peek()] >= A[i]){
                nextImmediateSmallerOnRightIndex.pop(); // pop out all in between larger or equal elements
            }
            nextImmediateSmallerOnRightIndexArr[i] = nextImmediateSmallerOnRightIndex.isEmpty() ? A.length: nextImmediateSmallerOnRightIndex.peek();
            nextImmediateSmallerOnRightIndex.push(i);
        }

        return nextImmediateSmallerOnRightIndexArr;
    }



    public long subArrayRanges(int[] nums) {
        // int MOD = 1000000007;

        // Summation of (Max - Min) of all subarrays
        // = for each element A[i],
        // find (total Contribution of A[i] where A[i] is max - total contribution of A[i] where A[i] is min)

        // to find where A[i] is maximum in subarray
        int[] prevImmediateGreaterOnLeftArr = previous_immediate_greater_number_index_to_the_left(nums);
        int[] nextImmediateGreaterOnRightArr = next_immediate_greater_number_index_to_the_right(nums);


        // to find where A[i] is minimum in subarray
        int[] prevImmediateSmallerOnLeftArr = previous_immediate_smaller_number_index_to_the_left(nums);
        int[] nextImmediateSmallerOnRightArr = next_immediate_smaller_number_index_to_the_right(nums);

        long sumOfContribution = 0;
        for(int i = 0; i < nums.length; i++){
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
            long countOfSubArraysToContribute =(cntOfSubArrMax - cntOfSubArrMin); //long countOfSubArraysToContribute = ((cntOfSubArrMax - cntOfSubArrMin) % MOD + MOD) % MOD;
            long individualContribution = (countOfSubArraysToContribute * nums[i]); //long individualContribution = (countOfSubArraysToContribute * nums[i]) % MOD;
            sumOfContribution = (sumOfContribution + individualContribution); //sumOfContribution = (sumOfContribution + individualContribution) % MOD;
        }
        return sumOfContribution;
    }

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
