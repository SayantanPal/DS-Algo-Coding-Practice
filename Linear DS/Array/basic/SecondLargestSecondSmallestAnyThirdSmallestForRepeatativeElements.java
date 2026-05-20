package basic;

import java.util.ArrayList;

/*Link: Given an array of positive integers arr[], return the second largest element from the array. If the second largest element doesn't exist then return -1.
*Note: The second largest element should not be equal to the largest element.
*/
// Link: https://www.geeksforgeeks.org/problems/find-the-smallest-and-second-smallest-element-in-an-array3226/1
public class SecondLargestSecondSmallestAnyThirdSmallestForRepeatativeElements {

    public ArrayList<Integer> minAnd2ndMin(int[] arr) {
        // code here
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < arr.length; i++){
            // do not use <= because in that case 1st and 2nd smallest will be same
            if(arr[i] < smallestNo){

                // below 2 lines are required - otherwise in case of monotonously decreasing order elements, second smallest no. will never be captured
                // below 2 lines are required when the second smallest lies in the LHS of first smallest number, else in case of descending order subsequence,
                // second smallest will start getting detected as the smallest to the RHS of first smallest
                secondSmallestNo = smallestNo;
                secondSmallestIndex = smallestIndex;

                smallestNo = arr[i];
                smallestIndex = i;
            }else if(arr[i] != smallestNo && arr[i] <= secondSmallestNo){ // use <= when you want higher index for same second highest element; for lower index, use <
                secondSmallestNo = arr[i];
                secondSmallestIndex = i;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        if(smallestNo != Integer.MAX_VALUE && secondSmallestNo != Integer.MAX_VALUE){
            result.add(smallestNo);
            result.add(secondSmallestNo);
        }else{
            result.add(-1);
        }

        return result;
    }

    public int getSecondSmallestNoOnRHSOfSmallest(int[] arr) {
        // code here
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] <= smallestNo){
                smallestNo = arr[i];
                smallestIndex = i;
            }else if(arr[i] != smallestNo && arr[i] <= secondSmallestNo){ // else-if + not tracking the prev smallest index as second smallest index makes 2nd smallest always to the RHS of global smallest
                secondSmallestNo = arr[i];
                secondSmallestIndex = i;
            }
        }

        return secondSmallestNo;
    }

    // 1, 5, 6, 2, 1, 5, 2: index 0 to index n-1 ie 6
    public int getMaxDistanceBetweenDistinctGlobalSmallestAndDistinctSecondSmallestOnRHSOfGlobalSmallest(int[] arr) {
        // code here
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < smallestNo){
                smallestNo = arr[i];
                smallestIndex = i;
            }else if(arr[i] != smallestNo && arr[i] <= secondSmallestNo){ // else-if + not tracking the prev smallest index as second smallest index makes 2nd smallest always to the RHS of global smallest
                secondSmallestNo = arr[i];
                secondSmallestIndex = i;
            }
        }

        return secondSmallestIndex - smallestIndex + 1;
    }

    // 1, 5, 6, 1, 2, 5, 2 // smallest at higher index 3 and second smallest at lower index 2
    // 1, 5, 6, 2, 1, 5, 2: // smallest at higher index 4 but second smallest at lower index 2 => 2nd smallest crossed to RHS of smallest
    public int getMinDistanceBetweenDistinctGlobalSmallestAndDistinctSecondSmallestOnRHSOfGlobalSmallest(int[] arr) {
        // code here
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE, minLen = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] <= smallestNo){
                smallestNo = arr[i];
                smallestIndex = i;
            }else if(arr[i] != smallestNo && arr[i] <= secondSmallestNo){ // else-if + not tracking the prev smallest index as second smallest index makes 2nd smallest always to the RHS of global smallest
                secondSmallestNo = arr[i];
                secondSmallestIndex = i;
                if(secondSmallestIndex > smallestIndex)
                    minLen  = Math.min(minLen, secondSmallestIndex - smallestIndex + 1);
            }
        }

        return minLen;
    }

    // Any Distinct Global Second Smallest to RHS of Distinct Global Smallest + Any Distinct 3rd smallest To the RHS of Distinct Second Smallest
    // Link: https://leetcode.com/problems/increasing-triplet-subsequence/
    public boolean increasingTriplet(int[] nums) {
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= smallestNo){
                smallestNo = nums[i];
                smallestIndex = i;
            }else if(nums[i] != smallestNo && nums[i] <= secondSmallestNo){ // else-if + no tracking the prev smallest index as second smallest index makes 2nd smallest always to the RHS of global smallest
                secondSmallestNo = nums[i];
                secondSmallestIndex = i;
            }else{ // else + not tracking the prev smallest index as second smallest index makes 2nd smallest always to the RHS of global smallest + not tracking prev 2nd smallest as 3rd smallest makes 3rd smallest always to RHS of second smallest on RHS of global smallest
                return true;
            }
        }
        return false;
    }

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
