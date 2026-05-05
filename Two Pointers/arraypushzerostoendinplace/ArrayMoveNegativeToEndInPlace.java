package arraypushzerostoendinplace;

// Link: https://www.geeksforgeeks.org/problems/move-all-negative-elements-to-end1813/1
public class ArrayMoveNegativeToEndInPlace {
    public void segregateElements(int[] arr) {
        // code here
        int slow = 0, fast = 0;
        int n = arr.length;

        while(fast < n){
            if(arr[fast] >= 0){
                int temp = arr[fast];
                arr[fast] = arr[slow];
                arr[slow] = temp;
                slow++;
            }
            fast++;
        }
    }
}
