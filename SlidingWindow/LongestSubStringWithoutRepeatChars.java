import java.util.HashSet;
import java.util.Set;

/*
* Given an unsorted array of integers,
* find the length of the longest sequence of consecutive numbers.
 * */

// Link - https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubStringWithoutRepeatChars {

    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;

        while(right < s.length()){
            char c = s.charAt(right);
            if(set.contains(c)){
                set.remove(s.charAt(left));
                left++;
            } else{
                maxLen = Math.max(maxLen, right - left + 1);
                set.add(c);
                right++;
            }
        }
        return maxLen;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int streak = 1;
                while (set.contains(curr + 1)) {
                    curr++;
                    streak++;
                }
                longest = Math.max(longest, streak);
            }
        }
        return longest;
    }
}
