package basic.subarrays;

// Constraint: All array elements are +ve
// Constraint: Subarray sums only for monotonically decreasing
public class MaxDescSubArrSumUsingKadane {

    public int maxDecreasingSubarraySum(int[] A) {
        if(A.length == 0) return 0;
        int currSum = A[0];
        int maxSum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                currSum += A[i];
            } else {
                currSum = A[i];
            }
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
