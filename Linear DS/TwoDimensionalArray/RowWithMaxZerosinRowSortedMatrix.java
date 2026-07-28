public class RowWithMaxZerosinRowSortedMatrix {
    public int rowAndMaximumOnes_BruteForce(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int rowWithMaxOnes = -1;
        int maxOnes = 0;
        for(int i = 0; i < n; i++){
            int countOfOnes = 0;
            for(int j = m - 1; j >=0; j--){
                if(A[i][j] == 1) countOfOnes++;
                else break;
            }
            if(countOfOnes > maxOnes){
                maxOnes = countOfOnes;
                rowWithMaxOnes = i;
            }
            if(countOfOnes == m){
                break;
            }
        }
        return rowWithMaxOnes;
    }

    public int[] rowAndMaximumOnes_optimised(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int i = 0, j = m - 1;
        int minRowWithMaxOne = -1;
        int maxCountOfOnes = 0;

        int countOfOnes = 0;
        while(i < n){
            while(j >= 0 && mat[i][j] == 1){
                j--;
                countOfOnes++;
            }
            if(countOfOnes > maxCountOfOnes){
                maxCountOfOnes = countOfOnes;
                minRowWithMaxOne = i;
            }
            i++;
        }

        return new int[] {minRowWithMaxOne, maxCountOfOnes};
    }
}
