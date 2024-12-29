package all_possible_combination_of_subsequences;

public class ZeroOneKnapsack {

    static int TopDownRec(int i, int[] weight, int[] value, int maxWeight){
        // since in each recursive step, we add one by one all the items hence
        // by the time one subset combination is complete, we donot have anything to mark as valid subset count as such
        // so return is 0
        if(maxWeight == 0) return 0;

        // since we have to take either 0 (not take) or 1(take) and we have only single count(NOT infinite supply) of each item
        // hence we simply check if the weight[i] is less than or equal to remaining sack capacity then only we can take that item only once
        // so single value[0] if picked up, else 0 if cannot be picked in opposite case
        if(i == 0) return (maxWeight >= weight[i]) ? value[i] : 0;

        int notTake = 0 + TopDownRec(i - 1, weight, value, maxWeight);
        int take = Integer.MIN_VALUE;
        if(maxWeight >= weight[i])
            // we move to index i-1 from i because we count that item only once because of single supply of each item
            // then we have to check another item placed before
            take = value[i] + TopDownRec(i - 1, weight, value, maxWeight - weight[i]);
        return Math.max(notTake, take);
    }

    static int TopDownRecMemorisation(int i, int[] weight, int[] value, int maxWeight, int[][] dp){
        if(maxWeight == 0) return 0;
        if(i == 0) return dp[i][maxWeight] = (maxWeight >= weight[i]) ? value[i] : 0;

        if(dp[i][maxWeight] != -1) return dp[i][maxWeight];

        int notTake = TopDownRecMemorisation(i - 1, weight, value, maxWeight, dp);
        int take = Integer.MIN_VALUE;
        if(maxWeight >= weight[i])
            take = value[i] + TopDownRecMemorisation(i - 1, weight, value, maxWeight - weight[i], dp);
        return dp[i][maxWeight] = Math.max(notTake, take);
    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        // int dp[][] = new int[n][maxWeight + 1];
        // for(int i = 0; i < n; i++){
        //     Arrays.fill(dp[i], -1);
        // }
        // return TopDownRecMemorisation(n - 1, weight, value, maxWeight, dp);


        // Tabulation to Space optimised 1D
        int dp[] = new int[maxWeight + 1];
        for(int j = weight[0]; j <= maxWeight; j++){
            dp[j] = value[0];
        }

        if(n == 1) return maxWeight >= weight[0] ? value[0] : 0;
        if(maxWeight == 0) return 0;

        for(int i = 1; i < n; i++){
            for(int j = maxWeight; j >= 0; j--){
                int notTake = dp[j];
                int take = Integer.MIN_VALUE;
                if(j >= weight[i]){
                    take = value[i] + dp[j-weight[i]];
                }
                dp[j] = Math.max(take, notTake);
            }
        }
        return dp[maxWeight];
    }
}
