package sortedmatrixsearchelem;

public class SearchElemInRowwiseInvertedSSortedMatrix {

    // TC = O(N*log(2)M)
    public int searchMatrix(int[][] A, int B) {
        for(int i = 0; i < A.length; i++){
            int l = 0, r = A[0].length - 1;
            while(l <= r){
                int mid = l + (r - l)/2;
                if(A[i][mid] == B){
                    return 1;
                }else if(A[i][mid] > B){
                    r = mid - 1;// move left
                }else if(A[i][mid] < B){
                    l = mid + 1;
                }
            }
        }
        return 0;
    }

    // TC = O(N + log(2)M)
    public int searchMatrix_v2(int[][] A, int B) {
        int n = A.length;
        int m = A[0].length;

        for(int i = 0; i < n; i++){

            // filter out rows
            if(B >= A[i][0] && B <= A[i][m - 1]) {
                int l = 0, r = m - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (A[i][mid] == B) {
                        return 1;
                    } else if (A[i][mid] > B) {
                        r = mid - 1;// move left
                    } else if (A[i][mid] < B) {
                        l = mid + 1;
                    }
                }
            }
        }
        return 0;
    }

    public int searchMatrix_v3(int[][] A, int B) {
        int n = A.length;
        int m = A[0].length;

        for(int i = 0; i < n; i++){

            // filter out rows
            if(B >= A[i][0] && B <= A[i][m - 1]) {
                int l = 0, r = m - 1;
                while (l <= r) {
                    int mid = l + (r - l) / 2;
                    if (A[i][mid] == B) {
                        return 1;
                    } else if (A[i][mid] > B) {
                        r = mid - 1;// move left
                    } else if (A[i][mid] < B) {
                        l = mid + 1;
                    }
                }
            }
        }
        return 0;
    }
}
