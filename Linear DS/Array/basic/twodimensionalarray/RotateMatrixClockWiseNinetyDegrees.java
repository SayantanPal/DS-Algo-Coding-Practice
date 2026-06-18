package basic.twodimensionalarray;

/*
* Rotate Matrix
Problem Description

You are given a n x n 2D matrix A representing an image.
Rotate the image by 90 degrees (clockwise).
You need to do this in place.

Note: If you end up using an additional array, you will only receive partial score.
*
*
* Problem Constraints
1 <= n <= 1000

Input Format: First argument is a 2D matrix A of integers
Output Format: Return the 2D rotated matrix.
*
*
* Example Input & Output
Input 1:

 [
    [1, 2],
    [3, 4]
 ]
 *
Output 1:
 [
    [3, 1],
    [4, 2]
 ]
 *
 * Example Explanation

Explanation 1:
 After rotating the matrix by 90 degree:
 1 goes to 2, 2 goes to 4
 4 goes to 3, 3 goes to 1
 *
 *
Input 2:
 [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
 ]
Output 2:
 [
    [7, 4, 1],
    [8, 5, 2],
    [9, 6, 3]
 ]
 *
 * Explanation 2:
 After rotating the matrix by 90 degree:
 1 goes to 3, 3 goes to 9
 2 goes to 6, 6 goes to 8
 9 goes to 7, 7 goes to 1
 8 goes to 4, 4 goes to 2
* */
public class RotateMatrixClockWiseNinetyDegrees {
    public void swap(int i, int j, int[][] A){
        int temp = A[i][j];
        A[i][j] = A[j][i];
        A[j][i] = temp;
    }
    public void swap(int i, int l, int r, int[][] A){
        int temp = A[i][l];
        A[i][l] = A[i][r];
        A[i][r] = temp;
    }
    public void transpose(int[][] A){
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j < A[i].length; j++){
                swap(i, j, A);
            }
        }
    }
    public void rowwiseReverse(int[][] A){
        for(int i = 0; i < A.length; i++){
            int l = 0, r = A[i].length - 1;
            while(l < r){
                swap(i, l, r, A); //A[i][l], A[i][r]
                l++;
                r--;
            }
        }
    }
    public void solve(int[][] A) {
        transpose(A);
        rowwiseReverse(A);
    }
}
