package all_possible_combination_of_subsequences;

public class CountSubsetsWithTargetSum {

    public static int topDownMem2(int i, int tar, int[] arr, int dp[][]){
        if(tar == 0) return 1; // if the target is already covered/achieved while scanning from left <- right, then it doesnot need to scan further left elements
        if(i == 0) return dp[i][tar] = (arr[0] == tar) ? 1 : 0; // if target is not covered/achieved up yet before scanning the first element from left <- right traversal, then there might be possibility that remaining first element is the target, else target not achieved

        if(dp[i][tar] != -1) return dp[i][tar];

        int take = 0;
        if(tar >= arr[i])
            take = topDownMem2(i - 1,tar - arr[i], arr, dp);
        int notTake = topDownMem2(i - 1,tar, arr, dp);

        return dp[i][tar] = (notTake + take);
    }

    public static long topDownMem(int i, int tar, int[] arr, long dp[][]){
        if(tar == 0) return 1; // if the target is already covered/achieved while scanning from left <- right, then it doesnot need to scan further left elements
        if(i == 0) return (arr[0] == tar) ? 1 : 0; // if target is not covered/achieved up yet before scanning the first element from left <- right traversal, then there might be possibility that remaining first element is the target, else target not achieved

        if(dp[i][tar] != -1) return dp[i][tar];
        long notTake = topDownMem(i - 1,tar, arr, dp);
        long take = 0L;
        if(tar >= arr[i])
            take = topDownMem(i - 1,tar - arr[i], arr, dp);
        return dp[i][tar] = take + notTake;
    }

    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;
        long countZero = 0;
        for(int i = 0; i < n; i++){
            if(num[i] == 0) countZero++;
        }

        long dp[][] = new long[n][tar + 1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= tar; j++){
                dp[i][j] = -1;
            }
        }

        int result = ((int)((topDownMem(n - 1, tar, num, dp)+countZero)) % 1000000007); // %((long)Math.pow(10,9) + 7);
        return result;
    }



    public static void main(String[] args) {

        int target = 5;
        int arr[] = {1, 4, 4, 5};
        int arr2[] = {1, 1, 4, 5};
        int arr4[] = {1, 1, 2, 2, 3, 3, 4, 4};

        System.out.println(findWays(arr, target));
        System.out.println(findWays(arr2, target));
        System.out.println(findWays(arr4, target));


        target = 3;
        int arr3[] = {1, 1, 2, 2};
        System.out.println(findWays(arr3, target));

        target = 6;
        int arr5[] = {1, 1, 2, 2, 3, 3};
        System.out.println(findWays(arr5, target));

    }
}
