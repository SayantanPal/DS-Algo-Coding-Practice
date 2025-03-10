package two_dimensional_grid_matrix.dfs;

// Link: https://leetcode.com/problems/pacific-atlantic-water-flow/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Goal: Find out the cell in island where rainwater drains to
// BOTH Pacific(top + left) and Atlantic(bottom + right) ocean.
public class WaterFlowToBothPacificAtlantic {
    public boolean isValidTraversal(int row, int col, int n, int m){
        return (row >= 0 && row < n && col >= 0 && col < m);
    }

    public void dfs(int currRow, int currCol, int n, int m, int[][] grid, int[][] visited){

        int[] X = {1, -1, 0 , 0};
        int[] Y = {0, 0, 1, -1};

        for(int coord = 0; coord < 4; coord++){
            int nextRow = currRow + X[coord];
            int nextCol = currCol + Y[coord];
            if(isValidTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] >= grid[currRow][currCol] && visited[nextRow][nextCol] != 1){
                visited[nextRow][nextCol] = 1;
                dfs(nextRow, nextCol, n, m, grid, visited);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> result = new ArrayList<>();

        int n = heights.length;
        int m = 0;
        if(n > 0) m = heights[0].length;

        // Trace if the rainwater can drain to Pacific Side
        // by checking if the cell is reachable from Pacific Edges(Top + Left)
        int visitedPacific[][] = new int[n][m];

        // Trace if the rainwater can drain to Atlantic Side
        // by checking if the cell is reachable from Atlantic Edges(Bottom + Right)
        int visitedAtlantic[][] = new int[n][m];

        for(int i = 0; i < n; i++){

            // Scanning for cells adjacent to pacific(left edge) towards other cells
            visitedPacific[i][0] = 1;
            dfs(i, 0, n, m, heights, visitedPacific);

            // Scanning for cells adjacent to atlantic(right edge) towards other cells
            visitedAtlantic[i][m - 1] = 1;
            dfs(i, m - 1, n, m, heights, visitedAtlantic);
        }

        for(int j = 0; j < m; j++){

            // Scanning for cells adjacent to pacific(top edge) towards other cells
            visitedPacific[0][j] = 1;
            dfs(0, j, n, m, heights, visitedPacific);

            // Scanning for cells adjacent to atlantic(bottom edge) towards other cells
            visitedAtlantic[n - 1][j] = 1;
            dfs(n - 1, j, n, m, heights, visitedAtlantic);
        }

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++){
                // Cells where rainwater can drain to BOTH Pacific and Atlantic Side
                // since these cells are reachable from BOTH Pacific and Atlantic Edges
                if(visitedPacific[i][j] == 1 && visitedAtlantic[i][j] == 1){
                    result.add(Arrays.asList(i, j));
                }
            }

        return result;
    }
}
