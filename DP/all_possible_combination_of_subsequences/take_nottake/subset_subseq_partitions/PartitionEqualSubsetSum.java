package all_possible_combination_of_subsequences.take_nottake.subset_subseq_partitions;

// Link: https://leetcode.com/problems/partition-equal-subset-sum/
public class PartitionEqualSubsetSum {
    public int topDownRec(int i, int[] nums, int sum, int[][] dp){

        if(sum == 0) return 1;
        if(i == 0) return (nums[i] == sum) ? 1: 0;

        if(dp[i][sum] != -1) return dp[i][sum];

        int notTake = topDownRec(i - 1, nums, sum, dp);
        int take = 0;
        if(sum >= nums[i])
            take = topDownRec(i - 1, nums, sum - nums[i], dp);
        return dp[i][sum] = (take == 1 || notTake == 1) ? 1 : 0;
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++)
            sum += nums[i];
        int[][] dp = new int[n][sum + 1];
        for(int i = 0; i < n; i++)
            for(int j = 0; j <= sum; j++)
                dp[i][j] = -1;

        if(sum % 2 == 0){
            return topDownRec(n - 1, nums, sum/2, dp) == 1 ? true : false;
        } else{
            return false;
        }
    }
}
