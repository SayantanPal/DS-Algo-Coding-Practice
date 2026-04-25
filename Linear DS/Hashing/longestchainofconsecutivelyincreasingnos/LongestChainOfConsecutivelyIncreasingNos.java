package longestchainofconsecutivelyincreasingnos;
import java.util.ArrayList;
import java.util.HashSet;

public class LongestChainOfConsecutivelyIncreasingNos {

    public int longest_chain_of_consecutive_numbers_brute_force(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        int longestChain = 0;
        // Look for chains of consecutive numbers that start from each number.
        for (int num : nums) {
            int currentNum = num;
            int currentChain = 1;
            // Continue to find the next consecutive numbers in the chain.
            while (nums.contains(currentNum + 1)) {
                currentNum += 1;
                currentChain += 1;
            }
            longestChain = Math.max(longestChain, currentChain);
        }
        return longestChain;
    }

    public int longest_chain_of_consecutive_numbers(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        HashSet<Integer> numSet = new HashSet<>(nums);
        int longestChain = 0;
        for (int num : numSet) {
            // If the current number is the smallest number in its chain, search for
            // the length of its chain.
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentChain = 1;
                // Continue to find the next consecutive numbers in the chain.
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentChain += 1;
                }
                longestChain = Math.max(longestChain, currentChain);
            }
        }
        return longestChain;
    }
}
