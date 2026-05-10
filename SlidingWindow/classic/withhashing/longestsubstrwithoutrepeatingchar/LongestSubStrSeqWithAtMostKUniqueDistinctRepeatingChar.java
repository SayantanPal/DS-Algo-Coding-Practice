package classic.withhashing.longestsubstrwithoutrepeatingchar;

// Link: https://www.naukri.com/code360/problems/distinct-characters_2221410?leftPanelTabValue=PROBLEM
public class LongestSubStrSeqWithAtMostKUniqueDistinctRepeatingChar {

    public int longestAtMostKUniqueDistinctUniqueChars(String s, int k) {
        // code here

        char[] sArr = s.toCharArray();
        int[] sCharFreq = new int[26];
        int left = 0, right = 0;
        int distinctChars = 0;
        int maxLen = -1;

        while(right < sArr.length){
            while(distinctChars > k){
                sCharFreq[sArr[left] - 'a']--;
                if(sCharFreq[sArr[left] - 'a'] == 0){
                    distinctChars--;
                }
                left++;
            }
            if(sCharFreq[sArr[right] - 'a'] == 0){
                distinctChars++;
            }
            sCharFreq[sArr[right] - 'a']++;
            if(distinctChars <= k){
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
        }

        return maxLen;
    }
}
