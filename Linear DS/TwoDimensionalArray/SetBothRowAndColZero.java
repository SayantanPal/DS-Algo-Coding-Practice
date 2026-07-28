

// Link: https://leetcode.com/problems/set-matrix-zeroes/description/
public class SetBothRowAndColZero {

    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        boolean hasAtleastOneZeroInFirstRow = false, hasAtleastOneZeroInFirstCol = false;


        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 0){
                hasAtleastOneZeroInFirstCol = true;
                break;
            }
        }

        for(int j = 0; j < m; j++){
            if(matrix[0][j] == 0){
                hasAtleastOneZeroInFirstRow = true;
                break;
            }
        }

        // use first row as row lookup and firs col as col lookup
        // mark the zero for the submatrix [1..(n-1)]x[1..(m-1)] except first row and first col
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // for(int i = 1; i < n; i++){
        //     if(matrix[i][0] == 0){
        //         for(int j = 1; j < m; j++){
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }

        // for(int j = 1; j < m; j++){
        //     if(matrix[0][j] == 0){
        //         for(int i = 1; i < n; i++){
        //             matrix[i][j] = 0;
        //         }
        //     }
        // }

        // whenever either row is zero or column is zero as per first row and first col lookup
        // set corresponding all elements of that row and all elements of that col as 0
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // if at least there was 1 original zero apart from later marked 0 in first row
        if(hasAtleastOneZeroInFirstRow){
            for(int j = 0; j < m; j++){
                matrix[0][j] = 0;
            }
        }

        // if at least there was 1 original zero apart from later marked 0 in first col
        if(hasAtleastOneZeroInFirstCol){
            for(int i = 0; i < n; i++){
                matrix[i][0] = 0;
            }
        }

    }
}
