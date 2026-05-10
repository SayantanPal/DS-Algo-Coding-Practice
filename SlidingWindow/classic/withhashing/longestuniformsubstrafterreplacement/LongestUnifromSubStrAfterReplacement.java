package classic.withhashing.longestuniformsubstrafterreplacement;
import java.util.HashMap;

// Link: https://leetcode.com/problems/longest-repeating-character-replacement/
public class LongestUnifromSubStrAfterReplacement {
    public int longest_uniform_substring_after_replacements(String s, int k) {
        HashMap<Character, Integer> freqs = new HashMap<>();
        int highestFreq = 0;
        int maxLen = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            // Update the frequency of the character at the right pointer and the highest
            // frequency for the current window.
            char ch = s.charAt(right);
            freqs.put(ch, freqs.getOrDefault(ch, 0) + 1);
            highestFreq = Math.max(highestFreq, freqs.get(ch));
            // Calculate replacements needed for the current window.
            int numCharsToReplace = (right - left + 1) - highestFreq;
            // Slide the window if the number of replacements needed exceeds 'k'.
            // The right pointer always gets advanced, so we just need to advance 'left'.
            if (numCharsToReplace > k) {
                char leftChar = s.charAt(left);
                freqs.put(leftChar, freqs.get(leftChar) - 1);
                left++;
            }
            // Since the length of the current window increases or stays the same, assign
            // the length of the current window to 'max_len'.
            maxLen = right - left + 1;
            // Expand the window.
            right++;
        }
        return maxLen;
    }

    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int[] frequency = new int[26];
        char[] sArray = s.toCharArray();
        int windowLen = 0, maxFreq = 0, longestLen = 0;
        while(right < sArray.length){
            frequency[sArray[right] - 'A']++;
            maxFreq = Math.max(maxFreq, frequency[sArray[right] - 'A']);
            windowLen = right - left + 1;
            while(windowLen - maxFreq > k){
                frequency[sArray[left] - 'A']--;
                windowLen--;
                left++;
            }
            longestLen = Math.max(longestLen, windowLen);
            right++;
        }
        return longestLen;
    }
}
