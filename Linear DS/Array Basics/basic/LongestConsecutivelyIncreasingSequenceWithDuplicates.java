package basic;

import java.util.*;

public class LongestConsecutivelyIncreasingSequenceWithDuplicates {

    public static int longestConsecutiveSeqWithEachOccuranceTrackingForDuplicates(int[] nums) {
        if(nums.length == 0) return 0;

        Map<Integer, Integer> lookUp = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            lookUp.put(nums[i], lookUp.getOrDefault(nums[i], 0) + 1);
        }

        System.out.println(lookUp);

        int longestConsecutiveSeqLen = 0;
        for(int i: nums){
            if(!lookUp.containsKey(i - 1)){
                int startingNoInSeq = i;
                int consecutiveSeqLen = 0;
                while(lookUp.containsKey(startingNoInSeq) && startingNoInSeq <= 100000){
                    consecutiveSeqLen += lookUp.get(startingNoInSeq);
                    longestConsecutiveSeqLen = Math.max(consecutiveSeqLen, longestConsecutiveSeqLen);
                    lookUp.remove(startingNoInSeq);
                    startingNoInSeq++;
                }
            }

        }
        return longestConsecutiveSeqLen;
    }

    public static int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;

        Set<Integer> lookUp = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            lookUp.add(nums[i]);
        }

        System.out.println(lookUp);

        int longestConsecutiveSeqLen = 0;
        for(int i: nums){
            if(!lookUp.contains(i - 1)){
                int startingNoInSeq = i;
                int consecutiveSeqLen = 1;
                lookUp.remove(startingNoInSeq);
                while(lookUp.contains(startingNoInSeq + 1)){
                    consecutiveSeqLen++;
                    startingNoInSeq++;
                    lookUp.remove(startingNoInSeq);
                }
                longestConsecutiveSeqLen = Math.max(consecutiveSeqLen, longestConsecutiveSeqLen);
            }
        }
        return longestConsecutiveSeqLen;
    }

    public static int longestConsecutiveLittleOptimized(int[] nums) {
        Set<Integer> lookUp = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            lookUp.add(nums[i]);
        }

        System.out.println(lookUp);

        int longestConsecutiveSeqLen = 0;
        for(int i: nums){
            if(!lookUp.contains(i - 1)){
                int startingNoInSeq = i;
                int consecutiveSeqLen = 0;
                while(lookUp.contains(startingNoInSeq)){
                    consecutiveSeqLen++;
                    lookUp.remove(startingNoInSeq);
                    startingNoInSeq++;
                }
                longestConsecutiveSeqLen = Math.max(consecutiveSeqLen, longestConsecutiveSeqLen);
            }
        }
        return longestConsecutiveSeqLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutiveLittleOptimized(nums));
    }
}
