package basic.uniqueduplicatemajority;

// Link: https://leetcode.com/problems/first-unique-character-in-a-string/description/
public class FirstUniqueCharInString {
    public int firstUniqChar(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
