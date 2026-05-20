package novice;


import java.util.Scanner;

/*
* Problem Description

You are given an integer N and an N x N matrix.
Print all elements of the matrix in a row-wise wave traversal.
The traversal should look like this:

Start with the first row, traverse from left to right.
For the next row, traverse in the reverse direction of the previous row (so if the previous was left → right, now it’s right → left).
For the following row, switch the direction again.
Continue alternating the direction for every subsequent row until the last row is covered.


Problem Constraints:
1 <= N <= 103
0 <= Mat[i][j] <= 109
*
*Input Format:
First line is an integer N
Next N lines contain N space separated integers representing the matrix Mat

Example Input:
Input 1:
3
4 1 2
7 4 4
3 7 4

* Input 2:
2
1 2
3 4

Output Format
Print all elements of the matrix in a single line, separated by spaces,
following the row-wise wave traversal order.

Note: Ensure there is a space character (' ') at the end of the line.

*
* */
public class WavePattern {

    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] matrix = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                matrix[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < N; i++){
            if(i%2 ==0){
                for(int j = 0; j < N; j++){
                    System.out.print(matrix[i][j] + " ");
                }
            }else{
                for(int j = N - 1; j >=0; j--){
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }
    }
}
