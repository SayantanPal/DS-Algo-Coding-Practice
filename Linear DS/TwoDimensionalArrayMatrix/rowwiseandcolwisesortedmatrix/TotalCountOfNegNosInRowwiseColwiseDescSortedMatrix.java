package rowwiseandcolwisesortedmatrix;

// Link: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
public class TotalCountOfNegNosInRowwiseColwiseDescSortedMatrix {
    public int countNegatives(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int i = n - 1, j = 0; // bottom left corner
        int totalCountOfNeg = 0;
        while(i >=0 && j < m){
            if(grid[i][j] < 0){
                totalCountOfNeg += (m - j); // if first no is neg, as per rowwise descending order sorting, all nos towards right will be negative
                i--;
            }else{
                // keep on moving to right, unless we find a negative number
                j++;
            }
            // if we reach end of one particular row ie j == m - 1 and cannot find such neg number, the above element ie same col prev row element is greater - so cannot be negative since  end of particular row is itself not negative
            // and all elements to the right of the above row are more greater in value - none of them can be negative
        }
        return totalCountOfNeg;
    }
}
