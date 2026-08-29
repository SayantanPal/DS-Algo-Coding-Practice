package rowwiseandcolwisesortedmatrix;

// Link: https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1
public class RowWithMaxOnesInRowWiseAscSortedMatrix {

    public int rowWithMax1s(int[][] arr) {
        // code here
        int n = arr.length;
        int m = arr[0].length;

        int i = 0, j = m - 1; // start with top right corner
        int maxRowCnt = -1;
        while(i < n && j >= 0){
            if(arr[i][j] == 1){
                j--;
                maxRowCnt = i;
            }
            else{
                i++; // skip these rows with lesser no of 1s
            }
        }

        return maxRowCnt;
    }
}
