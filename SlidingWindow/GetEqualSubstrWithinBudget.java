
// Link: https://leetcode.com/problems/get-equal-substrings-within-budget/
public class GetEqualSubstrWithinBudget {

    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int left = 0, right = 0;
        int maxLen = Integer.MIN_VALUE;
        int conversationCost = 0;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        while(right < n){
            conversationCost += Math.abs(sArr[right] - tArr[right]);
            while(conversationCost > maxCost){
                conversationCost-= Math.abs(sArr[left] - tArr[left]);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
