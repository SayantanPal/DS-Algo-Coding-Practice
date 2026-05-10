package classic.withstring.anagramstrsearchintext;

// Link:  LC #76 — https://leetcode.com/problems/minimum-window-substring/description/
public class MinWindowSubstring {

    public String minWindow(String s, String t) {
        int[] subStrFreq = new int[128];
        int subStrUniqueChars = 0;
        char[] subStrArr = t.toCharArray();
        for(char c: subStrArr){
            if(subStrFreq[c] == 0){
                subStrUniqueChars++;
            }
            subStrFreq[c]++;
        }

        char[] textStrArr = s.toCharArray();
        int[] textStrFreq = new int[128];

        int minLen = Integer.MAX_VALUE;
        int minLenStart = 0;
        int minLenEnd = -1;

        int left = 0, right = 0;

        //Forget about the "extra" characters in the window — they don't matter for the formed counter. You only care about characters that exist in t
        int uniqueCharOfSubStrFormation = 0; // how many unique characters in big text have met their required frequency of small sub str in the current window. Window complete when formed == required

        while(right < textStrArr.length){
            textStrFreq[textStrArr[right]]++;
            // formed only increments once per character, exactly when it becomes satisfied. Extra occurrences after that don't trigger it.
            if(subStrFreq[textStrArr[right]] > 0 // character is relevant ie contained in subtring
                    && subStrFreq[textStrArr[right]] == textStrFreq[textStrArr[right]]){ // if the freq count matches
                uniqueCharOfSubStrFormation++;
            }

            // formed-- only when it drops from need(or subStrUniqueChars) to need(or subStrUniqueChars) - 1
            while(uniqueCharOfSubStrFormation == subStrUniqueChars){ // continue shrinking till formed drops from required to 1 less.
                if(right - left + 1 < minLen){ // moment when formed == required
                    minLen = right - left + 1;
                    minLenStart = left;
                    minLenEnd = right;
                }
                if(subStrFreq[textStrArr[left]] > 0 // character is relevant ie contained in subtring
                        && subStrFreq[textStrArr[left]] == textStrFreq[textStrArr[left]]){ // if the freq count matches
                    uniqueCharOfSubStrFormation--;
                }
                textStrFreq[textStrArr[left]]--;
                left++;
            }
            right++;
        }
        return s.substring(minLenStart, minLenEnd + 1);
    }
}
