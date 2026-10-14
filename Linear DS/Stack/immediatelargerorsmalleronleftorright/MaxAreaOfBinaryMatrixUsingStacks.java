package immediatelargerorsmalleronleftorright;

import static immediatelargerorsmalleronleftorright.NextImmediateSmallerNumberToTheRight.next_immediate_smaller_number_index_to_the_right;
import static immediatelargerorsmalleronleftorright.PreviousImmediateSmallerNoToTheLeft.previous_immediate_smaller_number_index_to_the_left;

// Link: https://leetcode.com/problems/maximal-rectangle/description/
public class MaxAreaOfBinaryMatrixUsingStacks {

    // version 1 brute-force implementation using TC = O(N^2 x M ^2) is mentioned under 2D matrix grid module

    // If you notice, laying out max_x[i][j] helps you make histograms in every row.
    // Then the problem boils down to finding the maximum area in histograms on each row
    // TC = O(N x M)
    public int maximalRectangle_v2(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        for(int j = 0; j < m; j++){
            for(int i = 1; i < n; i++){
                if(A[i][j] != 0 && A[i - 1][j] != 0){
                    A[i][j] += A[i - 1][j];
                }
            }
        }

        int totalMaxArea = 0;

        for(int i = 0; i < n; i++){
            totalMaxArea = Math.max(totalMaxArea, areaOfHistogramBarsOnEachRow(A[i]));
        }
        return totalMaxArea;
    }

    public int areaOfHistogramBarsOnEachRow(int[] A){
        int[] prevImmediateSmaller = previous_immediate_smaller_number_index_to_the_left(A);
        int[] nextImmediateSmaller = next_immediate_smaller_number_index_to_the_right(A);
        int m = A.length;

        int maxAreaForEachRow = 0;
        for(int j = 0; j < m; j++){
            int l = prevImmediateSmaller[j];
            int r = nextImmediateSmaller[j];
            int width = (r - 1) - (l + 1) + 1;
            int height = A[j];
            maxAreaForEachRow = Math.max(maxAreaForEachRow, width*height);
        }
        return maxAreaForEachRow;
    }
}
