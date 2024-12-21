package linear_subsequences_or_subsets_constant_transition;

import java.util.Arrays;

/*
* Given a sorted array arr[] consisting of N positive integers such that arr[i] represent the days in which a worker will work and
* an array cost[] of size 3 representing the salary paid to the workers for 1 day, 7 days and 30 days respectively,
* the task is to find the minimum cost required to have a worker for all the given days in arr[].
*
* */
// Link: https://www.naukri.com/code360/problems/minimum-coins_2180776?leftPanelTabValue=PROBLEM
// Link: https://leetcode.com/problems/minimum-cost-for-tickets/description/
// Note: The days array is sorted
// Note: The cost of plans are of constant values and applicable for all booking days
// Note: Booking on some ith day also includes travelling on ith day within valid travel period
// Note: Booking happens prior to travel. So, Booking pointer will be always behind the travel pointer
// Note: Goal is to find for the last travel day, not the last booking day
class MinTicketCostToTravel {


    // T(n): O(3^n) since checks all possibilities and recalculates overlapping subproblems without storing/caching
    // TLE
    public static int minCostTicketsRecBottomUp(int currTravelDay, int[] travelOrNotDays, int[] costs) {

        // since given, at most, a day can be 365th day as year ends with 365 days
        if(currTravelDay > 365) return 0; // base condition

        // if on this currTravelDay, travelling happens or not
        // 0 means travelling not happening on this currTravelDay
        if(travelOrNotDays[currTravelDay] == 0)
            return minCostTicketsRecBottomUp(currTravelDay + 1, travelOrNotDays, costs);

        // if travelling happens on this currTravelDay, then consider three options

        // travelling for 1 day
        int option1 = costs[0] + minCostTicketsRecBottomUp(currTravelDay + 1, travelOrNotDays, costs);

        // travelling for 7 days
        int option2 = costs[1] + minCostTicketsRecBottomUp(currTravelDay + 7, travelOrNotDays, costs);

        // travelling for 30 days
        int option3 = costs[2] + minCostTicketsRecBottomUp(currTravelDay + 30, travelOrNotDays, costs);

        return Math.min(option1, Math.min(option2, option3));
    }

    // T(n): O(3^n) since checks all possibilities and recalculates overlapping subproblems without storing/caching
    // TLE
    public static int minCostTicketsRecTopDown(int currTravelDay, int[] travelOrNotDays, int[] costs) {

        // since given, at min, a day can be 1st day as the day starts with day-1 and not day-0
        if(currTravelDay <= 0) return 0; // base condition

        // if on this currTravelDay, travelling happens or not
        // 0 means travelling not happening on this currTravelDay
        if(travelOrNotDays[currTravelDay] == 0)
            return minCostTicketsRecTopDown(currTravelDay - 1, travelOrNotDays, costs);

        // if travelling happens on this currTravelDay, then consider three options

        // travelling for 1 day
        int option1 = costs[0] + minCostTicketsRecTopDown(currTravelDay - 1, travelOrNotDays, costs);

        // travelling for 7 days
        int option2 = costs[1] + minCostTicketsRecTopDown(currTravelDay - 7, travelOrNotDays, costs);

        // travelling for 30 days
        int option3 = costs[2] + minCostTicketsRecTopDown(currTravelDay - 30, travelOrNotDays, costs);

        return Math.min(option1, Math.min(option2, option3));
    }

