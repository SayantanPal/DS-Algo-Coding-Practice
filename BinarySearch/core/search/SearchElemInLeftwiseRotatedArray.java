package core.search;

// Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchElemInLeftwiseRotatedArray {

    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(target >= nums[0]){ // a. when target is in 1st half
                if(nums[mid] >= nums[0]){ // and when mid is also in same 1st half
                    // normal binary search
                    if(nums[mid] < target){
                        l = mid + 1;
                    }else if(nums[mid] > target){
                        r = mid - 1;
                    }else{
                        return mid;
                    }
                }else{ // but when mid is in 2nd half
                    r = mid - 1; // move left
                }
            }else{ // b. when target is in 2nd half
                if(nums[mid] < nums[0]){ // and when mid is also in same 2nd half
                    // normal binary search
                    if(nums[mid] < target){
                        l = mid + 1;
                    }else if(nums[mid] > target){
                        r = mid - 1;
                    }else{
                        return mid;
                    }
                }else{ // but when mid is in 1st half
                    l = mid + 1; // move right
                }
            }
        }
        return -1;
    }
}
