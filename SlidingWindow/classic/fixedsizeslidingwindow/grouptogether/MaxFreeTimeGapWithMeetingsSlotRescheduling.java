package classic.fixedsizeslidingwindow.grouptogether;

// Link: https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/description/
public class MaxFreeTimeGapWithMeetingsSlotRescheduling {

    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        // n -> total no./count of meetings
        int n = startTime.length;

        // in 'n' no of meeting slots, there will always be (n + 1) total count of free gaps
        // start_timeline -> meeting_0 -> ... -> meeting_n - 1 -> end_timeline
        int[] gaps = new int[n + 1];

        // start_timeline -> meeting_0
        gaps[0] = startTime[0] - 0;

        // meeting_0 -> ... -> meeting_n - 1
        for(int i = 1; i < n; i++){
            gaps[i] = startTime[i] - endTime[i - 1];
        }

        // meeting_n - 1 -> end_timeline
        gaps[n] = eventTime - endTime[n - 1];

        // shifting max k meeting slot involves shifting max (k + 1) free gaps
        int windowSize = k + 1;

        int maxFreeGap = 0;
        int freeGap = 0;
        for(int i = 0; i < windowSize; i++){
            freeGap += gaps[i];
        }
        maxFreeGap = Math.max(maxFreeGap, freeGap);

        for(int i = windowSize; i < n + 1; i++){
            freeGap += gaps[i] - gaps[i - windowSize];
            maxFreeGap = Math.max(maxFreeGap, freeGap);
        }

        return maxFreeGap;
    }
}
