
// Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Expected TC = O(log(2)N)
public class FindFirstAndLastIndexOfSearchElemInSortedArr {

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;

        // set below as n or -1 as mentioned in the problem statement
        int upperBound = n; // arr elem immediately greater than target (i.e., smallest no. which is greater than target)
        int lowerBound = n; // arr elem immediately smaller than target  (i.e., greatest no. which is smaller than target)

        // In sorted array: nums[lowerBound] < first index of duplicate target ... target ... last index of duplicate target < nums[upperBound

        // find first occurance of search element when present in duplicate
        int firstOccurance = -1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){ // continue moving till mid index element is target
                // don't stop when equals
                firstOccurance = mid; // hold the last recently encountered search elem while moving towards left
                right = mid - 1; // keep on moving towards left
            }else if(nums[mid] > target){ // target < nums[mid]
                upperBound = mid;
                right = mid - 1; // move to left half by discarding right half
            }else if(nums[mid] < target){
                lowerBound = mid;
                left = mid + 1; // move to right half by discarding left half
            }
        }

        // find last occurance of search element when present in duplicate
        int lastOccurance = -1;
        left = 0; right = n - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target){ // continue moving till mid index element is target
                lastOccurance = mid; // hold the last recently encountered search elem while moving towards right
                left = mid + 1; // keep on moving towards right
            }else if(nums[mid] > target){
//                upperBound = mid;
                right = mid - 1;
            }else if(nums[mid] < target){
//                lowerBound = mid;
                left = mid + 1;
            }
        }
        return new int[]{firstOccurance, lastOccurance};
    }
}
