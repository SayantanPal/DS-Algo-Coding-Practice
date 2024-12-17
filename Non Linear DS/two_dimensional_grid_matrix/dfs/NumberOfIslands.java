package two_dimensional_grid_matrix.dfs;

import java.util.*;

// Link: https://www.naukri.com/code360/problems/find-number-of-islands_630512?&interviewProblemRedirection=true&attempt_status=COMPLETED
// Link: https://leetcode.com/problems/number-of-islands/description/
public class NumberOfIslands {

    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m);
    }

    public static void depthFirstSearch_ver1(ArrayList<ArrayList<Integer>> grid, int currRow, int currCol, int n, int m, boolean[][] visited){


        visited[currRow][currCol] = true;

        int[] X = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] Y = {0, 0, 1, -1, 1, -1, 1, -1};

        for(int i = 0; i < 8; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && !visited[nextRow][nextCol] && grid.get(nextRow).get(nextCol) == 1){
                depthFirstSearch_ver1(grid, nextRow, nextCol, n, m, visited);
            }
        }
    }

    public static void depthFirstSearch_ver2(ArrayList<ArrayList<Integer>> grid, int currRow, int currCol, int n, int m){

        int[] X = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] Y = {0, 0, 1, -1, 1, -1, 1, -1};

        for(int i = 0; i < 8; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && grid.get(nextRow).get(nextCol) == 1){
                grid.get(nextRow).set(nextCol, 0);
                depthFirstSearch_ver2(grid, nextRow, nextCol, n, m);
            }
        }
    }


    public static int numberOfIslands_ver1(ArrayList<ArrayList<Integer>> grid, int n, int m)
    {
        //	  Write your code here.
        boolean[][] visited = new boolean[n][m];
        int countOfIslands = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && grid.get(i).get(j) == 1){
                    depthFirstSearch_ver1(grid, i, j, n, m, visited);
                    countOfIslands++;
                }
            }
        }
        return countOfIslands;
    }

    public static int numberOfIslands_ver2(ArrayList<ArrayList<Integer>> grid, int n, int m)
    {
        //	  Write your code here.
        int countOfIslands = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid.get(i).get(j) == 1){
                    depthFirstSearch_ver2(grid, i, j, n, m);
                    countOfIslands++;
                }
            }
        }
        return countOfIslands;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] firstLineNM = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = firstLineNM[0];
        int m = firstLineNM[1];
        ArrayList<ArrayList<Integer>> grid_matrix = new ArrayList<>();

        for(int i = 0; i < n; i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j < m; j++){
                int col = sc.nextInt();
                row.add(col);
            }
            grid_matrix.add(row);
        }
        System.out.println(numberOfIslands_ver2(grid_matrix, n, m));

    }
}
