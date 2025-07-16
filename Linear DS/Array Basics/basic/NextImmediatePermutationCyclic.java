package basic;

public class NextImmediatePermutationCyclic {

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverseArray(int[] arr, int start, int end){

        while(start <= end){
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    // condition: no repeative numbers like [9, 9, 9]
    public void nextPermutation(int[] nums) {

        int ind = -1;
        int n = nums.length;

        for(int i = n - 2; i >= 0; i--){
            if(nums[i] < nums[i+1]){
                ind = i;
                break;
            }
        }

        if(ind == -1){ // all elements in decreasing order, cycle back to first combo
            reverseArray(nums, 0, n - 1);
            return;
        }

        for(int i = n - 1; i >= 0; i--){
            if(nums[i] > nums[ind]){
                swap(nums, i, ind);
                break;
            }
        }

        reverseArray(nums, ind + 1, n - 1);
    }
}
