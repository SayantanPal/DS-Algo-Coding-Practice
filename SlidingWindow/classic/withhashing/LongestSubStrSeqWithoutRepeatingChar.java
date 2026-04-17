package classic.withhashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
 * Given an unsorted array of integers,
 * find the length of the longest sequence of consecutive numbers.
 * */
// Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LongestSubStrSeqWithoutRepeatingChar {

    public int lengthOfLongestSubstring_v1(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0, right = 0;
        int maxLen = 0;

        while(right < s.length()){
            char currChar = s.charAt(right);
            // Shrink the window one character at a time from the left, removing from HashSet until the duplicate is gone
            // Stale entries in the set needs to be cleared iteratively
            while(set.contains(currChar)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(currChar);
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }

    // Using HashSet
    // Optimizations - character array instead of string which is faster with indexed access compared to charAt
    public int lengthOfLongestConsecutiveSubstring_v2(String s) {
        Set<Character> lookUp = new HashSet<>();
        int right = 0, left = 0;
        char[] sArr = s.toCharArray();
        int maxLen = 0;
        while(right < sArr.length){
            char charAtRight = sArr[right];
            while(lookUp.contains(charAtRight)){
                char charAtLeft = sArr[left];
                lookUp.remove(charAtLeft);
                left++;
            }
            lookUp.add(charAtRight);
            right++;
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    // more optimized
    // Optimizations - character array instead of string which is faster with indexed access compared to charAt
    // Use-Case: On massive inputs with lots of long-range duplicates (e.g.,"abcdef...xyz" repeated 1M times), HashMap's O(1) jump would win because freq array's left pointer would shrink across thousands of positions.
    /*
    *   Use HashMap jump when:
      - Elements are unbounded (Unicode, integers, objects — not just chars)
      - Duplicates are far apart (long jump distance saves many iterations)
      - Input is massive and window sizes are large
      - Example: finding unique sessions in a stream of user IDs, deduplication in event pipelines
    *
    * */
    public int lengthOfLongestConsecutiveSubstring_v3_hashmap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int maxLen = 0;
        char[] sArr = s.toCharArray();

        while(right < sArr.length){
            char c = sArr[right];
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
            maxLen = Math.max(maxLen, right - left + 1); // jump to index after duplicate char at left
            right++;
        }
        return maxLen;
    }

    // Optimizations - character array instead of string which is faster with indexed access compared to charAt
    // Optimizations - Using frequency array with indexed access much faster than hash set with hash collision
    // Use-case - On LC-sized inputs (string length ~10^4-10^5), freq array wins because the constant factor of HashMap is huge.
    /*
    * Use freq array when:
      - Character set is bounded (ASCII, lowercase only)
      - Window is small relative to input
      - You need raw speed on typical inputs
      - Example: real-time text processing, log parsing, network packet inspection
    * */
    public int lengthOfLongestConsecutiveSubstring_v3_freq_arr(String s) {
        int[] freq = new int[128]; // 128 because of ASCII chars ( since it can includes spaces and other symbols as well)
        int right = 0, left = 0;
        char[] sArr = s.toCharArray();
        int maxLen = 0;
        while(right < sArr.length){
            char charAtRight = sArr[right];
            while(freq[charAtRight] > 0){
                char charAtLeft = sArr[left];
                freq[charAtLeft]--;
                left++; // shrink to index after duplicate char at left
            }
            freq[charAtRight]++;
            right++;
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    // Simple rule: Can the key fit in a fixed-size array? → freq array. Otherwise → HashMap.
}
