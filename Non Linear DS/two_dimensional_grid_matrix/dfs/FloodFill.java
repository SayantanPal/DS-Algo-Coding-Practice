package two_dimensional_grid_matrix.dfs;

import java.util.Arrays;
import java.util.Scanner;

// Link: https://www.naukri.com/code360/problems/flood-fill-algorithm_1089687?leftPanelTabValue=PROBLEM
// Link: https://leetcode.com/problems/flood-fill/description/
public class FloodFill {

    public static boolean validTraversal(int r, int c, int n, int m){
        return (r >=0 && r < n && c >=0 && c < m);
    }

    public static void depthFirstSearch(int[][]image, int currRow, int currCol, int n, int m, int oldColor, int newColor){

        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};

        for(int i = 0; i < 4; i++){
            int nextRow = currRow + X[i];
            int nextCol = currCol + Y[i];
            if(validTraversal(nextRow, nextCol, n, m) && image[nextRow][nextCol] == oldColor){
                image[nextRow][nextCol] = newColor;
                depthFirstSearch(image, nextRow, nextCol, n, m, oldColor, newColor);
            }
        }
    }
    public static int[][] floodFill(int[][] image, int x, int y, int newColor)
    {
        // Write your code here
        if(image[x][y] == newColor) return image;
        int oldColor = image[x][y];
        int n = image.length;
        int m = 0;
        if(n > 0) m = image[0].length;
        image[x][y] = newColor;
        depthFirstSearch(image, x, y, n, m, oldColor, newColor);
        return image;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] firstLineInput = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = firstLineInput[0];
        int y = firstLineInput[1];
        int newColor = firstLineInput[2];

        int[] secondLineInput = Arrays.stream(sc.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = secondLineInput[0];
        int m = secondLineInput[1];

        int[][] color = new int[n][m];

        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                color[i][j] = sc.nextInt();
            }
        }

        floodFill(color, x, y, newColor);

        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                System.out.print(color[i][j]+" ");
            }
            System.out.println();
        }
    }
}
