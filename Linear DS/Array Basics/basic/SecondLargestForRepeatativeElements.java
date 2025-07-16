package basic;

/*Link: Given an array of positive integers arr[], return the second largest element from the array. If the second largest element doesn't exist then return -1.
*Note: The second largest element should not be equal to the largest element.
*/
public class SecondLargestForRepeatativeElements {

    public int getSecondLargest(int[] arr) {
        // Code Here
        int n = arr.length;
        if(n < 2) return -1;

        int largest = -1;
        int secondLargest = -1;

        for(int i = 0; i < n; i++){
            if(arr[i] > largest){
                secondLargest = largest;
                largest = arr[i];
            } else if(arr[i] > secondLargest && arr[i] != largest){
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

}
