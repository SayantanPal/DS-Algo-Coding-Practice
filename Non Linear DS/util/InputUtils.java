package util;

import java.util.Arrays;
import java.util.Scanner;

public class InputUtils {

    public static int[][] readIntMatrix(int n, int m){
        // read matrix from user input

        Scanner sc = new Scanner(System.in);

        int[][] grid_matrix = new int[n][m];

        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                grid_matrix[i][j] = sc.nextInt();
            }
        }
        return grid_matrix;
    }

    public static char[][] readCharMatrix(int n, int m){
        // read matrix from user input

        Scanner sc = new Scanner(System.in);

        char[][] grid_matrix = new char[n][m];

        for(int i = 0; i < n ;i++){
            for(int j = 0; j < m; j++){
                grid_matrix[i][j] = sc.next().charAt(0);
            }
        }
        return grid_matrix;
    }

    public static int[] readLineWithWhiteSpaceSeparater(){
        // read array from user input with white space separated values

        Scanner sc = new Scanner(System.in);
        return Arrays.stream(sc.nextLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
