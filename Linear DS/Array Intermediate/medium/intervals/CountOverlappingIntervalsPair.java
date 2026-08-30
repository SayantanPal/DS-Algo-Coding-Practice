package medium.intervals;

public class CountOverlappingIntervalsPair {

    // BRUTE FORCE TC = O(N^2) checking every possibility
    // works when N <= 10^3 without TLE
    // Two intervals overlap when each one starts before the other ends.
    // Two intervals pair overlap when neither one ends before the other starts. That requires checking from both sides — A hasn't ended before B starts, AND B hasn't ended before A starts.
    /*
    * Consider two intervals A and B:
    Case 1: A starts before B ends ✓, but B starts after A ends ✗ → NO overlap

    A: [1, 3]
    B: [5, 8]
        A.start(1) <= B.end(8) ✓
        B.start(5) <= A.end(3) ✗  ← B starts after A ends

    Timeline: A[1---3]    B[5------8]
                      gap → no overlap

    Case 2: Both conditions met → OVERLAP

    A: [1, 5]
    B: [3, 8]
        A.start(1) <= B.end(8) ✓
        B.start(3) <= A.end(5) ✓

    Timeline: A[1------5]
                  B[3---------8]
                  ^^^^ overlap
    *
    * */
    // (s, e) -> (s + 0.1, e - 0.1)
    public int countOverlappingIntervals(int[][] intervals){
        int count = 0;
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            int startTimePair1 = intervals[i][0];
            int endTimePair1 = intervals[i][1];
            for (int j = i + 1; j < n; j++) { // previous ith pair possibilities are already handled
                int startTimePair2 = intervals[j][0];
                int endTimePair2 = intervals[j][1];
                if ( startTimePair1 <= endTimePair2 && startTimePair2 <= endTimePair1) {
                    count++;
                }
            }
        }
        return count;
    }
}
