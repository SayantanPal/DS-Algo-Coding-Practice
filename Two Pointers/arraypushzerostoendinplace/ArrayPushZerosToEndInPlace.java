package arraypushzerostoendinplace;

// Link: https://leetcode.com/problems/move-zeroes/
// constraint/catch - In Order preservation of numbers in array
public class ArrayPushZerosToEndInPlace {

    // since swap at each stage of arr[fast] != 0 along with redundant swapping when both slow == fast
    // it is bit slow ~ 2ms
    // but this will work for other variants like (+ve vs -ve numbers) or (even vs odd numbers) instead of (Zeros vs non-zeros) at opposite poles
    public void pushZerosToEndInPlace_v1(int[] nums) {
        int n = nums.length;
        if(n == 1) return;
        int slow = 0, fast = 0;
        while(fast < n){
            if(nums[fast] != 0){
                // basic swap instead of nums[slow] = nums[fast];
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }

        // refilling not needed because of basic swap
        // while(slow < n){
        //     nums[slow++] = 0;
        // }
    }

    // since this is simple assignment at each stage of arr[fast] != 0
    // it is bit fast ~ 1ms
    // but this won't work for other variants like (+ve vs -ve numbers) or (even vs odd numbers) instead of (Zeros vs non-zeros) at opposite poles
    public static void pushZerosToEndInPlace_v2(int[] arr) {
        // code here
        int n = arr.length;
        int slow = 0;
        int fast = 0;
        while(fast < arr.length){
            // nonZeroIndex will lag behind and i will move forward only when first 0 is encountered
            // at that stage, non zero will point to latest encountered 0th index always
            if(arr[fast] != 0){
                arr[slow++] = arr[fast];
                // never do arr[fast] = 0 at this stage because when slow == fast and arr[fast] = non-zero, then arr[slow] is also non-zero.
                // doing this mistake overwrites arr[slow] with 0 again
            }
            fast++;
        }

        // if there was no non-zero element; all elements were 0, then nonZeroIndex = 0 at the end
        // if there exists all non zero elements, then nonZeroIndex = n at the end

        // if at least one non-zero is present and 0 occurs after the series of non 0 element,
        // nonZeroIndex will point to last zero element
        while(slow < n){
            arr[slow++] = 0;
        }
    }
}
