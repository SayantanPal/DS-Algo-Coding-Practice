package sortedmatrixsearchelem;

// Link: https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
public class KthSmallestInMultiplicationTableMatrix {

    public int getMatrixValue(int row, int col){
        return (row + 1)*(col + 1);
    }

    // Row Level Binary Search in Row-wise sorted matrix
    public int findUpperBoundSmallerThanOrEqualToSearchElem(int rowNo, int searchElem, int cols){
        // matrix are row-wise sorted -> apply binary search on each row
        int leftIndex = 0, rightIndex = cols - 1;
        int upperBound = cols;
        while(leftIndex <= rightIndex){
            int midIndex = leftIndex + (rightIndex - leftIndex)/2;
            if(getMatrixValue(rowNo, midIndex) == searchElem){
                leftIndex = midIndex + 1; // move right
            }else if (getMatrixValue(rowNo, midIndex) > searchElem){
                upperBound = midIndex;
                rightIndex = midIndex - 1; // move left
            }else if(getMatrixValue(rowNo, midIndex) < searchElem){
                leftIndex = midIndex + 1; // move right
            }
        }
        return upperBound;
    }

    public int countOfTotalElemSmallerThanOrEquals(int searchElem, int rows, int cols){
        int count = 0;
        // matrix multiplication table is only row-wise sorted
        for(int row = 0; row < rows; row++){
            count += findUpperBoundSmallerThanOrEqualToSearchElem(row, searchElem, cols);
        }
        return count;
    }

    // Find search elem k: (count of elements which are all <= search elem) >= k
    public int findKthNumber(int m, int n, int k) {
        int rows = m;
        int cols = n;
        int minElem = 1;
        int maxElem = rows * cols;

        // sol space range is from minElem to maxElem
        int l = minElem, r = maxElem;
        int answer = -1;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(countOfTotalElemSmallerThanOrEquals(mid, rows, cols) == k){
                answer = mid;
                r = mid - 1; // move left
            }else if(countOfTotalElemSmallerThanOrEquals(mid, rows, cols) < k){
                l = mid + 1; // move right
            }else if(countOfTotalElemSmallerThanOrEquals(mid, rows, cols) > k){
                answer = mid;
                r = mid - 1; // move left
            }
        }
        return answer;
    }
}
