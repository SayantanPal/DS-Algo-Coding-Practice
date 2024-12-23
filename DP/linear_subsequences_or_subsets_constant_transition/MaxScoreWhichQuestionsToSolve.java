package linear_subsequences_or_subsets_constant_transition;

import java.util.Arrays;

// Link: https://leetcode.com/problems/solving-questions-with-brainpower/description/
public class MaxScoreWhichQuestionsToSolve {

    public long mostPointsBottomUpRec(int currQuestionIndex, int n, int[][] questions){
        if(currQuestionIndex >= n) return 0;

        long maxPoints = Math.max(mostPointsBottomUpRec(currQuestionIndex + 1, n, questions), mostPointsBottomUpRec(currQuestionIndex + questions[currQuestionIndex][1] + 1, n, questions) + questions[currQuestionIndex][0]);
        return maxPoints;
    }

    public long mostPointsBottomUpRecMemorisation(int currQuestionIndex, int n, int[][] questions, long[] dp){
        if(currQuestionIndex >= n) return 0;
        if(dp[currQuestionIndex] != -1) return dp[currQuestionIndex];

        return dp[currQuestionIndex] = Math.max(mostPointsBottomUpRecMemorisation(currQuestionIndex + 1, n, questions, dp), mostPointsBottomUpRecMemorisation(currQuestionIndex + questions[currQuestionIndex][1] + 1, n, questions, dp) + questions[currQuestionIndex][0]);
    }

    public long mostPointsBottomUpTabularisation(int n, int[][] questions){

        long[] dp = new long[n];
        dp[n-1] = questions[n-1][0];

        for(int i = n - 2; i >= 0 ; i--){
            long currQuestionPoint = questions[i][0];
            int currSkipIndex = questions[i][1];
            long nextQuestionPoint = dp[i + 1];
            long additionalPoints = 0;
            if(i + currSkipIndex + 1 <= n - 1){
                additionalPoints = dp[i + currSkipIndex + 1];
            }
            long option1 = currQuestionPoint + additionalPoints; // take
            long option2 = nextQuestionPoint; // not take
            dp[i] = Math.max(option1, option2);
        }
        return dp[0];
    }

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        // return mostPointsBottomUpRec(0, n, questions);
        long[] dp = new long[n];
        Arrays.fill(dp, -1);
        // return mostPointsBottomUpRecMemorisation(0, n, questions, dp);
        return mostPointsBottomUpTabularisation(n, questions);
    }
}
