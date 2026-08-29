package basic;

// Link: https://leetcode.com/problems/matrix-diagonal-sum/description/
public class DiagonalSum {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int i = 0, j = n - 1;
        int sumOfDiagonal = 0;
        while(i < n && j >= 0){
            sumOfDiagonal += mat[i][j];
            i++;
            j--;
        }

        i = 0; j = 0;
        while(i < n && j < n){
            sumOfDiagonal += mat[i][j];
            i++;
            j++;
        }

        if(n % 2 != 0)
            sumOfDiagonal -= mat[n/2][n/2];

        return sumOfDiagonal;
    }

    public int diagonalSum_v2(int[][] mat) {
        int diagonalSum = 0;
        int n = mat.length;
        for(int i = 0; i < n; i++){
            diagonalSum += mat[i][i];
        }

        int i = 0, j = n - 1;
        while(i < n && j >= 0){
            if(i != j) diagonalSum += mat[i][j];
            i++;
            j--;
        }

        return diagonalSum;
    }
}
