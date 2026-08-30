package rowwiseandcolwisesortedmatrix;

// Link: https://leetcode.com/problems/search-a-2d-matrix-ii/description/
public class SearchInRowWiseColWiseDescSortedMatrix {
    /*
    * Concept: if rows and cols are both sorted, then bottom right is the maximum element and top left is the minimum element
    * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int i = 0, j = m - 1; // start with top right corner (alternatively can start with bottom left corner also)
        while(i < n && j >= 0){
            if(matrix[i][j] == target) return true;
            else if(target > matrix[i][j]) i++; // move down
            else if(target < matrix[i][j]) j--; // move left
        }
        return false;
    }
}
