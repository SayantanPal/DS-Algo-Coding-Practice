package all_possible_combination_of_subsequences.take_nottake;

public class CoinChangeWIthMinCoins {

    public static int topDownRec(int i, int[] num, int x){
        // since in each recursive step, we add one by one all the coins hence
        // by the time one subset combination is complete, we donot have anything to mark as valid subset count as such
        // so return is 0
        if(x == 0) return 0;

        // target sum x needs to be attained no matter what ie we cannot leave any portion of target sum figure
        // when 0th index denomiation is the last denomination to be picked
        // if x % num[0] == 0, then only with infinite supply of 0th index denomination,
        // I will be able to achieve the target sum, else not(hence invalid state 1000000007 which will be later marked as -1)
        // here for invalid state we have to use MAX_VALUE while doing min comparison but we cannot use Integer.MAX_VALUE
        // since 1+ 1+ 1+... during recursion call added with Integer.MAX_VALUE will overflow the int
        // hence, not pick state we have to put something which is still bigger reference to be counted while calculation Math.min()
        // but at the same time not overflowing whiel adding 1 + 1 +... incrementally to 1e9 + 7
        // hence, 1e9 + 7 or 10^9 + 7 has been chosen for not pick state to mark as invalid as well
        if(i == 0) return (x % num[0] == 0)? x/num[0]: 1000000007;

        int notTake = 0 + topDownRec(i - 1, num, x);
        int take = Integer.MAX_VALUE;
        if(x >= num[i])
            // we stay at index i and do not decrease further just after one take because
            // we have infinite supply of coins so, we keep on reducing the same coin again and again
            // until the balance amount becomes less than the denomination value
            // after that, we automatically move on not to take
            // if we still have some balance target sum figure which is less than current denomination value
            take = Math.min(take, 1 + topDownRec(i, num, x - num[i]));
        return Math.min(take, notTake);
    }

    public static int topDownMemorisation(int i, int[] num, int x, int[][] dp){

        if(x == 0) return 0;
        if(i == 0) return dp[i][x] = (x % num[0] == 0)? x/num[0]: 1000000007;

        if(dp[i][x] != -1) return dp[i][x];

        int notTake = 0 + topDownMemorisation(i - 1, num, x, dp);
        int take = Integer.MAX_VALUE;
        if(x >= num[i])
            take = Math.min(take, 1 + topDownMemorisation(i, num, x - num[i], dp));
        return dp[i][x] = Math.min(take, notTake);
    }

    public static int minimumElements(int num[], int x) {
        // Write your code here..
        int n = num.length;

        // if(topDown(n - 1, num, x) == 1000000007) return -1;
        // return topDown(n - 1, num, x);

        // int[][] dp = new int[n][x+1];
        // for(int i = 0; i < n;i++){
        //     Arrays.fill(dp[i], -1);
        // }
        // int result = topDownMemorisation(n - 1, num, x, dp);
        // return (result  == 1000000007) ? -1 : result;


        // Tabulation
        // for(int j = 0; j <= x; j++){
        //     if(j % num[0] == 0)
        //         dp[0][j] = j/num[0];
        //     else
        //         dp[0][j] = 1000000007;
        // }

        // for(int i = 1; i < n; i++){
        //     for(int j = 0; j <= x; j++){
        //         int notTake = dp[i-1][j];
        //         int take = Integer.MAX_VALUE;
        //         if(j >= num[i]){
        //             take = Math.min(take, 1 + dp[i][j - num[i]]);
        //         }
        //         dp[i][j] = Math.min(take, notTake);
        //     }
        // }

        // return dp[n-1][x] == 1000000007 ? -1: dp[n-1][x];

        // Tabulation to Space optimised 1D
        int[] dp = new int[x+1];

        for(int j = 0; j <= x; j++){
            if(j % num[0] == 0){
                dp[j] = j/num[0];
            } else{
                dp[j] = 1000000007;
            }
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= x; j++){
                int notTake = dp[j];
                int take = Integer.MAX_VALUE;
                if(j >= num[i]){
                    take = Math.min(take, 1 + dp[j - num[i]]);
                }
                dp[j] = Math.min(take, notTake);
            }
        }

        return dp[x] == 1000000007 ? -1: dp[x];
    }
}
