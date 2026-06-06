package classic;

/*
* Given an array A, find the size of the smallest subarray such that it contains at least one occurrence of the maximum value of the array and at least one occurrence of the minimum value of the array.
*
* */
public class SmallestSubArrayWithMinMaxAtExtremes {

    public int findSmallestSubArrayWithBothMinAndMax(int[] A) {
        int n = A.length;
        int maximum = Integer.MIN_VALUE, minimum = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            maximum = Math.max(maximum, A[i]);
            minimum = Math.min(minimum, A[i]);
        }

        // System.out.println("Min: "+ minimum);
        // System.out.println("Max: "+ maximum);

        int lastMin = -1, lastMax = -1;
        int smallestLen = n + 1;
        for(int i = 0; i < n; i++){
            if(A[i] == maximum){
                lastMax = i;
            }
            if(A[i] == minimum){
                lastMin = i;
            }
            if(lastMax != -1 && lastMin != -1){
                smallestLen = Math.min(smallestLen, Math.abs(lastMin - lastMax) + 1);
            }
        }
        return smallestLen;
    }
}