    // T(n): O(n = 365) = O(1) since max length of n can be 365 with possible days from 1 to 365th day in a year
    public static int minCostTicketsRecBottomUpMemorization(int currTravelDay, int[] travelOrNotDays, int[] costs, int[] dp) {

        // since given, at most, a day can be 365th day as year ends with 365 days
        if(currTravelDay > 365) return 0; // base condition
        if(dp[currTravelDay] != -1) return dp[currTravelDay];

        // if on this currTravelDay, travelling happens or not
        // 0 means travelling not happening on this currTravelDay
        if(travelOrNotDays[currTravelDay] == 0)
            return dp[currTravelDay] = minCostTicketsRecBottomUpMemorization(currTravelDay + 1, travelOrNotDays, costs, dp);

        // if travelling happens on this currTravelDay, then consider three options

        // travelling for 1 day
        int option1 = costs[0] + minCostTicketsRecBottomUpMemorization(currTravelDay + 1, travelOrNotDays, costs, dp);

        // travelling for 7 days
        int option2 = costs[1] + minCostTicketsRecBottomUpMemorization(currTravelDay + 7, travelOrNotDays, costs, dp);

        // travelling for 30 days
        int option3 = costs[2] + minCostTicketsRecBottomUpMemorization(currTravelDay + 30, travelOrNotDays, costs, dp);

        return dp[currTravelDay] = Math.min(option1, Math.min(option2, option3));
    }

    public static int minCostTicketsRecTopDownMemorization(int currTravelDay, int[] travelOrNotDays, int[] costs, int[] dp) {

        // since given, at min, a day can be 1st day as the day starts with day-1 and not day-0
        if(currTravelDay <= 0) return 0; // base condition
        if(dp[currTravelDay] != -1) return dp[currTravelDay];

        // if on this currTravelDay, travelling happens or not
        // 0 means travelling not happening on this currTravelDay
        if(travelOrNotDays[currTravelDay] == 0)
            return dp[currTravelDay] = minCostTicketsRecTopDownMemorization(currTravelDay - 1, travelOrNotDays, costs, dp);

        // if travelling happens on this currTravelDay, then consider three options

        // travelling for 1 day
        int option1 = costs[0] + minCostTicketsRecTopDownMemorization(currTravelDay - 1, travelOrNotDays, costs, dp);

        // travelling for 7 days
        int option2 = costs[1] + minCostTicketsRecTopDownMemorization(currTravelDay - 7, travelOrNotDays, costs, dp);

        // travelling for 30 days
        int option3 = costs[2] + minCostTicketsRecTopDownMemorization(currTravelDay - 30, travelOrNotDays, costs, dp);

        return dp[currTravelDay] = Math.min(option1, Math.min(option2, option3));
    }


    public static int minCostTickets(int[] days, int[] costs) {
        int[] travelOrNotDays = new int[366];
        for(int day: days){
            travelOrNotDays[day] = 1;
        }
//        return minCostTicketsRecBottomUp(1, travelOrNotDays, costs);
//        return minCostTicketsRecTopDown(days[days.length - 1], travelOrNotDays, costs);

        int[] dp = new int[366];
        Arrays.fill(dp, -1);
//        return minCostTicketsRecBottomUpMemorization(1, travelOrNotDays, costs, dp);
//        return minCostTicketsRecTopDownMemorization(days[days.length - 1], travelOrNotDays, costs, dp);


        // Bottom up Tabular Approach
        int firstTravelDay = days[0];
        int lastTravelDay = days[days.length - 1];

        // base condition
//        for(int i = 0; i < firstTravelDay; i++){
//            dp[i] = 0;
//        }
//        Arrays.fill(dp, 0, firstTravelDay, 0); or Arrays.fill(dp, firstTravelDay - 30, firstTravelDay, 0);

        for(int currTravelDay = firstTravelDay; currTravelDay <= lastTravelDay; currTravelDay++) {

            if(travelOrNotDays[currTravelDay] == 0){
                dp[currTravelDay] = dp[currTravelDay - 1];
            } else {

                // travelling for 1 day
                int option1 = (currTravelDay - 1 >= 0) ? costs[0] + dp[currTravelDay - 1] : costs[0];

                // travelling for 7 days
                int option2 = (currTravelDay - 7 >= 0) ? costs[1] + dp[currTravelDay - 7] : costs[1];

                // travelling for 30 days
                int option3 = (currTravelDay - 30 >= 0) ? costs[2] + dp[currTravelDay - 30] : costs[2];

                dp[currTravelDay] = Math.min(option1, Math.min(option2, option3));
            }
        }

        return dp[lastTravelDay];

    }


}
