
// Link: https://leetcode.com/problems/maximal-rectangle/description/
public class MaxAreaOfBinaryMatrix {

    // Total rectangles created in possible worst case = N*(N+1)/2 x M*(M+1)/2
    // TC = O(N^2 x M^2)
    public int maximalRectangle_v1(int[][] A) {
        int n = A.length, m = A[0].length;
        int maxArea = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                // from each top left corner point as (i,j), start forming valid rectangle by expanding right and expanding down
                // r controls till how much a cell can be expanded in right towards end column
                // initially assuming r can be extended till last col
                int r = m - 1;

                // for each row index till the end
                for(int k = i; k < n; k++){

                    // for each col index till r where r denotes the farthest col that can be extended with only 1 value in current row
                    // initially assuming r can be extended till last col
                    for(int l = j; l <= r; l++){

                        // only consider the rectangle submatrix with top left corner (i, j) -> bottom right corner (k, l) with only 1'solution

                        // if before reaching initial assumption of r as last column
                        // any cell encounters 0 in any column, then stop further exapnsion and consider till the prev cell col containaing 1 value
                        if(A[k][l] == 0){
                            r = l - 1;
                            break;
                        }
                    }

                    if(r < j){ // if the very first cell itself has 0, then l - 1 ptr ie r ptr comes behind starting point j
                        break; // then no point in calculating the area
                    }

                    maxArea = Math.max(maxArea, (k - i + 1)*(r - j + 1));
                }

            }
        }

        return maxArea;
    }

    // version 2 is under stacks which reduce TC down to O(NxM)
}
