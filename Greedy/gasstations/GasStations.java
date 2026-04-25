package gasstations;
import java.util.ArrayList;

// Link: https://leetcode.com/problems/gas-station/description/
public class GasStations {

    public static int gas_stations(ArrayList<Integer> gas, ArrayList<Integer> cost) {
        // If the total gas is less than the total cost, completing the circuit is
        // impossible.
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.size(); i++) {
            totalGas += gas.get(i);
            totalCost += cost.get(i);
        }
        if (totalGas < totalCost) {
            return -1;
        }
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.size(); i++) {
            tank += gas.get(i) - cost.get(i);
            // If our tank has negative gas, we cannot continue through the circuit from
            // the current start point, nor from any station before or including the
            // current station 'i'.
            if (tank < 0) {
                // Set the next station as the new start point and reset the tank.
                start = i + 1;
                tank = 0;
            }
        }
        return start;
    }
}
