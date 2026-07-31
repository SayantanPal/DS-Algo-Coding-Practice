package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Link: https://leetcode.com/problems/merge-intervals/
// Problem: 2-D Array
public class MergeIntervals {

    public int[][] mergeOverlappingIntervals_v1(int[][] intervals) {
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

    public int[][] mergeOverlappingIntervals_v2_1(int[][] intervals) {
        // Arrays.sort(intervals, Comparator.comparingInt( (int[] interval) -> interval[0]));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int currStartTime = -1;
        int currEndTime = -1;

        for(int i = 0; i < intervals.length; i++){
            if(currStartTime == -1){
                currStartTime = intervals[i][0];
                currEndTime = intervals[i][1];
            }else if(currEndTime >= intervals[i][0]){ // if intervals[0][1] >= newInterval[0]
                currEndTime = Math.max(intervals[i][1], currEndTime);
            }else{
                result.add(new int[]{currStartTime, currEndTime});
                currStartTime = intervals[i][0];
                currEndTime = intervals[i][1];
            }
        }

        if(currStartTime != -1)
            result.add(new int[]{currStartTime, currEndTime});
        return result.toArray(new int[result.size()][]);
    }

    public int[][] mergeOverlappingIntervals_v2_2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        int currStartTime = intervals[0][0];
        int currEndTime = intervals[0][1];

        for (int i = 0; i < intervals.length; i++) {
            if (currEndTime >= intervals[i][0]) {
                currEndTime = Math.max(intervals[i][1], currEndTime);
            } else {
                result.add(new int[]{currStartTime, currEndTime});
                currStartTime = intervals[i][0];
                currEndTime = intervals[i][1];
            }
        }

        result.add(new int[]{currStartTime, currEndTime});
        return result.toArray(new int[result.size()][]);
    }

    // overlapping interval
    public ArrayList<ArrayList<Integer>> mergeOverlappingIntervals_v3(ArrayList<ArrayList<Integer>> A) {

        int currStartTime = A.get(0).get(0);
        int currEndTime = A.get(0).get(1);


        int n = A.size();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 1; i < n; i++){
            if(A.get(i).get(0) <= currEndTime){
                currEndTime = Math.max(currEndTime, A.get(i).get(1));
            }else{
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(currStartTime);
                ans.add(currEndTime);
                result.add(ans);
                currStartTime = A.get(i).get(0);
                currEndTime = A.get(i).get(1);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(currStartTime);
        ans.add(currEndTime);
        result.add(ans);
        return result;
    }

    // overlapping interval + insert
    public ArrayList<ArrayList<Integer>> insert(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B) {

        int n = A.size();

        int startTime = B.get(0);
        int endTime = B.get(1);
        ArrayList<Integer> newInterval = new ArrayList<Integer>();
        newInterval.add(startTime);
        newInterval.add(endTime);

        int pos = 0;
        while (pos < n && startTime > A.get(pos).get(0)) { // find the first interval that starts after new interval
            pos++;
        }
        // loop breaks when new interval start time becomes >= start time of any existing interval ie startTime < A.get(pos).get(0)
        A.add(pos, newInterval); // then insert before it

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int currStartTime = A.get(0).get(0);
        int currEndTime = A.get(0).get(1);

        for(int i = 1; i < A.size(); i++){
            if(A.get(i).get(0) <= currEndTime){
                currEndTime = Math.max(currEndTime, A.get(i).get(1));
            }else{
                ArrayList<Integer> ans = new ArrayList<>();
                ans.add(currStartTime);
                ans.add(currEndTime);
                result.add(ans);
                currStartTime = A.get(i).get(0);
                currEndTime = A.get(i).get(1);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(currStartTime);
        ans.add(currEndTime);
        result.add(ans);
        return result;
    }

    // non-overlapping interval + insert
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        // Step 1: Merge existing intervals (in case they overlap)
        List<int[]> merged = new ArrayList<>();
        if (n > 0) {
            int currStart = intervals[0][0];
            int currEnd = intervals[0][1];
            for (int i = 1; i < n; i++) {
                if (intervals[i][0] <= currEnd) {
                    currEnd = Math.max(currEnd, intervals[i][1]);
                } else {
                    merged.add(new int[]{currStart, currEnd});
                    currStart = intervals[i][0];
                    currEnd = intervals[i][1];
                }
            }
            merged.add(new int[]{currStart, currEnd});
        }

        // Step 2: Three-group insert on the merged list
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int size = merged.size();

        while (i < size && merged.get(i)[1] < newInterval[0]) {
            result.add(merged.get(i));
            i++;
        }

        while (i < size && merged.get(i)[0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], merged.get(i)[0]);
            newInterval[1] = Math.max(newInterval[1], merged.get(i)[1]);
            i++;
        }
        result.add(newInterval);

        while (i < size) {
            result.add(merged.get(i));
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    // non-overlapping interval + insert
    public int[][] insert_v2(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> result = new ArrayList<>();
        int i = 0;

        // While 1: merge intervals entirely before newInterval
        int currStart = -1, currEnd = -1;
        while (i < n && intervals[i][1] < newInterval[0]) {
            if (currStart == -1) {
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            } else if (intervals[i][0] <= currEnd) {
                currEnd = Math.max(currEnd, intervals[i][1]);
            } else {
                result.add(new int[]{currStart, currEnd});
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            }
            i++;
        }
        if (currStart != -1) {
            result.add(new int[]{currStart, currEnd});
        }

        // While 2: merge intervals overlapping with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // While 3: merge remaining intervals
        currStart = -1;
        currEnd = -1;
        while (i < n) {
            if (currStart == -1) {
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            } else if (intervals[i][0] <= currEnd) {
                currEnd = Math.max(currEnd, intervals[i][1]);
            } else {
                result.add(new int[]{currStart, currEnd});
                currStart = intervals[i][0];
                currEnd = intervals[i][1];
            }
            i++;
        }
        if (currStart != -1) {
            result.add(new int[]{currStart, currEnd});
        }

        return result.toArray(new int[result.size()][]);
    }



    public int[][] merge(int[][] intervals) {
        // after sorting as per start time, all possible overlapping intervals will be adjacent or grouped together
        // because of strictness constraint: for each interval, [startTime < endTime]
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
            while(j < intervals.length && endTime >= intervals[j][0]){ // find overlapping interval
                endTime = Math.max(endTime, intervals[j][1]); // merged interval end time: longest end time of all overlapping intervals
                j++;
            }
            result.add(new int[]{startTime, endTime});
            i = j; // after merging all overlapping interval, shift pointer window to start with new interval
        }
        return result.toArray(new int[result.size()][]);
    }

}
