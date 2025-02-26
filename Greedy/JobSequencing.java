import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static util.PrintOutputUtils.print;

public class JobSequencing {

    // Pre-requisites assumption: only 1 job can be performed in single day and one job at a time

    public static ArrayList<Integer> jobSequencing(int[] id, int[] deadline, int[] profit) {
        // code here..
        int n = id.length;
        int[][] jobs = new int[n][3];

        for(int i = 0; i < n; i++){
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
            jobs[i][2] = id[i];
        }

        // sort jobs based on profit in descending order
        Arrays.sort(jobs, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return b[1] - a[1];
            }
        });

        // find the maximum deadline available for any job
        int maxDeadline = 0;
        for(int i = 0; i < deadline.length; i++){
            maxDeadline = Math.max(maxDeadline, deadline[i]);
//            if(maxDeadline < deadline[i]){
//                maxDeadline = deadline[i];
//            }
        }

        // maximum no of days for which job will be assigned is the longest available deadline for job
        int[] assignJobIdToDays = new int[maxDeadline];

        // initially mark the full list of days as unassigned with any job
        Arrays.fill(assignJobIdToDays, -1);

        // counter for the total maximum profit earned by completing those jobs
        int maxprofit = 0;

        // counter for the number of job that can be assigned to any day within deadline
        // there might be jobs that cannot be assigned because all days before deadline are already assigned with some job
        int assignedJobCounter = 0;

        for(int i = 0; i < n; i++){
            // push assigning job as farthest date as possible within deadline
            // so first try to assign the job on deadline day
            int endDay = jobs[i][0] - 1; //  0th based indexing for the day index in assignJobIdToDays

            // if the deadline day is unassigned with any job
            if(assignJobIdToDays[endDay] == -1){
                assignJobIdToDays[endDay] = jobs[i][2]; // assign job on that day
                maxprofit += jobs[i][1]; // count maxProfit for the job done
                assignedJobCounter++; // count total no of job assigned days
            } else{ // then keep on tracking the days before starting from deadline which ever day is empty/unassigned
                for(int j = endDay - 1; j >= 0; j--){
                    if(assignJobIdToDays[j] == -1){ // if any day before deadline found unassigned
                        assignJobIdToDays[j] = jobs[i][2]; // assign job on that day
                        maxprofit += jobs[i][1]; // count maxProfit for the job done
                        assignedJobCounter++; // count total no of job assigned days
                        break;
                    }
                }
            }
            print(assignJobIdToDays);
        }

        return new ArrayList<>(Arrays.asList(assignedJobCounter, maxprofit));
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        int[][] jobs = new int[n][4];

        for(int i = 0; i < n; i++){
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
            jobs[i][3] = i + 1;
        }

        Arrays.sort(jobs, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return b[2] - a[2];
            }
        });

        int max_endTime = Integer.MIN_VALUE;
        for(int i = 0; i < n ; i++){
            max_endTime = Math.max(max_endTime, endTime[i]);
        }

        int[] assignedJobs = new int[max_endTime];
        Arrays.fill(assignedJobs, -1);

        int assignedJobCounter = 0;
        int maxProfit = 0;

        for(int i = 0; i < n; i++){
            int startDay = jobs[i][0] - 1;
            int endDay = jobs[i][1] - 1;
            boolean canBeAssigned = true;
            for(int j = startDay; j < endDay; j++){
                if(assignedJobs[j] != -1){
                    canBeAssigned = false;
                    break;
                }
            }
            if(canBeAssigned){
                for(int j = startDay; j <= endDay; j++){
                    assignedJobs[j] = jobs[i][3];
                }
                assignedJobCounter++;
                maxProfit += jobs[i][2];
            }
        }

        print(assignedJobs);

        return maxProfit;
    }

    public static void main(String[] args){
        int[] id = {6, 3, 4, 2, 5, 8, 1, 7};
        int[] deadline = {2, 6, 6, 5, 4, 2, 4, 2};
        int[] profit = {80, 70, 65, 60, 25, 22, 20, 10};
//        int[] id = {1, 2, 3, 4};
//        int[] deadline = {4, 1, 1, 1};
//        int[] profit = {20, 10, 40, 30};

        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit2 = {50,10,40,70};

//        ArrayList<Integer> result = jobSequencing(id, deadline, profit);
//        System.out.println(result);

        int maxProfit = jobScheduling(startTime, endTime, profit2);
        System.out.println(maxProfit);
    }

}
