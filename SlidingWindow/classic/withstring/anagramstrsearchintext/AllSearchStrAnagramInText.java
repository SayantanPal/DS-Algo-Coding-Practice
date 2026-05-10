package classic.withstring.anagramstrsearchintext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Link: LC #438 —https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class AllSearchStrAnagramInText {
    // s => big text string
    // p=> search string
    public List<Integer> findAnagrams(String s, String p){
        int left = 0, right = 0;
        List<Integer> result = new ArrayList<>();
        int[] wFreq = new int[26];
        int[] pFreq = new int[26];

        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }

        char[] sArr = s.toCharArray();

        while(right < sArr.length){
            while(right - left + 1 > p.length()){
                wFreq[sArr[left] - 'a']--;
                left++;
            } // loop ends the moment (right - left + 1) == p.length()
            wFreq[sArr[right] - 'a']++;
            if(Arrays.equals(wFreq, pFreq)){
                result.add(left);
            }
            right++;
        }
        return result;
    }
}
