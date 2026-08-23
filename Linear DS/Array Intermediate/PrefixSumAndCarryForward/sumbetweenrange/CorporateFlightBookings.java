package sumbetweenrange;

// Link: https://leetcode.com/problems/corporate-flight-bookings/
// Link: https://www.naukri.com/code360/problems/corporate-flight-bookings_1466958?leftPanelTabValue=PROBLEM
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] seatsPrefixSum = new int[n];
        for(int i = 0; i < bookings.length; i++){
            int startingFlight = bookings[i][0] - 1;
            int endingFlight = bookings[i][1] - 1;
            int seatCount = bookings[i][2];

            seatsPrefixSum[startingFlight] += seatCount;

            if(endingFlight < n - 1){
                seatsPrefixSum[endingFlight + 1] -= seatCount;
            }
        }
        for(int i = 1; i < n; i++){
            seatsPrefixSum[i] += seatsPrefixSum[i - 1];
        }
        return seatsPrefixSum;
    }
}
