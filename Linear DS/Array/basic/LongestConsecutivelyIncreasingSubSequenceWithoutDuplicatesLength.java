package basic;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutivelyIncreasingSubSequenceWithoutDuplicatesLength {

    public static int longestConsecutiveIncreasingWithSorting(int[] nums) {

        int longestConsecutiveSequence = 0;
        if(nums.length == 0) return longestConsecutiveSequence;

        Arrays.sort(nums);

        int currentConsecutiveSequence = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                if(nums[i] - nums[i - 1] == 1){
                    currentConsecutiveSequence++;
                } else {
                    longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currentConsecutiveSequence);
                    currentConsecutiveSequence = 1;
                }
            }
        }

        // in case the list ends with an increasing sequence
        longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currentConsecutiveSequence);

        return longestConsecutiveSequence;
    }

    public static int longestConsecutiveIncreasingWithSorting_ver2(int[] nums) {

        if(nums.length == 0) return 0;


        Arrays.sort(nums);

        int longestConsecutiveSequence = 1;
        int currentConsecutiveSequence = 1;

        for(int i = 1; i < nums.length; i++){
            // if number is same as that of prev num, ingore counting length
            if(nums[i] != nums[i - 1]){ // if the number follows increasing order
                if(nums[i] - nums[i - 1] == 1){
                    currentConsecutiveSequence++;
                    longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currentConsecutiveSequence);
                } else {
                    currentConsecutiveSequence = 1;
                }
            }
        }

        return longestConsecutiveSequence;
    }

    public static int longestConsecutiveIncreasingWithoutSorting(int[] nums) {


         HashSet<Integer> set = new HashSet<>();
        // Integer[] numList = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        // set.addAll(Arrays.asList(numList));

        // Set<Integer> set = Arrays.stream(nums) // Create an IntStream
        //                              .boxed()          // Box each int to Integer
        //                              .collect(HashSet::new, HashSet::add, HashSet::addAll); // Collect into a HashSet

         for(int num : nums){
             set.add(num);
         }

         int longestConsecutiveSequence = 0;
         for(Integer num: set){
             // if it crosses half length of the set, then it must be the longest increasing subsequence without duplicates
             if(longestConsecutiveSequence > set.size() / 2) return longestConsecutiveSequence;
             if(!set.contains(num - 1)){// this is the starting element of a sequence
                 int currentConsecutiveSequence = 1;
                 int currNum = num;
                 while(set.contains(currNum + 1)){
                     currentConsecutiveSequence++;
                     currNum++;
                 }
                 longestConsecutiveSequence = Math.max(longestConsecutiveSequence, currentConsecutiveSequence);
             }
         }
        return longestConsecutiveSequence;
    }

    public static void main(String[] args) {
        LongestConsecutivelyIncreasingSubSequenceWithoutDuplicatesLength l = new LongestConsecutivelyIncreasingSubSequenceWithoutDuplicatesLength();
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        nums = new int[]{6, 7, 8, 3, 4, 5, 9, 10};
        System.out.println(longestConsecutiveIncreasingWithSorting(nums));
        System.out.println(longestConsecutiveIncreasingWithSorting_ver2(nums));
        System.out.println(longestConsecutiveIncreasingWithoutSorting(nums));
    }
}
