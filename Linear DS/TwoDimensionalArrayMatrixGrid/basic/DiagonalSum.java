package basic;

// Link: https://leetcode.com/problems/matrix-diagonal-sum/description/
public class DiagonalSum {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int i = 0, j = n - 1; // top right corner
        int sumOfDiagonal = 0;
        while(i < n && j >= 0){ // to bottom left corner
            sumOfDiagonal += mat[i][j];
            i++;
            j--;
        }

        i = 0; j = 0; // top left corner
        while(i < n && j < n){ // to bottom right corner
            sumOfDiagonal += mat[i][j];
            i++;
            j++;
        }

        // remove duplicate count of matrix center in case of odd length square matrix
        if(n % 2 != 0)
            sumOfDiagonal -= mat[n/2][n/2];

        return sumOfDiagonal;
    }

    public int diagonalSum_v2(int[][] mat) {
        int diagonalSum = 0;
        int n = mat.length;

        // top left corner to bottom right corner
        for(int i = 0; i < n; i++){
            diagonalSum += mat[i][i];
        }

        int i = 0, j = n - 1;
        while(i < n && j >= 0){
            if(i != j) diagonalSum += mat[i][j]; // for odd length square matrix
            i++;
            j--;
        }

        return diagonalSum;
    }
}
