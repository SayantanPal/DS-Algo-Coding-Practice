package two_dimensional_grid_matrix.bfs.singlesource;

import java.util.*;

/*
* Roco is an island near Africa which is very prone to forest fire. Forest fire is such that it destroys the complete forest. Not a single tree is left.This island has been cursed by God , and the curse is that whenever a tree catches fire, it passes the fire to all its adjacent tree in all 8 directions,North, South, East, West, North-East, North-West, South-East, and South-West.And it is given that the fire is spreading every minute in the given manner, i.e every tree is passing fire to its adjacent tree.Suppose that the forest layout is as follows where T denotes tree and W denotes water.

Your task is that given the location of the first tree that catches fire, determine how long would it take for the entire forest to be on fire. You may assume that the lay out of the forest is such that the whole forest will catch fire for sure and that there will be at least one tree in the forest

Input Format:

First line contains two integers, M, N, space separated, giving the size of the forest in terms of the number of rows and columns respectively.
The next line contains two integers X,Y, space separated, giving the coordinates of the first tree that catches the fire.
The next M lines, where ith line containing N characters each of which is either T or W, giving the position of the Tree and Water in the  ith row of the forest.
Output Format:

Single integer indicating the number of minutes taken for the entire forest to catch fire

Constrains:

3 ≤ M ≤ 20
3 ≤ N ≤ 20
Sample Input 1:

3 3
W T T
T W W
W T T
Sample Output 1:

5

Explanation:
In the second minute,tree at (1,2) catches fire,in the third minute,the tree at (2,1) catches fire,fourth minute tree at (3,2) catches fire and in the fifth minute the last tree at (3,3) catches fire.
Sample Input 2:
6 6
1 6
W T T T T T
T W W W W W
W T T T T T
W W W W W T
T T T T T T
T W W W W W

Sample Output 2:

16
* */

// Link: https://www.naukri.com/code360/problems/spreading-smoke_1381967?&interviewProblemRedirection=true&attempt_status=COMPLETED&sort_entity=company_count&sort_order=DESC

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

    public static int breadthFirstSearch_ver1(int[][] grid, int n, int m, ArrayList<int[]> currLevel){
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
        return breadthFirstSearch_ver1(grid, n, m, nextlevel);
    }

    public static int breadthFirstSearch_ver2(int[][] grid, int n, int m, ArrayList<int[]> currLevel){
        // if(currLevel.isEmpty()) return 0;

        ArrayList<int[]> nextlevel = new ArrayList<>();

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        for(int[] coordinate: currLevel){
            int currRow = coordinate[0];
            int currCol = coordinate[1];
            for(int i = 0; i < 4; i++){
                int nextRow = currRow + x[i];
                int nextCol = currCol + y[i];
                if(validTraversal(nextRow, nextCol, n, m) && grid[nextRow][nextCol] == 0){
                    grid[nextRow][nextCol] = grid[currRow][currCol] + 1;
                    nextlevel.add(new int[]{nextRow, nextCol});
                }
            }
        }
        if(nextlevel.isEmpty()) {
            int[] coordinate = currLevel.getFirst();
            return grid[coordinate[0]][coordinate[1]] - 1;
        }
        return breadthFirstSearch_ver2(grid, n, m, nextlevel);
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
//        return breadthFirstSearch_ver1(grid, n, m, currLevel);
        return breadthFirstSearch_ver2(grid, n, m, currLevel);
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
