package all_possible_combination_of_subsequences.take_nottake.subset_subseq_partitions;

// Link - https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
public class SubsetSum {

    static int topDownRec(int i, int[] nums, int sum, int[][] dp){

        if(sum == 0) return 1;
        if(i == 0) return (nums[i] == sum) ? 1: 0;

        if(dp[i][sum] != -1) return dp[i][sum];

        int notTake = topDownRec(i - 1, nums, sum, dp);
        int take = 0;
        if(sum >= nums[i])
            take = topDownRec(i - 1, nums, sum - nums[i], dp);
        return dp[i][sum] = (take == 1 || notTake == 1) ? 1 : 0;
    }

    static Boolean isSubsetSum(int arr[], int target) {
        // code here

        int n = arr.length;
        int[][] dp = new int[n][target + 1];
        for(int i = 0; i < n; i++)
            for(int j = 0; j <= target; j++)
                dp[i][j] = -1;
        return topDownRec(n - 1, arr, target, dp) == 1 ? true : false;
    }
}
