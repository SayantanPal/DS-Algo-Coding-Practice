package searchinassumedsolutionspace;

import java.util.Arrays;

// Link: https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class KthSmallestPairDistance {

    public int findLtOrEqViaUpperBound(int l, int r, int[] sortedArr, long element){
        int offset = l;
        int upperBoundIndex = offset + (r - l + 1);
        while(l<=r){
            int midIndex = l + (r - l)/2;
            if(sortedArr[midIndex] == element){
                l = midIndex + 1;
            }else if(sortedArr[midIndex] < element){
                l = midIndex + 1;
            }else if(sortedArr[midIndex] > element){
                upperBoundIndex = midIndex;
                r = midIndex - 1;
            }
        }
        return upperBoundIndex - offset;
    }

    public long getCntOfAllLtOrEqPairSum(int[] sortedArr, int midDiff){
        int n = sortedArr.length;
        long cntOfAllLtOrEqPairSum = 0L;
        for(int i = 0; i < n - 1; i++){
            long otherPair = midDiff + sortedArr[i];
            cntOfAllLtOrEqPairSum += findLtOrEqViaUpperBound(i + 1, n - 1, sortedArr, otherPair);
        }
        return cntOfAllLtOrEqPairSum;
    }

    public int findUpperBoundOrFirstOccurance(int l, int r, int[] sortedArr, int searchElem){
        int ans = -1;
        while(l<=r){
            int midSearchElem = l + (r - l)/2;
            long cntOfAllLtOrEqPairSum = getCntOfAllLtOrEqPairSum(sortedArr, midSearchElem);
            if(cntOfAllLtOrEqPairSum == searchElem){
                ans = midSearchElem; // when elem exists
                r = midSearchElem - 1; // move left to find 1st occurance
            }else if(cntOfAllLtOrEqPairSum < searchElem){
                l = midSearchElem + 1;
            }else if(cntOfAllLtOrEqPairSum > searchElem){
                ans = midSearchElem; // upperbound in case of missing elem
                r = midSearchElem - 1;
            }
        }
        return ans;
    }

    public int smallestDistancePair(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int minDiff = 0;
        int maxDiff = nums[n - 1] - nums[0];

        int ans = findUpperBoundOrFirstOccurance(minDiff, maxDiff, nums, k);

        return ans;
    }
}
