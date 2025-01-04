public class LeftRotateArrayByDCyclicSteps {

    static void reverseArray(int arr[], int startIndex, int endIndex) {
        // code here
        if(arr.length < 2 || startIndex == endIndex) return;
        while(startIndex < endIndex){
            if(arr[startIndex] != arr[endIndex]){
                int temp = arr[startIndex];
                arr[startIndex] = arr[endIndex];
                arr[endIndex] = temp;
            }
            startIndex++;
            endIndex--;
        }
    }

    // Function to rotate an array by d elements in counter-clockwise direction.
    static void rotateArr(int arr[], int d) {
        // add your code here
        int n = arr.length;
        if(n < 2 || d == 0) return;
        d = d % n; // since cyclic
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
        reverseArray(arr, 0, n - 1);
    }

}
