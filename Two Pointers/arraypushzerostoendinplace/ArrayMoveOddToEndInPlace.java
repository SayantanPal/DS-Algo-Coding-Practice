package arraypushzerostoendinplace;

// Link: https://leetcode.com/problems/sort-array-by-parity/
public class ArrayMoveOddToEndInPlace {
    public int[] sortArrayByParity(int[] nums) {
        int slow = 0, fast = 0;
        int n = nums.length;
        while(fast < n){
            if(nums[fast]%2 == 0){
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }
}
