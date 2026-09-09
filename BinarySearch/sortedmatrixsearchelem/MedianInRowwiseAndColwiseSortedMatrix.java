package sortedmatrixsearchelem;

public class MedianInRowwiseAndColwiseSortedMatrix {

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

    public int findTotalElemGtMedian(int[][] matrix, int median){
        int count = 0;
        for(int row = 0; row < matrix.length; row++)
            count += findUpperBoundSmallerThanOrEqualToSearchElem(matrix, row, median);
        return count;
    }

    // Find search elem median: (count of elements which are all <= search elem) >= N*M/2 + 1
    public int findMedian(int[][] A) {

        int minElem = Integer.MAX_VALUE;
        int maxElem = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++){
                minElem = Math.min(minElem, A[i][j]);
                maxElem = Math.max(maxElem, A[i][j]);
            }
        }
        int n = A.length;
        int m = A[0].length;
        int countGreaterThanOrEqualToMedian = n*m/2 + 1; // how many elements median is greater than or equal to - n*m/2 + 1
        int l = minElem, r = maxElem;
        int median = -1;
        while(l <= r){
            int midSearchElem = l + (r - l)/2;
            if(findTotalElemGtMedian(A, midSearchElem) == countGreaterThanOrEqualToMedian){
                median = midSearchElem;
                r = midSearchElem - 1;
            }else if(findTotalElemGtMedian(A, midSearchElem) > countGreaterThanOrEqualToMedian){
                median = midSearchElem;
                r = midSearchElem - 1;
            }else if(findTotalElemGtMedian(A, midSearchElem) < countGreaterThanOrEqualToMedian){
                l = midSearchElem + 1;
            }
        }
        return median;
    }
}
