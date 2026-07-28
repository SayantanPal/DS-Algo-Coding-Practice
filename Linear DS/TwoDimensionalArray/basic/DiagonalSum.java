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
}
