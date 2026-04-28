package geometricsequencetriplets;

import java.util.ArrayList;
import java.util.HashMap;

public class GeometricSequenceTriplets {

    public int geometric_sequence_triplets(ArrayList<Integer> nums, int r) {
        // Use 'HashMap' to ensure the default value of 0 is returned when
        // accessing a key that doesn’t exist in the hash map. This effectively sets
        // the default frequency of all elements to 0.
        HashMap<Long, Long> leftMap = new HashMap<>();
        HashMap<Long, Long> rightMap = new HashMap<>();
        long count = 0;
        // Populate 'rightMap' with the frequency of each element in the array.
        for (int num : nums) {
            long x = (long) num;
            rightMap.put(x, rightMap.getOrDefault(x, 0L) + 1);
        }
        // Search for geometric triplets that have x as the center.
        for (int num : nums) {
            long x = (long) num;
            // Decrement the frequency of x in 'rightMap' since x is now being
            // processed and is no longer to the right.
            rightMap.put(x, rightMap.get(x) - 1);
            if (x % r == 0) {
                count += leftMap.getOrDefault(x / r, 0L) * rightMap.getOrDefault(x * r, 0L);
            }
            // Increment the frequency of x in 'leftMap' since it'll be a part of the
            // left side of the array once we iterate to the next value of `x`.
            leftMap.put(x, leftMap.getOrDefault(x, 0L) + 1);
        }
        return (int) count;
    }
}
