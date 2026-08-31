package classic.fixedsizeslidingwindow.easy;

// Link: https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/
public class NoOfSubArrOfSizeKAvgGtEqThreshold {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int cntSubArr = 0;
        int sum = 0;
        for(int i = 0; i < k; i++){
            sum += arr[i];
        }
        if(sum/k >= threshold) cntSubArr++;

        for(int i = k; i < arr.length; i++){
            sum += arr[i] - arr[i - k];
            if(sum/k >= threshold) cntSubArr++;
        }
        return cntSubArr;
    }
}
