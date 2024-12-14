package two_dimensional_grid_matrix;

import java.util.*;

public class SpreadingSmoke {

    public static boolean validTraversal(int i, int j, int n, int m){
        return !(i >= n || i < 0 || j >=m || j < 0);
    }

    public static int breadthFirstSearch(int[][] grid, int n, int m, boolean[][] visited, ArrayList<int[]> currLevel){
        // if(currLevel.isEmpty()) return 0;

        ArrayList<int[]> nextlevel = new ArrayList<>();

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        for(int[] coordinate: currLevel){
            int currRow = coordinate[0];
            int currCol = coordinate[1];
            int time = coordinate[2];
            visited[currRow][currCol] = true;
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + x[i];
                int nextCol = currCol + y[i];
                if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]){
                    nextlevel.add(new int[]{nextRow, nextCol, time + 1});
                }
            }
        }
        if(nextlevel.isEmpty()) return currLevel.getFirst()[2];
        return breadthFirstSearch(grid, n, m, visited, nextlevel);
    }

    public static void breadthFirstSearch(int[][] grid, int n, int m, boolean[][] visited, ArrayList<String> currLevel, ArrayList<Integer> time){
        if(currLevel.isEmpty()) return;

        ArrayList<String> nextlevel = new ArrayList<>();

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        boolean flag = false;
        for(String coordinate: currLevel){
            String[] coord = coordinate.split("-");
            int currRow = Integer.parseInt(coord[0]);
            int currCol = Integer.parseInt(coord[1]);
            visited[currRow][currCol] = true;
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + x[i];
                int nextCol = currCol + y[i];
                if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]){
                    flag = true;
                    nextlevel.add(nextRow+"-"+nextCol);
                }
            }
        }
        if(flag) time.set(0, time.getFirst()+1);
        breadthFirstSearch(grid, n, m, visited, nextlevel, time);
    }

    public static int breadthFirstSearch(int[][] grid, int n, int m, ArrayList<int[]> currLevel){
        // if(currLevel.isEmpty()) return 0;

        ArrayList<int[]> nextlevel = new ArrayList<>();

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        for(int[] coordinate: currLevel){
            int currRow = coordinate[0];
            int currCol = coordinate[1];
            int time = coordinate[2];
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + x[i];
                int nextCol = currCol + y[i];
                if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 0){
                    grid[nextRow][nextCol] = 1;
                    nextlevel.add(new int[]{nextRow, nextCol, time + 1});
                }
            }
        }
        if(nextlevel.isEmpty()) return currLevel.getFirst()[2];
        return breadthFirstSearch(grid, n, m, nextlevel);
    }

    public static int minimumSpreadTime(int[][] grid, int x, int y) {
        // Write your code here

        int n = grid.length;
        int m = 0;
        if(n > 0){
            m = grid[0].length;
        }

        x--; // 0-based indexing
        y--; // 0-based indexing

//        boolean[][] visited = new boolean[n][m];
//        ArrayList<String> currLevel = new ArrayList<>(Collections.singletonList(x+"-"+y));
//        ArrayList<Integer> time = new ArrayList<>(Collections.singletonList(0));
//        breadthFirstSearch(grid, n, m, visited, currLevel, time);
//        return time.get(0);

//        boolean[][] visited = new boolean[n][m];
//        ArrayList<int[]> currLevel = new ArrayList<>(Collections.singletonList(new int[]{x, y, 0}));
//        return breadthFirstSearch(grid, n, m, visited, currLevel);

        ArrayList<int[]> currLevel = new ArrayList<>(Collections.singletonList(new int[]{x, y, 0}));
        grid[x][y] = 1;
        return breadthFirstSearch(grid, n, m, currLevel);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] firstLineXY = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] secondLineNM = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = secondLineNM[0];
        int m = secondLineNM[1];
        int[][] grid_matrix = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                grid_matrix[i][j] = sc.nextInt();
            }
        }
        int x = firstLineXY[0];
        int y = firstLineXY[1];
        System.out.println(minimumSpreadTime(grid_matrix, x, y));
    }

}
