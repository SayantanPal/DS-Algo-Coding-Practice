package threepointers;

// Link: https://leetcode.com/problems/sort-colors/description/
public class SortZeroOneTwoUsingDutchNationalFlagAlgo {

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0, mid = 0, high = n - 1;
        // when swapping with high, don't advance mid (the swapped-in element is unexamined).
        // When swapping with low, advance both (swapped-in element is already processed — it's a 1).

        //  When mid swaps with low, the swapped-in element at mid can only be 0 or 1 — never 2.
        // Why? low <= mid always. Everything between low and mid-1 has already been examined by mid and classified as 1.

        // So the element at low is either:
        // 1 (most common - it was skipped by mid earlier)
        // 0 (when low == mid — swapping with itself)
        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }else if(nums[mid] == 1){
                mid++;
            }else if(nums[mid] == 2){
                swap(nums, mid, high);
                high--;
            }
        }
    }
}


