import java.util.ArrayList;
import java.util.List;

// Link: https://leetcode.com/problems/spiral-matrix/
public class SpiralMatrixTraversal {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        int n = matrix.length;
        int m = matrix[0].length;

        // for single column, traverse all rows
        if(m == 1){
            for(int i = 0; i < n; i++){
                result.add(matrix[i][0]);
            }
            return result;
        }

        // for single row, traverse all columns
        if(n == 1){
            for(int j = 0; j < m; j++){
                result.add(matrix[0][j]);
            }
            return result;
        }

        int i = 0, j = 0;
        while(m > 1 && n > 1){
            for(int k = 0; k < m - 1; k++){ // from left -> right
                result.add(matrix[i][j]);
                j++;
            }

            // from top
            //  |
            //  v
            // down
            for(int k = 0; k < n - 1; k++){
                result.add(matrix[i][j]);
                i++;
            }

            for(int k = 0; k < m - 1; k++){ // from left <- right
                result.add(matrix[i][j]);
                j--;
            }

            // top
            //  ^
            //  |
            // from down
            for(int k = 0; k < n - 1; k++){
                result.add(matrix[i][j]);
                i--;
            }

            i++;
            j++;

            m -= 2;
            n -= 2;
        }

        if(n == 1){
            for(int k = j; k < j + m; k++){
                result.add(matrix[i][k]);
            }
        }else if(m == 1){ //else if is imp when m == n in case of square matrix, then both becomes 1 and the centre element can be added only once
            for(int k = i; k < i + n; k++){
                result.add(matrix[k][j]);
            }
        }
        return result;
    }
}
