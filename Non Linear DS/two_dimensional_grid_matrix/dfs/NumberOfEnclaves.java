package two_dimensional_grid_matrix.dfs;

// Link: https://leetcode.com/problems/number-of-enclaves/
// Similar Problem: SurroundedRegionsOrReplaceOWithX
public class NumberOfEnclaves {

    public boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }

    public void depthFirstSearch(int[][] grid, int currRow, int currCol, int n, int m, boolean[][] visited){

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};
        visited[currRow][currCol] = true;
        for(int i = 0; i < 4; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]){
                depthFirstSearch(grid, nextRow, nextCol, n, m, visited);
            }
        }
    }

    public void depthFirstSearch_v2(int[][] grid, int currRow, int currCol, int n, int m){

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};
        for(int i = 0; i < 4; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 1){
                grid[nextRow][nextCol] = 0;
                depthFirstSearch_v2(grid, nextRow, nextCol, n, m);
            }
        }
    }

    public int numEnclaves(int[][] grid) {

        int n = grid.length;
        int m = 0;
        if(n > 0) m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){

            if(grid[i][0] == 1){
                // visited[i][0] = true;
                depthFirstSearch(grid, i, 0, n, m, visited);
            }

            if(grid[i][m - 1] == 1){
                // visited[i][m - 1] = true;
                depthFirstSearch(grid, i, m - 1, n, m, visited);
            }
        }

        for(int j = 0; j < m ;j++){
            if(grid[0][j] == 1){
                // visited[0][j] = true;
                depthFirstSearch(grid, 0, j, n, m, visited);
            }

            if(grid[n - 1][j] == 1){
                // visited[n - 1][j] = true;
                depthFirstSearch(grid, n - 1, j, n, m, visited);
            }
        }

        int countOfEnclave = 0;

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    countOfEnclave++;
                }
            }
        }

        return countOfEnclave;
    }

    public int numEnclaves_v2(int[][] grid) {

        int n = grid.length;
        int m = 0;
        if(n > 0) m = grid[0].length;


        for(int i = 0; i < n; i++){

            if(grid[i][0] == 1){
                grid[i][0] = 0;
                depthFirstSearch_v2(grid, i, 0, n, m);
            }

            if(grid[i][m - 1] == 1){
                grid[i][m - 1] = 0;
                depthFirstSearch_v2(grid, i, m - 1, n, m);
            }
        }

        for(int j = 0; j < m ;j++){
            if(grid[0][j] == 1){
                grid[0][j] = 0;
                depthFirstSearch_v2(grid, 0, j, n, m);
            }

            if(grid[n - 1][j] == 1){
                grid[n - 1][j] = 0;
                depthFirstSearch_v2(grid, n - 1, j, n, m);
            }
        }

        int countOfEnclave = 0;

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                countOfEnclave += grid[i][j];
            }
        }

        return countOfEnclave;

    }
}
