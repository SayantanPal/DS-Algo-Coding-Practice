package all_possible_combination_of_subsequences.take_nottake;

public class CoinChangeTotalWays {
    public static int topDown(int i, int[] num, int x){
        if(x == 0) return 1;

        // target sum x needs to be attained no matter what ie we cannot leave any portion of target sum figure
        // when 0th index denomiation is the last denomination to be picked
        // if x % num[0] == 0, then only with infinite supply of 0th index denomination,
        // I will be able to achieve the target sum, else not(0)
        // here for invalid cannot pick state we have to use 0 since in each recursion call, nothing is added incrementally
        // only when the combination of subset matches target, we are marking that particular combination subset with 1
        // hence 0 is chosen for not pick state which states target sum is marked as CANNOT be formed with such combination(similar like invalid state)
        if(i == 0) return x % num[0] == 0 ? 1 : 0;

        int notTake = 0 + topDown(i - 1, num, x);
        int take = 0;
        if(x >= num[i])
            // we stay at index i and do not decrease further just after one take because
            // we have infinite supply of coins so, we keep on reducing the same coin again and again
            // until the balance amount becomes less than the denomination value
            // after that, we automatically move on not to take
            // if we still have some balance target sum figure which is less than current denomination value
            take = topDown(i, num, x - num[i]);

        // we do (+) because we add up all possibilities of if taken or not taken
        return take + notTake;
    }

    public static long topDownMem(int i, int[] num, int x, long[][] dp){

        if(x == 0) return 1;
        if(i == 0) return dp[i][x] = x % num[0] == 0 ? 1 : 0;

        if(dp[i][x] != -1) return dp[i][x];

        long notTake = topDownMem(i - 1, num, x, dp);
        long take = 0;
        if(x >= num[i])
            take = topDownMem(i, num, x - num[i], dp);

        return dp[i][x] = take + notTake;
    }

    public static long countWaysToMakeChange(int denominations[], int value){
        //write your code here
        int n = denominations.length;
        // return topDown(n - 1, denominations, value);

        // long[][] dp = new long[n][value+1];
        // for(int i = 0; i < n ;i++){
        //     Arrays.fill(dp[i], -1L);
        // }
        // return topDownMem(n - 1, denominations, value, dp);

        // Tabulation
        // long[][] dp = new long[n][value+1];
        // for(int i = 0 ; i < n; i++)
        //     dp[i][0] = 1L;

        // for(int j = 0; j <= value; j++)
        //     if(j % denominations[0] == 0)
        //         dp[0][j] = 1L;

        // for(int i = 1; i < n; i++){
        //     for(int j = 0; j <= value; j++){
        //         long notTake = dp[i-1][j];
        //         long take = 0L;
        //         if(j >= denominations[i])
        //             take = dp[i][j - denominations[i]];
        //         dp[i][j] = take + notTake;
        //     }
        // }
        // return dp[n-1][value];

        // Tabulation to Space optimised 1D
        long[] dp = new long[value+1];

        dp[0] = 1L;

        for(int j = 0; j <= value; j++)
            if(j % denominations[0] == 0)
                dp[j] = 1L;

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= value; j++){
                long notTake = dp[j];
                long take = 0L;
                if(j >= denominations[i])
                    take = dp[j - denominations[i]];
                dp[j] = take + notTake;
            }
        }
        return dp[value];
    }
}
