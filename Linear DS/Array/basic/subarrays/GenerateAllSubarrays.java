package basic.subarrays;
/*
* You are given an array A of N integers.
* Return a 2D array consisting of all the subarrays of the array
* Note : The order of the subarrays in the resulting 2D array does not matter.
* */
public class GenerateAllSubarrays {
    public int[][] solve(int[] A) {
        int n = A.length;
        int totalSubArr = n*(n + 1)/2;
        int[][] answer = new int[totalSubArr][n];
        int s = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = i; j < A.length; j++){
                for(int k = i; k <=j; k++){
                    answer[s][k - i] = A[k];
                }
                s++; // s will be j - i + 1
            }
        }
        return answer;
    }
}
