package basic;

import java.util.Arrays;

// Link:
public class CountTribonnacci {

    public int countTribonnacciTopDownRec(int currNum){ // TLE because overlapping subproblems are recalculated
        if(currNum == 0) return 0;
        if(currNum == 1) return 1;
        if(currNum == 2) return 1;

        return countTribonnacciTopDownRec(currNum - 1) + countTribonnacciTopDownRec(currNum - 2) + countTribonnacciTopDownRec(currNum - 3);
    }

    public int countTribonnacciTopDownRecMemorization(int currNum, int[] dp){ // overlapping subproblems are stored in memory
        if(dp[currNum] != -1) return dp[currNum];

        return dp[currNum]  = countTribonnacciTopDownRecMemorization(currNum - 1, dp) + countTribonnacciTopDownRecMemorization(currNum - 2, dp) + countTribonnacciTopDownRecMemorization(currNum - 3, dp);
    }

    public int tribonacci(int n) {

//        return countTribonnacciTopDownRec(n);

//        int[] dp = new int[n+1];
//        Arrays.fill(dp, -1);

//        if(n <= 1) return n;
//        else{
//            for(int i = 0; i <= 1; i++){
//                dp[i] = i;
//            }
//            dp[2] = 1;
//        }

//        return countTribonnacciTopDownRecMemorization(n, dp);


//        for(int i = 3; i <= n; i++){
//            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
//        }
//        return dp[n];

        if(n <= 1) return n;
        if(n == 2) return 1;
        int thirdLast = 0;
        int secondLast = 1;
        int last = 1;
        int totalWays = 0;
        for(int i = 3; i <= n; i++){
            totalWays = thirdLast + secondLast + last;
            thirdLast = secondLast;
            secondLast = last;
            last = totalWays;
        }
        return totalWays;
    }
}
