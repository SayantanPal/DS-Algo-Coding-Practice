package zerostriping;

import java.util.ArrayList;

public class ZeroStriping {

    public static void zeroStriping(ArrayList<ArrayList<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0 || matrix.get(0).size() == 0) {
            return;
        }
        int m = matrix.size();
        int n = matrix.get(0).size();
        // Check if the first row initially contains a zero.
        boolean firstRowHasZero = false;
        for (int c = 0; c < n; c++) {
            if (matrix.get(0).get(c) == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        // Check if the first column initially contains a zero.
        boolean firstColHasZero = false;
        for (int r = 0; r < m; r++) {
            if (matrix.get(r).get(0) == 0) {
                firstColHasZero = true;
                break;
            }
        }
        // Use the first row and column as markers. If an element in the submatrix is zero,
        // mark its corresponding row and column in the first row and column as 0.
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix.get(r).get(c) == 0) {
                    matrix.get(0).set(c, 0);
                    matrix.get(r).set(0, 0);
                }
            }
        }
        // Update the submatrix using the markers in the first row and column.
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix.get(0).get(c) == 0 || matrix.get(r).get(0) == 0) {
                    matrix.get(r).set(c, 0);
                }
            }
        }
        // If the first row had a zero initially, set all elements in the first row to zero.
        if (firstRowHasZero) {
            for (int c = 0; c < n; c++) {
                matrix.get(0).set(c, 0);
            }
        }
        // If the first column had a zero initially, set all elements in the first column to zero.
        if (firstColHasZero) {
            for (int r = 0; r < m; r++) {
                matrix.get(r).set(0, 0);
            }
        }
    }
}
