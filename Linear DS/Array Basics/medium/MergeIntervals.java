package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Problem: 2-D Array
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // more optimal than Arrays.sort(intervals, Comparator.comparingInt( (int[] interval) -> interval[0]));
        List<int[]> result = new ArrayList<>();
        int i = 0;
        while(i < intervals.length){
            int startTime = intervals[i][0]; // currently start with min start tome
            int endTime = intervals[i][1];
            int j = i + 1;
            // find all eligible/overlapping intervals which can be merged with current interval
            // when current interval end time is greater than or equals the start time of other interval, overlapping happens
            // then merge all overlapping intervals
            // the output of single merged intervals will have end time as the longest end time among all of the overlapping intervals
            while(j < intervals.length && endTime >= intervals[j][0]){ // if overlapping interval
                endTime = Math.max(endTime, intervals[j][1]);
                j++;
            }
            result.add(new int[]{startTime, endTime});
            i = j; // after merging all overlapping interval, shift pointer window to start with new interval
        }
        return result.toArray(new int[result.size()][]);
    }
}
