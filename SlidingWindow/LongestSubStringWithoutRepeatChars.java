import java.util.HashSet;
import java.util.Set;


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
}
