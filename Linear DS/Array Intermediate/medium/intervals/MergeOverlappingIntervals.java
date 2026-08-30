package medium.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Link: https://leetcode.com/problems/merge-intervals/
public class MergeOverlappingIntervals {

    public int[][] merge(int[][] intervals) {
        // Arrays.sort(intervals, Comparator.comparingInt( (int[] interval) -> interval[0]));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int currStartTime = intervals[0][0];
        int currEndTime = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            if(currEndTime >= intervals[i][0]){
                currEndTime = Math.max(intervals[i][1], currEndTime);
            }else{
                result.add(new int[]{currStartTime, currEndTime});
                currStartTime = intervals[i][0];
                currEndTime = intervals[i][1];
            }
        }

        result.add(new int[]{currStartTime, currEndTime});
        return result.toArray(new int[result.size()][]);
    }
}
