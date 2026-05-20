package basic;

import java.util.*;

// Link: https://neetcode.io/problems/longest-consecutive-sequence?list=blind75

// The key point: Only start counting from the beginning of a sequence. An element is a sequence start only if num - 1 does NOT exist in the set
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
                // The slowness is from unique.remove(startingNo) — you're mutating the set while iterating over the original nums array,
                // which causes redundant contains checks on already-removed elements.
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
                // The slowness is from unique.remove(startingNo) — you're mutating the set while iterating over the original nums array,
                // which causes redundant contains checks on already-removed elements.
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

    // Most Optimal
    public static int longestConsecutiveMostOptimized(){
        Set<Integer> set = new HashSet<>(Arrays.asList(/* nums boxed */));
        int maxLen = 0;

        // iterate over set instead of the original array
        for (int num : set) {
            if (!set.contains(num - 1)) {  // only start from sequence beginning
                int len = 0;
                int startingNo = num;
                while (set.contains(startingNo)) {
                    len++;
                    startingNo++;
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutiveLittleOptimized(nums));
    }
}
