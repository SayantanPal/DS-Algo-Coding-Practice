package basic.twodimensionalarray;

/*
* You are given a matrix A and and an integer B, you have to perform scalar multiplication of matrix A with an integer B.
*
* A = [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]
B = 2
* */

public class ScalerProduct {

    public int[][] solve(int[][] A, int B) {
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++){
                A[i][j] *= B;
            }
        }
        return A;
    }
}
