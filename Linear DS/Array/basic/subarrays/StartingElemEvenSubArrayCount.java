package basic.subarrays;

public class StartingElemEvenSubArrayCount {

    public int findEvenNumberStartingElemSubArrCount(int[] A){
        long countSubArrays = 0;
        int n = A.length;
        for(int i = 0; i < n; i++){
            if(A[i] % 2 == 0){
                countSubArrays += (n - i);
            }
        }
        return (int)(countSubArrays/1000000000);
    }
}
