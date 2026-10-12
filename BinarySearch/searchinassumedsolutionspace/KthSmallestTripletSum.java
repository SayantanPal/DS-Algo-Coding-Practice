package searchinassumedsolutionspace;

import java.util.Arrays;

/*
The trivial way to do it is by generating all possible triplets and sorting all of them,
and finding elements at index B-1.
But it will exceed the memory limit for larger test cases.

We can do this by binary search in O(N2logA[i]).
As the answer lies in the range [3 to 3*108]. So we binary search in this range
and can choose a mid and count the number of triplets whose sum is less than mid.
If the count of triplets is more than or equal to B, then our answer is lesser than mid
Else our answer is greater than equal to mid.
So we can use that to find the answer.

Time Complexity : O(N2logA[i])
Space Complexity : O(1)


*/
public class KthSmallestTripletSum {

    // Upper Bound search on array index only returns no of elements lesser than or equals search Elem
    public int upperBoundIndex(int l, int r, int[] A, long searchElem){
        int indexDisplacement = l;
        int upperBound = indexDisplacement + (r - l + 1);
        while(l <= r){
            int mid = l + (r - l)/2;
            if(A[mid] == searchElem){
                l = mid + 1;
            }else if(A[mid] > searchElem){
                upperBound = mid;
                r = mid - 1;
            }else if(A[mid] < searchElem){
                l = mid + 1;
            }
        }
        return upperBound - indexDisplacement;
    }

    public long getCntOfTripletSumLtOrEqual(int[] A, long searchTripletMidSum){
        int n = A.length;
        long cntOfTripletSumLtOrEq = 0L;
        for(int i = 0; i < n - 2; i++){
            for(int j = i + 1; j < n - 1; j++){
                long thirdSearchElem = searchTripletMidSum - (A[i] + A[j]);
                cntOfTripletSumLtOrEq += upperBoundIndex(j + 1, n - 1, A, thirdSearchElem);
            }
        }
        return cntOfTripletSumLtOrEq;
    }

    public int solve(int[] A, int B) {
        int n = A.length;
        Arrays.sort(A);
        long l = A[0] + A[1] + A[2], r = A[n - 3] + A[n - 2] + A[n - 1];
        long upperBound = -1L;
        while(l <= r){
            long midTripletSearchSum = l + (r - l)/2;
            long cntOfTripletSumLtOrEqual = getCntOfTripletSumLtOrEqual(A, midTripletSearchSum);
            if(cntOfTripletSumLtOrEqual == B){
                upperBound = midTripletSearchSum;
                r = midTripletSearchSum - 1;
            }else if(cntOfTripletSumLtOrEqual < B){
                l = midTripletSearchSum + 1;
            }else if(cntOfTripletSumLtOrEqual > B){
                upperBound = midTripletSearchSum;
                r = midTripletSearchSum - 1;
            }
        }
        return (int)upperBound;
    }
}
