package classic.withhashing.longestsubstrwithoutrepeatingchar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Link: https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/
public class LongestSubStrSeqWithExactlyKUniqueDistinctRepeatingChar {

    public int longestKUniqueDistinctUniqueChars(String s, int k) {
        // code here

        char[] sArr = s.toCharArray();
        int[] sCharFreq = new int[26];
        int left = 0, right = 0;
        int distinctChars = 0;
        int maxLen = -1;

        while(right < sArr.length){
            while(distinctChars > k){ // shrink window when more than k distinct/unique repeating chars
                sCharFreq[sArr[left] - 'a']--;
                if(sCharFreq[sArr[left] - 'a'] == 0){
                    distinctChars--;
                }
                left++;
            }
            if(sCharFreq[sArr[right] - 'a'] == 0){ // track distinct/unique repeating chars
                distinctChars++;
            }
            sCharFreq[sArr[right] - 'a']++;
            if(distinctChars == k){ // only for exactly k distinct/unique repeating chars
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
        }

        return maxLen;
    }
}
