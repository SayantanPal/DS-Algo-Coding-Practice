package sumbetweenrange;

// Link: https://leetcode.com/problems/car-pooling/description/
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int MAX_TRIP_INDEX = 1001; // trip index can be [0, 1000] as mentioned
        int[] tripPrefixSum = new int[MAX_TRIP_INDEX];
        int noOfQueriesForTrips = trips.length;
        for(int i = 0; i < noOfQueriesForTrips; i++){
            int noOfPassengers = trips[i][0];
            int startingTripIndex = trips[i][1]; // 0-based indexing
            int endingTripIndex = trips[i][2]; // 0-based indexing

            if(noOfPassengers > capacity) // preliminary first level check
                return false;

            tripPrefixSum[startingTripIndex] += noOfPassengers;
            // when the passengers are dropped off at endingTripIndex, seats are released at that end index
            // drop passenger count at end index to release off and counterbalance
            tripPrefixSum[endingTripIndex] -= noOfPassengers;
        }
        for(int i = 1; i < MAX_TRIP_INDEX; i++){
            tripPrefixSum[i] += tripPrefixSum[i - 1];
            if(tripPrefixSum[i] > capacity) return false;
        }

        return true;
    }
}
