// Link: https://leetcode.com/problems/merge-sorted-array/description/
// Problem: Given two sorted arrays nums1 (size m+n with trailing zeros) and nums2 (size n), merge nums2 into nums1 in-place, sorted.
// Hint: Hint: Merge from the end, not the beginning. That way you don't overwrite unprocessed elements.

// Production Use: Memory-efficient diff checker between two log streams.
public class MergeTwoSortedArrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = (m + n) - 1;
        int i = (m - 1);
        int j = (n - 1);
        while(i >= 0 && j >= 0){
            int mergedNo;
            if(nums2[j] > nums1[i]){
                mergedNo = nums2[j];
                j--;
            } else{
                mergedNo = nums1[i];
                i--;
            }
            nums1[k--] = mergedNo;
        }
        while(i >= 0){
            nums1[k--] = nums1[i--];
        }
        while(j >= 0){
            nums1[k--] = nums2[j--];
        }
    }
}
