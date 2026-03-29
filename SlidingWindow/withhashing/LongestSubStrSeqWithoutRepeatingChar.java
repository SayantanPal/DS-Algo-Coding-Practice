package withhashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubStrSeqWithoutRepeatingChar {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0, right = 0;
        int maxLen = 0;

        while(right < s.length()){
            char currChar = s.charAt(right);
            if(set.contains(currChar)){
                // Shrink the window one character at a time from the left, removing from HashSet until the duplicate is gone
                // Stale entries in the set needs to be cleared iteratively
                while(set.contains(currChar)){
                    set.remove(s.charAt(left));
                    left++;
                }
            } else {
                set.add(currChar);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            }
        }
        return maxLen;
    }

    // more optimized
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int maxLen = 0;

        while(right < s.length()){
            char c = s.charAt(right);
            // Use a HashMap storing the last index of each character,
            // and jump the left pointer directly past the duplicate
            // Stale entries in the map are harmless — they donot require to be cleared because math.max locks left counter not to shift towards leftwards from current position
            if (map.containsKey(c)) {
                // The rule: Math.max ensures left pointer never moves backward.
                // they always point to an index before left, so Math.max ignores them
                // classic example: "abcdebafghi" -> here when second b is encountered, left ptr moves to indx 2 but, once second a is encountered, it never allows left ptr to toggle to indx 1
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
