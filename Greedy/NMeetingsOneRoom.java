import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class NMeetingsOneRoom {

    public int maxMeetings(int start[], int end[]) {
        // add your code here
        int n = start.length;
        int[][] meetings = new int[n][3]; // eache meeting with start time, end time, index No for arrangement or knowing sequence

        // create the tuple array of each meeting
        for(int i = 0; i < n ; i++){
            meetings[i][0] = start[i];
            meetings[i][1] = end[i];
            meetings[i][2] = i;
        }

        // Greedily lowest end time meetings take priority first to be allocated
        Arrays.sort(meetings, new Comparator<int[]>(){
            // sort by comparing wrt end time
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });

        ArrayList<Integer> meetingSequence = new ArrayList<>();

        // first meeting can always be accomodated in 1 room
        meetingSequence.add(meetings[0][2]);
        int freeTime = meetings[0][1];

        // from second meeting onwards,
        // Greedily allocate room to lowest end time meetings
        // whose start time is after the previous lowest end time meeting
        for(int i = 1; i < n ; i++){
            if(meetings[i][0] > freeTime){
                meetingSequence.add(meetings[i][2]);
                freeTime = meetings[i][1];
            }
        }

        return meetingSequence.size();
    }

}
