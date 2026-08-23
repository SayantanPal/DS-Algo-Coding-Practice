package sorting;

// Link: https://leetcode.com/problems/sort-an-array/?envType=problem-list-v2&envId=merge-sort
public class MergeSort {
    public void mergeArray(int[] nums, int l, int mid, int r){
        int n1 = mid - l + 1;
        int n2 = r - (mid + 1) + 1;

        int[] L = new int[n1];
        int[] R = new int[n2];

        int k = l;
        for(int i = 0; i < L.length; i++){
            L[i] = nums[k++];
        }

        for(int i = 0; i < R.length; i++){
            R[i] = nums[k++];
        }

        int i = 0, j = 0;
        k = l;
        while (i < n1 && j < n2){
            if(L[i] <= R[j]){
                nums[k++] = L[i++];
            }else{
                nums[k++] = R[j++];
            }
        }

        while(i < n1){
            nums[k++] = L[i++];
        }

        while(j < n2){
            nums[k++] = R[j++];
        }
    }

    public void mergeSort(int[] nums, int l, int r){
        if(l == r) return;
        int mid = (l + r)/2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        mergeArray(nums, l, mid, r);
    }

    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
}
