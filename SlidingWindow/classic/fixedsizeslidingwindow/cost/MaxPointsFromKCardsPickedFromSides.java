package classic.fixedsizeslidingwindow.cost;

// Link: https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class MaxPointsFromKCardsPickedFromSides {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] prefixSumPoints = new int[n];
        prefixSumPoints[0] = cardPoints[0];
        for(int i = 1; i < n ; i++){
            prefixSumPoints[i] = prefixSumPoints[i - 1] + cardPoints[i];
        }

        // edge-case: when all cards are picked
        if(n == k) return prefixSumPoints[n - 1];
        int maxScore = Integer.MIN_VALUE;

        // picking exactly k cards from end
        maxScore = Math.max(maxScore, prefixSumPoints[n - 1] - prefixSumPoints[n - 1 - k]);

        // picking ( cards picked from left Edge, cards picked from right Edge) cards where cards picked from left edge combines with cards picked from right edge to give count of total k cards
        for(int i = 1; i < k; i++){
            int pickFromLeft = i;
            int pickFromRight = k - pickFromLeft;
            maxScore = Math.max(maxScore, prefixSumPoints[pickFromLeft - 1] + prefixSumPoints[n - 1] - prefixSumPoints[n - 1 - pickFromRight]);
        }

        // picking exactly k cards from start
        maxScore = Math.max(maxScore, prefixSumPoints[k - 1]);
        return maxScore;
    }
}
