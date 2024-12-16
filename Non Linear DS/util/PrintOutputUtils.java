package util;

public class PrintOutputUtils {

    public static void print(int[][] grid_matrix){
        int n = grid_matrix.length;
        int m = 0;
        if(n > 0)
            m = grid_matrix[0].length;

        System.out.println();
        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                System.out.print(grid_matrix[i][j]);
                if(j != m - 1)
                    System.out.println(" ");
            }
            System.out.println();
        }
    }

    public static void print(char[][] grid_matrix){
        int n = grid_matrix.length;
        int m = 0;
        if(n > 0)
            m = grid_matrix[0].length;

        System.out.println();
        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                System.out.print(grid_matrix[i][j]);
                if(j != m - 1)
                    System.out.println(" ");
            }
            System.out.println();
        }
    }
}
