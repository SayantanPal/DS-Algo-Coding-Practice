package two_dimensional_grid_matrix.dfs;

import util.InputUtils;
import util.PrintOutputUtils;

// only the connected group of 0's connecting to any of 4 edges will remain 0
// so that all the remaining disconnected group/cluster of 0's just can be converted to X
// that means only group of 0's surrounded by X in all 4 sides(up, down, left, right) will be converted to X


// Link: https://www.naukri.com/code360/problems/replace-o-with-x_630517?leftPanelTabValue=PROBLEM&count=25&page=1&search=&sort_entity=order&sort_order=ASC
// Link: https://leetcode.com/problems/surrounded-regions/
// Similar Problem: NumberOfEnclaves
public class SurroundedRegionsOrReplaceOWithX {

    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >=0 && c < m);
    }

    public static void depthFirstSearch(char[][] matrix, int n, int m, int currRow, int currCol){

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};

        for(int i = 0; i < 4; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && matrix[nextRow][nextCol] == 'R'){
                matrix[nextRow][nextCol] = 'O';
                depthFirstSearch(matrix, n, m, nextRow, nextCol);
            }
        }
    }

    public static void depthFirstSearch_v2(char[][] matrix, int n, int m, int currRow, int currCol, boolean[][] visited){

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};

        for(int i = 0; i < 4; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && !visited[nextRow][nextCol] && matrix[nextRow][nextCol] == 'O'){
                visited[nextRow][nextCol] = true;
                depthFirstSearch_v2(matrix, n, m, nextRow, nextCol, visited);
            }
        }
    }

    public static void replaceOWithX(char[][] matrix) {
        // write your code here

        int n = matrix.length;
        int m = matrix[0].length;

        // mark all the 0's to R - so that few groups of R will later be flipped to 0's for those connected to 4 edges
        // and other remaining R's in the middle of X's to X
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'O'){
                    matrix[i][j] = 'R';
                }
            }
        }

        // try dfs for only sources of R's from these 4 edges so that all connecting group of R's from edges
        // can only be converted to 0 along with sources converted to 0 as well
        for(int i = 0; i < n; i++){
            // edge-1: 1st column
            if(matrix[i][0] == 'R'){
                matrix[i][0] = 'O';
                depthFirstSearch(matrix, n, m, i, 0);
            }

            // edge-2: last column
            if(matrix[i][m - 1] == 'R'){
                matrix[i][m - 1] = 'O';
                depthFirstSearch(matrix, n, m, i, m - 1);
            }
        }

        for(int j = 0; j < m; j++){
            // edge-3: 1st row
            if(matrix[0][j] == 'R'){
                matrix[0][j] = 'O';
                depthFirstSearch(matrix, n, m, 0, j);
            }
            // edge-4: last row
            if(matrix[n - 1][j] == 'R'){
                matrix[n - 1][j] = 'O';
                depthFirstSearch(matrix, n, m, n - 1, j);
            }
        }

        // now the remaining R's in the grid matrix are not connected by any of the 0's on the edge
        // which means they are surrounded by all X's
        // so flip those R's to X
        for(int i = 1; i < n - 1; i++){
            for(int j = 1; j < m - 1; j++){
                if(matrix[i][j] == 'R'){
                    matrix[i][j] = 'X';
                }
            }
        }
    }

    public static void replaceOWithX_v2(char matrix[][]) {
        // write your code here

        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 'O'){
                depthFirstSearch_v2(matrix, n, m, i, 0, visited);
            }
            if(matrix[i][m - 1] == 'O'){
                depthFirstSearch_v2(matrix, n, m, i, m - 1, visited);
            }
        }

        for(int j = 0; j < m; j++){
            if(matrix[0][j] == 'O'){
                depthFirstSearch_v2(matrix, n, m, 0, j, visited);
            }
            if(matrix[n - 1][j] == 'O'){
                depthFirstSearch_v2(matrix, n, m, n - 1, j, visited);
            }
        }

        for(int i = 1; i < n - 1; i++){
            for(int j = 1; j < m - 1; j++){
                if(!visited[i][j] && matrix[i][j] == 'O'){
                    matrix[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args){

        int[] firstLine = InputUtils.readLineWithWhiteSpaceSeparater();
        int n = firstLine[0];
        int m = firstLine[1];

        char[][] matrix = InputUtils.readCharMatrix(n , m);
        replaceOWithX_v2(matrix);
        PrintOutputUtils.print(matrix);
    }
}
