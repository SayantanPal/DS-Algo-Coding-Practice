import java.util.LinkedHashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/first-unique-character-in-a-string/
public class FirstNonRepeatingCharInString {
    public static void main(String[] args) {
        String str = "swiss";
        System.out.println("First non repeating char in string: " + firstNonRepeatingChar(str));
    }

    private static Character firstNonRepeatingChar(String str) {
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (char c : str.toCharArray())
            count.put(c, count.getOrDefault(c, 0) + 1);
        for (char c : str.toCharArray())
            if (count.get(c) == 1)
                return c;
        return '_';
    }

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
