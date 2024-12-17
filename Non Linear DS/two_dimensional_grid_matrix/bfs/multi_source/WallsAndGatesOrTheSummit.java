package two_dimensional_grid_matrix.bfs.multi_source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Link: https://www.naukri.com/code360/problems/walls-and-gates_1092887?interviewProblemRedirection=true&practice_topic%5B%5D=Depth-first%20Search&practice_topic%5B%5D=Breadth-first%20Search&difficulty%5B%5D=Easy&sort_entity=company_count&sort_order=DESC&leftPanelTabValue=PROBLEM
// Link: https://www.naukri.com/code360/problems/the-summit_6227412?interviewProblemRedirection=true&page=2&practice_topic%5B%5D=Depth-first%20Search&practice_topic%5B%5D=Breadth-first%20Search&difficulty%5B%5D=Easy&sort_entity=company_count&sort_order=DESC&leftPanelTabValue=PROBLEM
// Similar Problem: Rotten Oranges
public class WallsAndGatesOrTheSummit {

    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }

    public static void breadthFirstSearch(int[][] grid, int n, int m, ArrayList<int[]> currLevel){

        if(currLevel.isEmpty()) return; // very imp in case where 2 row 1 col array of [[2],[2]]; then return is needed

        ArrayList<int[]> nextLevel = new ArrayList<>();

        int[] X = {1, -1, 0, 0};
        int[] Y = {0, 0, 1, -1};

        for(int[] coordinates: currLevel){
            int currRow = coordinates[0];
            int currCol = coordinates[1];
            int dist = coordinates[2];
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + X[i];
                int nextCol = currCol + Y[i];
                if(validTraversal(nextRow, nextCol, n , m) && grid[nextRow][nextCol] == Integer.MAX_VALUE){ // neither source nor obstacle
                    // grid[nextRow][nextCol] =  Math.min(grid[nextRow][nextCol], dist + 1);
                    // nextLevel.add(new int[]{nextRow, nextCol, grid[nextRow][nextCol]});

                    grid[nextRow][nextCol] = dist + 1;
                    nextLevel.add(new int[]{nextRow, nextCol, dist + 1});
                }
            }
        }
        breadthFirstSearch(grid, n, m, nextLevel);
    }

    public static void summit(int[][] grid) {
        // Write your code here.

        int n = grid.length;
        int m = grid[0].length;
        ArrayList<int[]> currLevel = new ArrayList<>();

        // multisource bfs
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){
                if(grid[i][j] == 0){
                    currLevel.add(new int[]{i, j, 0});
                }
            }
        }

        breadthFirstSearch(grid, n, m, currLevel);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] firstLineNM = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
         int n = firstLineNM[0];
        int m = firstLineNM[1];
        int[][] grid_matrix = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grid_matrix[i][j] = sc.nextInt();
            }
        }
        summit(grid_matrix);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.println(grid_matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

}
