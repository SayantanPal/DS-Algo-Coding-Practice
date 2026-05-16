package classic.withstring.anagramstrsearchintext;

import java.util.Arrays;

// Link:  LC #567 — https://leetcode.com/problems/permutation-in-string/
public class ContainsOrNotSearchStrAnagramInText {
    // s1 => search string
    // s2 => big text string
    public boolean checkInclusionUsingVariableSlidingWindow(String s1, String s2) {
        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];
        int subStrLen = s1.length();
        for(int i = 0; i < subStrLen; i++){
            freqS1[s1.charAt(i) - 'a']++;
        }

        int slow = 0, fast = 0;
        while(fast < s2.length()){
            while(fast - slow + 1 > subStrLen){
                freqS2[s2.charAt(slow) - 'a']--;
                slow++;
            }
            freqS2[s2.charAt(fast) - 'a']++;
            if(Arrays.equals(freqS1, freqS2)){
                return true;
            }
            fast++;
        }
        return false;
    }

    // v2 => string converted to character array for raw cpu power
    public boolean checkInclusionUsingVariableSlidingWindow_v2(String s1, String s2) {
        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        int subStrLen = s1Arr.length;

        for(int i = 0; i < subStrLen; i++){
            freqS1[s1Arr[i] - 'a']++;
        }

        int slow = 0, fast = 0;
        while(fast < s2Arr.length){
            while(fast - slow + 1 > subStrLen){
                freqS2[s2Arr[slow] - 'a']--;
                slow++;
            }
            freqS2[s2Arr[fast] - 'a']++;
            if(Arrays.equals(freqS1, freqS2)){
                return true;
            }
            fast++;
        }
        return false;
    }

    // When window size is defined, Fixed Sliding window is Fastest
    public boolean checkInclusionUsingFixedSlidingWindow(String s1, String s2) {
        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        int subStrLen = s1Arr.length;

        for(int i = 0; i < subStrLen; i++){
            freqS1[s1Arr[i] - 'a']++;
        }

        for(int i = 0; i < s2Arr.length; i++){
            freqS2[s2Arr[i] - 'a']++;
            if(i - subStrLen >= 0){
                freqS2[s2Arr[i - subStrLen] - 'a']--;
            }
            if(Arrays.equals(freqS1, freqS2)){
                return true;
            }
        }

        return false;
    }
}
