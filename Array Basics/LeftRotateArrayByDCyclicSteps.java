import util.PrintOutputUtils;

public class LeftRotateArrayByDCyclicSteps {

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void reverseArray(int arr[], int startIndex, int endIndex) {
        // code here
        if(arr.length < 2 || startIndex == endIndex) return;
        while(startIndex < endIndex){
            if(arr[startIndex] != arr[endIndex]){
                swap(arr, startIndex, endIndex);
            }
            startIndex++;
            endIndex--;
        }
    }

    // Function to rotate an array by d elements in counter-clockwise direction.
    static void rotateArrByLeftDElem(int arr[], int d) {
        // add your code here
        int n = arr.length;
        if(n < 2 || d == 0) return;
        d = d % n; // since cyclic
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
        reverseArray(arr, 0, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        int d = 3;
        rotateArrByLeftDElem(arr, d);
        PrintOutputUtils.print(arr);
    }
}
