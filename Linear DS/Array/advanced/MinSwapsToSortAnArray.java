package advanced;

import java.util.Arrays;
import java.util.Comparator;

/*
* Given an array of distinct elements,
* return the minimum number of swaps required to sort the array.
* */
// This is a graph cycle detection problem disguised as an array question.
public class MinSwapsToSortAnArray {

    public static int minSwaps(int[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int[][] paired = new int[n][2];

        // Pair elements with their indexes.
        //Rev Dict Pair{value:index} = {arr[i], i}
        for (int i = 0; i < n; i++) {
            paired[i][0] = arr[i];
            paired[i][1] = i;
        }

        // Sort by value.
        Arrays.sort(paired, Comparator.comparingInt(a -> a[0]));

        // Count cycles in index mapping.
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || paired[i][1] == i) continue;
            int cycleSize = 0;
            int j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = paired[j][1];
                cycleSize++;
            }
            if (cycleSize > 1) swaps += (cycleSize - 1);
        }
        return swaps;
    }

    public static void main(String[] args) {

    }
}
