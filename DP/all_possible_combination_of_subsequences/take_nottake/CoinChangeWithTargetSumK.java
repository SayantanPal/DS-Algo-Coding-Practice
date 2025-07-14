package all_possible_combination_of_subsequences.take_nottake;

import java.util.Arrays;

public class CoinChangeWithTargetSumK {

    public static boolean topDownRec(int i, int K, int target, int[] coins, int count){

        if(target == 0) return count == K;

        if(i == 0){
            if(target >= coins[0]){
                count += (target/coins[0]);
                return count == K;
            }
            return false;
        }

        boolean notTake = topDownRec(i - 1, K, target, coins, count);
        boolean take = false;
        if(target >= coins[i])
            take = topDownRec(i, K, target - coins[i], coins, count + 1);

        return take || notTake;
    }

    public static int topDownRecWithMem(int i, int K, int target, int[] coins, int count, int[][][] dp){

        if(count > K) return 0;

        if(target == 0) return (count == K) ? 1 : 0;

        // if(i < 0) return 0;  // handle boundary

        // if(i == 0) {
        //     if(target%coins[i] == 0){
        //         count += (target/coins[0]);
        //         return (count == K) ? 1 : 0;
        //     }
        //     return 0;
        // }

        if(i == 0) return (target%coins[i] == 0) ? (( (count + (target/coins[0])) == K) ? 1 : 0) : 0;

        if(dp[i][target][count] != -1) return dp[i][target][count];

        int notTake = topDownRecWithMem(i - 1, K, target, coins, count, dp);
        int take = 0;
        if(target >= coins[i])
            take = topDownRecWithMem(i, K, target - coins[i], coins, count + 1, dp);

        return dp[i][target][count] = (take + notTake);
    }

    public static boolean makeChanges(int N, int K, int target, int[] coins) {
        // code here

//        return topDownRec(N - 1, K, target, coins, 0);

        int dp[][][] = new int[N][target + 1][K + 1];
        for(int i = 0; i < N; i++){
            for(int j = 0; j <= target; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        return topDownRecWithMem(N - 1, K, target, coins, 0, dp) >= 1;
    }


}
