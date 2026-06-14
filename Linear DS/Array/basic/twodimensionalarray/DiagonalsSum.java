package basic.twodimensionalarray;

public class DiagonalsSum {

    // Main/Principal Diagonal: Top-Left To Bottom-Right
    public int findMainDiagonalSumForSquareMatrix(final int[][] A) {
        int sum = 0;
        for(int i = 0; i < A.length; i++){
            sum += A[i][i];
        }
        return sum;
    }

    // Minor Diagonal: Top-Right To Bottom-Left
    public int findMinorDiagonalSumForMatrix(final int[][] A) {
        int n = A.length;
        int m = 0;
        if(n > 0){
            m = A[0].length;
        }
        int minorDiagonalSum = 0;
        int i = 0, j = m - 1;
        while(i < n && j >=0){
            minorDiagonalSum += A[i][j];
            i++;
            j--;
        }
        return minorDiagonalSum;
    }

    private void printLeftAntiDiagonals(int k, int j, int n, int[][] antiDiagonal, int[][] A){
        int i = 0;
        while(i < n && j >= 0){
            antiDiagonal[k][i] = A[i][j];
            i++;
            j--;
        }
    }

    private void printRightAntiDiagonals(int k, int i, int m, int n, int[][] antiDiagonal, int[][] A){
        int j = m - 1;
        while(i < n && j >= 0){
            antiDiagonal[k][m - 1 - j] = A[i][j];
            i++;
            j--;
        }
    }

    public int[][] printDiagonal(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] antiDiagonal = new int[n + m - 1][Math.max(n, m)];

        int k = 0;
        for(int j = 0; j < m; j++){ // 0 to m-1 -> m left anti diagonals
            printLeftAntiDiagonals(k, j, n, antiDiagonal, A);
            k++;
        }

        for(int i = 1; i < n; i++){ // 0 to n-1 -> n right anti diagonals
            printRightAntiDiagonals(k, i, m, n, antiDiagonal, A);
            k++;
        }

        return antiDiagonal;
    }
}
