package util;

public class PrintUtils {

    public void print(int[][] grid_matrix){
        int n = grid_matrix.length;
        int m = 0;
        if(n > 0)
            m = grid_matrix[0].length;

        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                System.out.print(grid_matrix[i][j]);
                if(j != m - 1)
                    System.out.println(" ");
            }
            System.out.println();
        }
    }

    public void print(char[][] grid_matrix){
        int n = grid_matrix.length;
        int m = 0;
        if(n > 0)
            m = grid_matrix[0].length;

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
