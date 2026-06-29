package basic.movezeros;
import util.PrintOutputUtils;
/*
*A chocolate factory is packing chocolates into packets.
* The chocolate packets here represent an array arr of N number of integer values.
* The task is to find the empty packets(0) of chocolate and
* push it to the end of the conveyor belt(array).
*For Example:

N=7 and arr = [4,5,0,1.9,0,5,0].
There are 3 empty packets in the given set.
* These 3 empty packets represented as O should be pushed towards the end of the array

* Example 1:
Input:
7
– Value of N
[4,5,0,1,0,0,5] – Element of arr[O] to arr[N-1],While input each element is separated by newline

* Output:
4 5 1 9 5 0 0

* Example 2:
Input:
6
— Value of N.
[6,0,1,8,0,2] – Element of arr[0] to arr[N-1], While input each element is separated by newline

* Output:
6 1 8 2 0 0
* */

// GOLDEN HACKS - Always remember:
// 1. if the zeros needs to be clubbed together either right or left pole/corners, this automatically means, non-zero elements are clubbed together either on opposite poles/corners
// 2. Sequencing matters for non-zero elements, NEVER for zero elements as they are look-alike.
// 3. Whose sequencing matters, that needs to be preserved -
//      - a. so always use fast pointer to point to non-zeros while sequentially it traverses one by one maintaining order and slow pointer preserves the order of non-zero elements
//      - b. when asked to move zero to right -> indirectly means move non-zero to left side(0th index) -> start slow and first pointer from left, where slow will sequentially club all non-zeros to accumulate clubbed zeros at right end.
//      - c. when asked to move zero to left -> indirectly means move non-zero to right side(n - 1 th index) -> start slow and first pointer from right, where slow will sequentially club all non-zeros to accumulate clubbed zeros at left end.

// Link: https://www.interviewbit.com/problems/move-zeroes/
//       https://www.naukri.com/code360/problems/interview-shuriken-41-move-zeroes-to-end_240143
//       https://www.geeksforgeeks.org/problems/move-all-zeroes-to-end-of-array0751/1
//       https://www.naukri.com/code360/problems/move-zeros-to-left_1094877?leftPanelTabValue=PROBLEM
public class MoveZerosToEndInPlaceMaintainingOrder {

    public static void pushZerosToEnd_v1(int[] arr) {
        // code here
        int n = arr.length;
        int nonZeroIndex = 0;
        for(int i = 0; i < n; i++){
            // nonZeroIndex will lag behind and i will move forward only when first 0 is encountered
            // at that stage, non zero will point to latest encountered 0th index always
            if(arr[i] != 0){
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;
            }
        }

        // if there was no non-zero element; all elements were 0, then nonZeroIndex = 0 at the end
        // if there exists all non zero elements, then nonZeroIndex = n at the end

        // if at least one non-zero is present and 0 occurs after the series of non 0 element,
        // nonZeroIndex will point to last zero element
        if(nonZeroIndex > 0 && nonZeroIndex < n){
            for(int i = nonZeroIndex; i < n; i++){
                arr[i] = 0;
            }
        }
    }

    public static void swap(int[] arr, int a, int b){
        if(a!=b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    public static void pushZerosToEnd_v2(int[] arr) {
        // code here
        int n = arr.length;
        int nonZeroIndex = 0; // this freezes at the first encountered zero from the start left
        for(int i = 0; i < n; i++){
            // X <-> Y

            // nonZeroIndex will lag behind and i will move rightward only when first 0 is encountered
            // at that stage, non zero will point to latest encountered 0th index always

            // push/exchange the numbers from forward right to left non-zero boundary | X <- Y(always non-zero since if cond check)
            // only when the number is + ve ie no scope of pushing zero to left from right

            // exchange forward from left to right is always first immediately encountered zero | X(0 obv) -> Y
            // since non zero will point to latest encountered 0th index always

            if(arr[i] != 0){ // i++ even when arr[i] is 0 or not 0 - does not matter
                swap(arr, nonZeroIndex, i);
                nonZeroIndex++;
            }
        }
    }

    public static void pushZerosToStart_v2(int[] arr) {
        // code here
        int n = arr.length;
        int nonZeroIndex = n - 1;
        for(int i = n - 1; i >= 0; i--){
            // nonZeroIndex will lag behind from right and i will move leftward only when first 0 is encountered
            // at that stage, non zero will point to latest encountered 0th index always
            if(arr[i] != 0){ // i++ even when arr[i] is 0 or not 0 - does not matter
                swap(arr, nonZeroIndex, i);
                nonZeroIndex--;
            }
        }
    }

    public static void main(String[] args){

        int[] arr = {4,5,0,1,0,0,5};

        pushZerosToEnd_v2(arr);
        PrintOutputUtils.print(arr); // 4 5 1 5 0 0 0

        pushZerosToStart_v2(arr);
        PrintOutputUtils.print(arr); // 0 0 0 4 5 1 5
    }
}
