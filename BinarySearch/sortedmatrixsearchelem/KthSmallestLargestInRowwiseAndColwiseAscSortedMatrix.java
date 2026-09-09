package sortedmatrixsearchelem;

// Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
public class KthSmallestLargestInRowwiseAndColwiseAscSortedMatrix {

    // Row Level Binary Search in Row-wise sorted matrix
    public int findUpperBoundSmallerThanOrEqualToSearchElem(int[][] matrix, int rowNo, int searchElem){
        // matrix are row-wise sorted -> apply binary search on each row
        int leftIndex = 0, rightIndex = matrix[rowNo].length - 1;
        int upperBound = matrix[rowNo].length;
        while(leftIndex <= rightIndex){
            int midIndex = leftIndex + (rightIndex - leftIndex)/2;
            if(matrix[rowNo][midIndex] == searchElem){
                leftIndex = midIndex + 1; // move right
            }else if (matrix[rowNo][midIndex] > searchElem){
                upperBound = midIndex;
                rightIndex = midIndex - 1; // move left
            }else if(matrix[rowNo][midIndex] < searchElem){
                leftIndex = midIndex + 1; // move right
            }
        }
        return upperBound;
    }

    public int countOfTotalElemSmallerThanOrEquals(int[][] matrix, int searchElem){
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 0;
        for(int row = 0; row < n; row++){

            // below checks due to column-wise sorting advantage
            // which can skip few rows from row level binary search

            // if search element is less than starting elem of any row: if(searchElem < matrix[row][0]) count += 0;

            // if search element is within the range of starting element and ending element of row:
            if(searchElem >= matrix[row][0] && searchElem <= matrix[row][m - 1]){
                count += findUpperBoundSmallerThanOrEqualToSearchElem(matrix, row, searchElem);
            }

            // since due to column wise sorting, instead of calculating via binary search
            // directly if search element is greater than ending elem of any row, then take full row as smaller than search element
            if(searchElem > matrix[row][m - 1]) count += m;
        }
        return count;
    }

    // Find search elem k: (count of elements which are all <= search elem) >= k
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minElem = Integer.MAX_VALUE;
        int maxElem = Integer.MIN_VALUE;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                minElem = Math.min(minElem, matrix[row][col]);
                maxElem = Math.max(maxElem, matrix[row][col]);
            }
        }

        // sol space range is from minElem to maxElem
        int l = minElem, r = maxElem;
        int answer = -1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(countOfTotalElemSmallerThanOrEquals(matrix, mid) == k){
                answer = mid;
                r = mid - 1; // move left
            }else if(countOfTotalElemSmallerThanOrEquals(matrix, mid) < k){
                l = mid + 1; // move right
            }else if(countOfTotalElemSmallerThanOrEquals(matrix, mid) > k){
                answer = mid;
                r = mid - 1; // move left
            }
        }
        return answer;
    }

    // Find search elem k: (count of elements which are all <= search elem) >= n*m - k + 1
    public int kthLargest(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minElem = Integer.MAX_VALUE;
        int maxElem = Integer.MIN_VALUE;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                minElem = Math.min(minElem, matrix[row][col]);
                maxElem = Math.max(maxElem, matrix[row][col]);
            }
        }

        // sol space range is from minElem to maxElem
        int l = minElem, r = maxElem;
        int answer = -1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(countOfTotalElemSmallerThanOrEquals(matrix, mid) == rows*cols - k + 1){
                answer = mid;
                r = mid - 1; // move left
            }else if(countOfTotalElemSmallerThanOrEquals(matrix, mid) < rows*cols - k + 1){
                l = mid + 1; // move right
            }else if(countOfTotalElemSmallerThanOrEquals(matrix, mid) > rows*cols - k + 1){
                answer = mid;
                r = mid - 1; // move left
            }
        }
        return answer;
    }
}
