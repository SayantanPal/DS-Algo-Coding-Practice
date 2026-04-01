import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramSubstringFinder {

    /*
    * Problem Statement:
    * Given two strings s and p, return all the starting indices of p’s anagrams in s.
    * s = "cbaebabacd", p = "abc"
      Output = [0, 6]
    * */

    /*
    Build a frequency map of p.
    Use a sliding window of length p.length().
    Compare frequency maps as we slide.
    * */

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] freqP = new int[26];
        int[] freqS = new int[26];
        for (char c : p.toCharArray()) freqP[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            freqS[s.charAt(i) - 'a']++;
            if (i >= p.length()) {
                freqS[s.charAt(i - p.length()) - 'a']--;
            }
            if (Arrays.equals(freqS, freqP)) result.add(i - p.length() + 1);
        }
        return result;
    }
}
