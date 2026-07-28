import java.util.ArrayList;


// Link: https://www.geeksforgeeks.org/problems/boundary-traversal-of-matrix-1587115620/1
public class BoundaryElementsTraversal {

    public ArrayList<Integer> boundaryTraversal(int mat[][]) {
        // code here
        int n = mat.length;
        int m = mat[0].length;

        ArrayList<Integer> result = new ArrayList<Integer>();

        if(n == 1){
            for(int j = 0; j < m; j++){
                result.add(mat[0][j]);
            }
            return result;
        }

        if(m == 1){
            for(int i = 0; i < n; i++){
                result.add(mat[i][0]);
            }
            return result;
        }

        int i = 0, j = 0;
        // m - 1 step move right
        for(int k = 1; k <= m - 1; k++){
            result.add(mat[i][j++]);
        }

        // n - 1 step move down
        for(int k = 1; k <= n - 1; k++){
            result.add(mat[i++][j]);
        }

        // m - 1 step move left
        for(int k = 1; k <= m - 1; k++){
            result.add(mat[i][j--]);
        }

        // n - 1 step move up
        for(int k = 1; k <= n - 1; k++){
            result.add(mat[i--][j]);
        }

        return result;
    }
}
