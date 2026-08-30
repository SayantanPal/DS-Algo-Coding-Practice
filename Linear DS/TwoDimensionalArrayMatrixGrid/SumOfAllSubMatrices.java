public class SumOfAllSubMatrices {

    public int sumOfSubMatrices(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int individualContributionSum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                individualContributionSum += A[i][j] * (i + 1) * (j + 1) * (n - i) * (m - j);
            }
        }
        return individualContributionSum;
    }
}
