package searchtargetwithouthashing;

// Link: https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/
public class CountOfSubArrHavingOddSumOrEvenSum {

    // TC = O(N) using prefix sum technique
    // Intuition: Odd Prefix Sum - Even Prefix Sum = Odd Sum
    // and Even Prefix Sum - Odd Prefix Sum = Even Sum
    public int numOfSubarraysWithOddSum_v2(int[] arr) {
        int n = arr.length;
        long evenPrefixSumCount = 1; // sum 0 ie empty prefix is also even prefix sum mathematically
        long oddPrefixSumCount = 0;
        long subArrCountWithOddSum = 0;
        long currPrefixSum = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += arr[i];
            if(currPrefixSum % 2 == 0){ // when even
                subArrCountWithOddSum += oddPrefixSumCount; // pair with odd // even sum pairs with odd sum to generate the diff of odd sum
                evenPrefixSumCount++;
            }else{ // when odd
                subArrCountWithOddSum += evenPrefixSumCount; // pair with even // odd sum pairs with even sum to generate the diff of odd sum
                oddPrefixSumCount++;
            }
        }
        return (int)(subArrCountWithOddSum % 1000000007);
    }

    // TC = O(N^2) when considering all possible subarrays
    public int numOfSubarraysWithOddSum(int[] arr) {
        long totalSubArrOddSumCnt = 0;
        for(int i = 0; i < arr.length; i++){
            long subArrSum = 0;
            for(int j = i; j < arr.length; j++){
                subArrSum += arr[j];
                if(subArrSum % 2 != 0){
                    totalSubArrOddSumCnt++;
                }
            }
        }
        return (int) (totalSubArrOddSumCnt % 1000000007);
    }

    public int numOfSubarraysWithEvenSum_v2(int[] arr) {
        int n = arr.length;
        long evenPrefixSumCount = 1; // sum 0 ie empty prefix is also even prefix sum mathematically
        long oddPrefixSumCount = 0;
        long subArrCountWithEvenSum = 0;
        long currPrefixSum = 0;
        for(int i = 0; i < n; i++){
            currPrefixSum += arr[i];
            if(currPrefixSum % 2 == 0){ // when even
                subArrCountWithEvenSum += evenPrefixSumCount; //pair with even // even sum pairs with even sum to generate the diff of even sum
                evenPrefixSumCount++;
            }else{ // when odd
                subArrCountWithEvenSum += oddPrefixSumCount; // pair with odd // odd sum pairs with odd sum to generate the diff of even sum
                oddPrefixSumCount++;
            }
        }
        return (int)(subArrCountWithEvenSum % 1000000007);
    }
}
