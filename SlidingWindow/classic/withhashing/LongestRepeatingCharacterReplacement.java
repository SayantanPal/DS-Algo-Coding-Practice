package classic.withhashing;
// Link: https://leetcode.com/problems/longest-repeating-character-replacement/
public class LongestRepeatingCharacterReplacement {
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
