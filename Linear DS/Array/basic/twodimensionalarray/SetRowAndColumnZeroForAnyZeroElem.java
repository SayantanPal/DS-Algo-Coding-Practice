package basic.twodimensionalarray;

/*
* You are given a 2D integer matrix A, make all the elements in a row or column zero if the A[i][j] = 0.
* Specifically, make entire ith row and jth column zero.
*
* Example Input
Input 1:
[1,2,3,4]
[5,6,7,0]
[9,2,0,4]
*
* Example Output
Output 1:
[1,2,0,0]
[0,0,0,0]
[0,0,0,0]
*
* */
public class SetRowAndColumnZeroForAnyZeroElem {
    public int[][] solve(int[][] A) {
        int[] rowZeroVisited = new int[A.length];
        int[] colZeroVisited = new int[A.length];

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++){
                if(A[i][j] == 0){
                    rowZeroVisited[i] = 1;
                    colZeroVisited[j] = 1;
                }
            }
        }

        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++){
                if(rowZeroVisited[i] == 1 || colZeroVisited[j] == 1){
                    A[i][j] = 0;
                }
            }
        }
        return A;
    }
}
