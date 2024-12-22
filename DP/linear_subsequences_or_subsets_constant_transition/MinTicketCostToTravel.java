package linear_subsequences_or_subsets_constant_transition;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
// Note: Booking on some ith day also includes travelling on ith day within valid travel period.
// So, validity of pass starts from booking day itself
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

    public static int minimumCoins_v2(int n, int[] days, int[] cost) {

        // min cost will store the minimum coins required to complete the whole trip.
        int minCost = 0;

        // Declaring two queues.
        Queue<int[]> monthpass = new LinkedList<>();
        Queue<int[]> weekpass = new LinkedList<>();

        // No need to track 1 day pass because the 1-day booking pass applies for that same day travel only

        for (int currTravelDay : days) {

            // when current travel day has crossed the deadline,
            // Removing expired days/passes from both the queues
            // <= check instead of only < because last booking day also counted as the valid travel day
            // Removing expired passes from the queue.

            int minCostOfLastBookingDayForMonthlyPass = -1;

            while (!monthpass.isEmpty()) {
                int lastBookingDayForMonthlyPass = monthpass.peek()[0]; // first probable monthly pass booking day
                int deadLineForlastBookingDayForMonthlyPass = lastBookingDayForMonthlyPass + 30; // deadline of 30 day validity period from that pass booking day
                // 30th day is not covered within 30 day validity period
                if(deadLineForlastBookingDayForMonthlyPass <= currTravelDay){ // if current travel day crossed validity period of last monthly pass booking day
                    monthpass.poll(); // expire that monthly pass by removing its booking day
                } else { // otherwise current travel day is within the validity period of its booking day
                    minCostOfLastBookingDayForMonthlyPass = monthpass.peek()[1]; // consider today's travel day booking on that booking day for within 30 days validity
                    break;
                }
            }

            int minCostOfLastBookingDayForWeeklyPass = -1;
            while (!weekpass.isEmpty()) {
                int lastBookingDayForWeeklyPass = weekpass.peek()[0]; // weekly pass booking day
                int deadLineForlastBookingDayForWeeklyPass = lastBookingDayForWeeklyPass + 7; // deadline of 7 day validity period from that pass booking day
                // 7th day is not covered within 7 day validity period
                if(deadLineForlastBookingDayForWeeklyPass <= currTravelDay){ // if current travel day crossed validity period of last weekly pass booking day
                    weekpass.poll(); // expire that weekly pass by removing its booking day
                } else { // otherwise current travel day is within the validity period of its booking day
                    minCostOfLastBookingDayForWeeklyPass = weekpass.peek()[1]; // consider today's travel day booking on that booking day for within 7 days validity
                    break;
                }
            }

            // Buying 30-day and 7-day pass on current day.
            int currBookingDayForNextTravelDay = currTravelDay;
            int costOf1DayPassForCurrTravelDay = minCost + cost[0];

            // If there exists no previous travel day whose 7-day or 40-day validity can cover today's current travel day

            // If no valid 7-day pass, take the current 7-day cost
            int costOf7DayPassBookedOnCurrTravelDay = minCost + cost[1];
            // when there exists no such prior booking date whose 7-day validity period covers current day
            if(weekpass.isEmpty()) //if(minCostOfLastBookingDayForWeeklyPass == -1)
                minCostOfLastBookingDayForWeeklyPass = costOf7DayPassBookedOnCurrTravelDay; // since travel on current day mandatory hence, consider today's 7-day booking pass

            weekpass.add(new int[]{currBookingDayForNextTravelDay, costOf7DayPassBookedOnCurrTravelDay});

            // If no valid 30-day pass, take the current 30-day cost
            int costOf30DayPassBookedOnCurrTravelDay = minCost + cost[2];
            // when there exists no such prior booking date whose 30-day validity period covers current day
            if(monthpass.isEmpty()) // if(minCostOfLastBookingDayForMonthlyPass == -1)
                minCostOfLastBookingDayForMonthlyPass = costOf30DayPassBookedOnCurrTravelDay; // since travel on current day mandatory hence, consider today's 30-day booking pass
            monthpass.add(new int[]{currBookingDayForNextTravelDay, costOf30DayPassBookedOnCurrTravelDay});

            // Updating minCost as min of today's travel with today's 1 day pass booking day, or (any prior day whose pass booking covers today as within valid day and if not, then today's 7-day and 30-day pass booking day)
            minCost = Math.min(costOf1DayPassForCurrTravelDay, Math.min(minCostOfLastBookingDayForWeeklyPass, minCostOfLastBookingDayForMonthlyPass));
        }

        return minCost;
    }

}
