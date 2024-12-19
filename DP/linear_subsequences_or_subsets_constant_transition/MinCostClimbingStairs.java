package linear_subsequences_or_subsets_constant_transition;

// Link: https://leetcode.com/problems/min-cost-climbing-stairs/


// Problem states the cost of each ith step is the cost to reach 1 or 2 steps above

// Note: the cost of each ith step is not the cost to reach that step
// so to reach ith step, the cost is either ie min of
// cost of 1 step down along with cost of 1 step down to reach i
// or cost of 2 steps down along with cost of 2 steps down

// Note 2: Goal is to reach the top which is not the last index. It is index + 1.
// Because last index is the last stair step and not the top destination.

// min cost from floor 0th index to reach currStep = min of
// min cost from floor 0th index to reach currStep - 1 + cost to reach from currStep-1 to currStep(i.e., cost[currStep-1])
// min cost from floor 0th index to reach currStep - 2 + cost to reach from currStep-2 to n(i.e., cost[currStep-2])

import java.util.Arrays;

public class MinCostClimbingStairs {

    public int minCostClimbingStairsTopDownRec(int currStep, int[] cost){
        if(currStep <= 1) return 0;

        return Math.min(minCostClimbingStairsTopDownRec(currStep - 1, cost) + cost[currStep - 1], minCostClimbingStairsTopDownRec(currStep - 2, cost) + cost[currStep - 2]);
    }

    public int minCostClimbingStairsTopDownMemorisation(int currStep, int[] cost, int[] dp){
        if(dp[currStep] != -1) return dp[currStep];

        return dp[currStep] = Math.min(minCostClimbingStairsTopDownMemorisation(currStep - 1, cost, dp) + cost[currStep - 1], minCostClimbingStairsTopDownMemorisation(currStep - 2, cost, dp) + cost[currStep - 2]);
    }


    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
//        return minCostClimbingStairsTopDownRec(n, cost);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        if(n == 0) return 0; // if there are at least one element i, then also i+1 counts as top
        dp[0] = 0;
        dp[1] = 0;
        return minCostClimbingStairsTopDownMemorisation(n, cost, dp);
    }

}
