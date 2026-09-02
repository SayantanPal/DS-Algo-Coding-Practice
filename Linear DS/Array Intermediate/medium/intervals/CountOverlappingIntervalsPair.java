package medium.intervals;

/*
* Q. Count Intersection
* Problem Description
* * -----------------
You are given a 2D array A of length N°3 consisting of N intervals.
where interval from A[i][1] to A[i][2] is defined below
1. if A[i][0] = 1 then both A[i][1] and A[i][2] is included in interval => [A[i][1], A[i][2]]
2. if A[i][0] = 2 then A[i][1] is included and and A[i][2] is excluded in interval => [A[i][1], A[i][2])
3. if A[i][0] = 3 then A[i][1] is excluded and and A[i][2] is included in interval => (A[i][1], A[i][2]]
4. if A[i][0] = 4 then both A[i][1] and A[i][2] is excluded in interval => (A[i][1], A[i][2])
Your task is to find How many pairs of intervals are so their intersection is not null.
*
Problem Constraints:
* -----------------
1 <= |A| <= 1000
1 <= A[i][0] <= 4
1 <= A[i][1] <A[i][2] <= 10^9
* */
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
    // overlap => s before e
    public float[][] uniformIntervals(int[][] intervals) {
        int n = intervals.length;
        float[][] unifiedIntervals = new float[n][3];
        for (int i = 0; i < n; i++) {
            int s = intervals[i][1];
            int e = intervals[i][2];
            int mode = intervals[i][0];
            switch(mode){
                case 1: unifiedIntervals[i][1] = intervals[i][1];
                        unifiedIntervals[i][2] = intervals[i][2];
                        break;

                case 2: unifiedIntervals[i][1] = intervals[i][1];
                        unifiedIntervals[i][2] = intervals[i][2] - 0.1f;
                        break;

                case 3: unifiedIntervals[i][1] = intervals[i][1] + 0.1f;
                        unifiedIntervals[i][2] = intervals[i][2];
                        break;

                case 4: unifiedIntervals[i][1] = intervals[i][1] + 0.1f;
                        unifiedIntervals[i][2] = intervals[i][2] - 0.1f;
                        break;
            }
        }
        return unifiedIntervals;
    }

    public void uniformIntervals2(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            intervals[i][1] *= 2;
            intervals[i][2] *= 2;
            switch (intervals[i][0]) {
                case 2: intervals[i][2] -= 1; break;
                case 3: intervals[i][1] += 1; break;
                case 4: intervals[i][1] += 1; intervals[i][2] -= 1; break;
            }
        }
    }

    public int countOverlappingIntervals(int[][] intervals){
        int count = 0;
        int n = intervals.length;
//        uniformIntervals2(intervals);
        float[][] unifiedIntervals = uniformIntervals(intervals);
        for (int i = 0; i < n; i++) {
            float startTimePair1 = unifiedIntervals[i][1];
            float endTimePair1 = unifiedIntervals[i][2];
            for (int j = i + 1; j < n; j++) { // previous ith pair possibilities are already handled
                float startTimePair2 = unifiedIntervals[j][1];
                float endTimePair2 = unifiedIntervals[j][2];
//                if(startTimePair1 > endTimePair2 || startTimePair2 > endTimePair1) continue;
                if ( startTimePair1 <= endTimePair2 && startTimePair2 <= endTimePair1) {
                    count++;
                }
            }
        }
        return count;
    }
}
