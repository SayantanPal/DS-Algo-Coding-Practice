package sumbetweenrange;

import java.util.ArrayList;

class SumBetweenRange {
    private ArrayList<Integer> prefixSum;

    public SumBetweenRange(ArrayList<Integer> nums) {
        // Start by adding the first number to the prefix sums array.
        prefixSum = new ArrayList<>();
        prefixSum.add(nums.get(0));
        // For all remaining indexes, add 'nums[i]' to the cumulative sum from the previous index.
        for (int i = 1; i < nums.size(); i++) {
            prefixSum.add(prefixSum.get(i - 1) + nums.get(i));
        }
    }

    public Integer sumRange(Integer i, Integer j) {
        // If i == 0, return the prefix sum directly.
        if (i == 0) {
            return prefixSum.get(j);
        }
        // Otherwise, subtract the prefix sum up to index i - 1.
        return prefixSum.get(j) - prefixSum.get(i - 1);
    }
}