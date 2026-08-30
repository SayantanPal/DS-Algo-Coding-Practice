package sumbetweenrange;

// Link: https://leetcode.com/problems/corporate-flight-bookings/
// Link: https://www.naukri.com/code360/problems/corporate-flight-bookings_1466958?leftPanelTabValue=PROBLEM

// Each Flight has same seat capacity and the given input does not require checking against any seat capacity limit because inputs are trusted well within seat capacity constraints
// Bookings array contains 'q' no of queries which asks for adding seats for each flight from range bookings[i] to bookings[j]
// The flight index mentioned in each bookings[i] queries are well within ranges [1, n]
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] answerPrefixSum = new int[n]; // startingFlightIndex -> endingFlightIndex well within n
        int noOfQueries = bookings.length;
        for(int i = 0; i < noOfQueries; i++){
            // Think each flight like a container that can hold ceetain seatCnt without bothering about overexceeding seat count since no max flight seat count capacity constraint mentioned for the flights
            int startingFlightIndex = bookings[i][0] - 1; // Given inputs are 1-based indexing. so map into 0-based indexing
            int endingFlightIndex = bookings[i][1] - 1; // Given inputs are 1-based indexing. so map into 0-based indexing
            int seatCntForAllFlightsInRange = bookings[i][2];

            answerPrefixSum[startingFlightIndex] += seatCntForAllFlightsInRange;
            // below except when endingFlightIndex is the last flight
            // because seats cnt hold till including endingFlightIndex
            // and there is no flight index after endingFlightIndex to drop seat count and counterbalance
            if(endingFlightIndex + 1 < n)
                answerPrefixSum[endingFlightIndex + 1] -= seatCntForAllFlightsInRange;
        }

        for(int i = 1; i < n; i++){
            answerPrefixSum[i] += answerPrefixSum[i - 1];
        }

        return answerPrefixSum;
    }
}
