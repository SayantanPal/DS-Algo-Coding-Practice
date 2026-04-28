package longestchainofconsecutivelyincreasingnos;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Link: https://leetcode.com/problems/longest-consecutive-sequence/
// Link: https://www.naukri.com/code360/problems/longest-consecutive-sequence_759408?leftPanelTabValue=PROBLEM
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

    public int longestChainConsecutivelyIncreasingNos(int[] nums) {
        Set<Integer> lookUp = new HashSet<>();
        for(int num: nums){
            lookUp.add(num);
        }

        int longestLen = 0;
        for(int num: nums){
            if(!lookUp.contains(num - 1)){
                int len = 0;
                int startNoInSeq = num;
                while(lookUp.contains(startNoInSeq)){
                    lookUp.remove(startNoInSeq);
                    len++;
                    startNoInSeq++;
                }
                longestLen = Math.max(longestLen, len);
            }
        }
        return longestLen;
    }

    public int longestChainConsecutivelyIncreasingNos(ArrayList<Integer> nums) {
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        Set<Integer> lookUp = new HashSet<>(nums);
        int longestLen = 0;
        for(int num: nums){
            if(!lookUp.contains(num - 1)){
                int len = 0;
                int startNoInSeq = num;
                while(lookUp.contains(startNoInSeq)){
                    lookUp.remove(startNoInSeq);
                    len++;
                    startNoInSeq++;
                }
                longestLen = Math.max(longestLen, len);
            }
        }
        return longestLen;
    }
}
