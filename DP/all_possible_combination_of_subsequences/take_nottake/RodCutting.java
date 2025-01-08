package all_possible_combination_of_subsequences.take_nottake;

import java.util.Arrays;

public class RodCutting {

    public static int TopDownRec(int i, int[] value, int target){
        // since in each recursive step, we add one by one all the items hence
        // by the time one subset combination is complete, we donot have anything to mark as valid subset count as such
        // so return is 0
        if(target == 0) return 0;

        if(i == 0) return value[0]*(target/(i+1)); //(target % (i+1)) == 0 ? value[0]*(target/(i+1)) : 10000007;

        int notTake = 0 + TopDownRec(i - 1, value, target);
        int take = Integer.MIN_VALUE;
        if(target >= (i + 1))
            // we stay at index i and do not decrease further just after one take because
            // we have infinite supply of each item so, we keep on reducing the weight of same item again and again
            // until the remaining weight becomes less than that particular item weight
            // after that, we automatically move on not to take
            // if we still have some remaining sack weight which is less than current item weight
            take = value[i] + TopDownRec(i, value, target - (i+1));
        return Math.max(notTake, take);
    }

    public static int TopDownRecMem(int i, int[] value, int target, int[][] dp){
        // since in each recursive step, we add one by one all the items hence
        // by the time one subset combination is complete, we donot have anything to mark as valid subset count as such
        // so return is 0
        if(target == 0) return 0;

        if(i == 0) return dp[i][target] = value[0]*(target/(i+1)); //(maxWeight % (i+1)) == 0 ? value[0]*(maxWeight/(i+1)) : 10000007;

        if(dp[i][target] != -1) return dp[i][target];

        int notTake = 0 + TopDownRecMem(i - 1, value, target, dp);
        int take = Integer.MIN_VALUE;
        if(target >= (i + 1))
            // we stay at index i and do not decrease further just after one take because
            // we have infinite supply of each item so, we keep on reducing the weight of same item again and again
            // until the remaining weight becomes less than that particular item weight
            // after that, we automatically move on not to take
            // if we still have some remaining sack weight which is less than current item weight
            take = value[i] + TopDownRecMem(i, value, target - (i+1), dp);
        return dp[i][target] = Math.max(notTake, take);
    }

    public static int cutRod(int price[], int n) {
        // Write your code here.
        int N = price.length;
        // return TopDownRec(N-1, price, n);

        int[][] dp = new int[N][n+1];
        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], -1);
        }
        return TopDownRecMem(N-1, price, n, dp);
    }

}
