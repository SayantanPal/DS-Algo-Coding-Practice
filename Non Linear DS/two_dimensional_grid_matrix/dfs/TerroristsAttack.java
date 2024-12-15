package two_dimensional_grid_matrix.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Link: https://www.techgig.com/practice/question/terrorists-attack/WXUxMm11QWo0YkRlTlA5QUJtTVBsaENGa1cwWklmdFVSVFJXdFoyWHNXZnBjdURicExIb2xGMjh3T1pZMUdMYQ==/1
public class TerroristsAttack {

    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >= 0 && r < n && c >= 0 && c < m );
    }

    public static int depthFirstSearch(int[][] graph, int currRow, int currCol, int n, int m, ArrayList<Integer> blockCounter){

        // if(visited[i][j]) return blockCounter.get(0);
        blockCounter.set(0, blockCounter.get(0) + 1);

        int[] X = {1,-1,0,0};
        int[] Y = {0,0,1,-1};
        for(int direction = 0; direction < 4; direction++){
            int nextRow = currRow + X[direction];
            int nextCol = currCol + Y[direction];
            if(validTraversal(nextRow, nextCol, n, m) && graph[nextRow][nextCol] == 1){
                graph[nextRow][nextCol] = 0;
                depthFirstSearch(graph, nextRow, nextCol, n, m, blockCounter);
            }
        }
        return blockCounter.get(0);
    }


    public static void findBlocksAndGroups(int n, int m, int[][] graph){
//        int[] result = new int[2];
        int group = 0;
        int maxBlock = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 1){
                    group++;
                    graph[i][j] = 0;
                    ArrayList<Integer> blockCounter = new ArrayList<>(0);
                    blockCounter.add(0);

                    int block = depthFirstSearch(graph, i, j, n, m, blockCounter);
                    maxBlock = Math.max(maxBlock, block);
                }
            }
        }
        System.out.println(group+" "+maxBlock);
    }

    public static void main(String args[] ) throws Exception {

        //Write code here

        Scanner sc = new Scanner(System.in);
        int[] firstInputLine = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();

        int N = firstInputLine[0];
        int M = firstInputLine[1];
        int[][] graph = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                graph[i][j] = sc.nextInt();
            }
        }
        findBlocksAndGroups(N, M, graph);
    }

}
