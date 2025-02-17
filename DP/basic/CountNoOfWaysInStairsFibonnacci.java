package basic;

import java.util.Arrays;

// Link: https://leetcode.com/problems/climbing-stairs/
public class CountNoOfWaysInStairsFibonnacci {

    public int countWaysTopDownRec(int currStep){ // TLE for n = 45 because overlapping subproblems are recalculated
        if(currStep <= 2) return currStep;

        return countWaysTopDownRec(currStep - 1) + countWaysTopDownRec(currStep - 2);
    }

    public int countWaysTopDownRecMemorization(int currStep, int[] dp){ // overlapping subproblems are stored in memory
        if(currStep <= 2) return currStep;
        
        if(dp[currStep] != -1) return dp[currStep];

        return dp[currStep]  = countWaysTopDownRecMemorization(currStep - 1, dp) + countWaysTopDownRecMemorization(currStep - 2, dp);
    }

    public int climbStairs(int n) {
//        return countWaysTopDownRec(n); // TLE for n = 45

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        // for 0th stair step, ways = 0,
        // for 1st stair step from 0th, its 1
        // and similarly for 2, it's 2
        if(n <= 2) return n;
        else{
            for(int i = 0; i <= 2; i++){
                dp[i] = i;
            }
        }

        // from stair step-3,
        // pattern of optimal substructure and overlapping subproblem follows

        // Top down Memorization
//        return countWaysTopDownRecMemorization(n, dp);

        // Bottom up Memorization with Tabulation(arrays pre-filling with -1 not needed)
        /*
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
        */

        // Bottom Up Memorization using 2 pointers
        // since pattern depends on past 2 values(Space optimised)
        // previous code pre-requisites not needed
        if(n <= 2) return n;
        int secondLast = 1;
        int last = 2;
        int totalWays = 0;
        for(int i = 3; i <= n; i++){
            totalWays = last + secondLast;
            secondLast = last;
            last = totalWays;
        }
        return totalWays;
    }
}
