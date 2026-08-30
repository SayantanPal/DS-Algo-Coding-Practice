package sumbetweenrange;

// Link: https://www.geeksforgeeks.org/problems/attend-all-meetings-ii/1
public class CountOfMeetingRoomsRequiredAcrossTimeline {

    public int minMeetingRooms(int[] start, int[] end) {
        // code here

        int MAX_END_TIME = 1000001; // Given as i/p constraints and 0-based indexing

        int[] roomCntAtTimelines = new int[MAX_END_TIME];

        int noOfQueries = start.length;

        for(int i = 0; i < noOfQueries; i++){
            int startingTimestampIndex = start[i];
            int endingTimestampIndex = end[i];

            roomCntAtTimelines[startingTimestampIndex] += 1;

            // Room can be released if next starting time coincides with previous meeting end time
            roomCntAtTimelines[endingTimestampIndex] -= 1;
        }

        int roomsReqd = Integer.MIN_VALUE;
        // max count of rooms at any timestamp instance across full timeline
        // is the min no of meeting rooms required to serve the full timeline
        for(int i = 1; i < MAX_END_TIME; i++){
            roomCntAtTimelines[i] += roomCntAtTimelines[i - 1];
            roomsReqd = Math.max(roomsReqd, roomCntAtTimelines[i]);
        }

        return roomsReqd;
    }
}
