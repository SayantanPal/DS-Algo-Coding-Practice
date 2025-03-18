public class Fountain {

    public static int findMinFountains(int arr[], int n) {
        // Write your code here.
        if(n == 1) return 1;
        int l = 0;
        for(int i = 0; i < n; i++){
            if(i - arr[i] == 0){
                l = i;
            }
        }
        int r = l + arr[l];

        int minFountain = 1;

        while(r < n - 1){
            int maxiumArea = 0;
            for(int i = l; i <= r; i++){
                maxiumArea = Integer.max(maxiumArea, arr[i] + i);
            }
            minFountain++;
            l = r + 1;
            r = maxiumArea;
        }
        return minFountain;
    }

    public static void main(String[] args){

        int[] arr = {2, 1, 1, 1};
        int n = arr.length;
        findMinFountains(arr, n);
    }
}
