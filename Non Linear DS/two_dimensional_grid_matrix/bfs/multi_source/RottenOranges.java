package two_dimensional_grid_matrix.bfs.multi_source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Link: https://www.naukri.com/code360/problems/rotting-oranges_701655?interviewProblemRedirection=true&practice_topic%5B%5D=Breadth-first%20Search&practice_topic%5B%5D=Depth-first%20Search&sort_entity=company_count&sort_order=DESC&leftPanelTabValue=SUBMISSION
// Similar Problem: Walls And Gates or The Summit problem
public class RottenOranges {
    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }
    public static int breadthFirstSearch(int[][] grid, int n, int m, ArrayList<int[]> currLevel){

        if(currLevel.isEmpty()) return 0;

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};

        ArrayList<int[]> nextLevel = new ArrayList<>();

        for(int[] coordinate: currLevel){
            int currRow = coordinate[0];
            int currCol = coordinate[1];
            int time = coordinate[2];
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + X[i];
                int nextCol = currCol + Y[i];
                if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 1){
                    grid[nextRow][nextCol] = 2;
                    nextLevel.add(new int[]{nextRow, nextCol, time + 1});
                }
            }
        }
        if(nextLevel.isEmpty()) return currLevel.get(0)[2];
        return breadthFirstSearch(grid, n, m, nextLevel);
    }

    public static int minTimeToRot(int[][] grid, int n, int m) {
        // Write your code here.
        ArrayList<int[]> currLevel = new ArrayList<>();

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    currLevel.add(new int[]{i, j, 0});
                }
            }
        }
        int t = breadthFirstSearch(grid, n, m, currLevel);
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return t;
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

        System.out.println(minTimeToRot(grid_matrix, n, m));
    }
}
