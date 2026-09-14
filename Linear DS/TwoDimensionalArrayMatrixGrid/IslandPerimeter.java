/*
* You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.


Example 1:
Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4


Constraints:
row == grid.length
col == grid[i].length
1 <= row, col <= 100
grid[i][j] is 0 or 1.
There is exactly one island in grid.
*
* */
// Link: https://leetcode.com/problems/island-perimeter/description/
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int totalPerimeter = 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){ // filter only for island cells

                    // check: for non-border cells
                    if( (j - 1 >= 0) && (j - 1 < m) && grid[i][j - 1] == 0) totalPerimeter++; // if cell has 0 on left
                    if( (j + 1 >= 0) && (j + 1 < m) && grid[i][j + 1] == 0) totalPerimeter++; // if cell has 0 on right
                    if( (i + 1 >= 0) && (i + 1 < n) && grid[i + 1][j] == 0) totalPerimeter++; // if cell has 0 on top
                    if( (i - 1 >= 0) && (i - 1 < n) && grid[i - 1][j] == 0) totalPerimeter++; // if cell has 0 on bottom

                    // check: for border cells
                    if(j == 0) totalPerimeter++; // if cell on 1st col
                    if(j == m - 1) totalPerimeter++; // if cell on last col
                    if(i == 0) totalPerimeter++; // if cell on 1st row
                    if(i == n - 1) totalPerimeter++; // if cell on last row
                }
            }
        }
        return totalPerimeter;
    }
}
