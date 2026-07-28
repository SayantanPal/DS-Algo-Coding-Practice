
// Link: https://leetcode.com/problems/rotate-image/
public class RotateMatrix {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose the matrix
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(i != j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        // rowwise reverse the elements in matrix
        for(int i = 0; i < n; i++){
            int s = 0, e = n - 1;
            while(s < e){
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
                s++;
                e--;
            }
        }
    }
}
