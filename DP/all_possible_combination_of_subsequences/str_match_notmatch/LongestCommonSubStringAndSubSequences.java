package all_possible_combination_of_subsequences.str_match_notmatch;

public class LongestCommonSubStringAndSubSequences {

    static int ans = 0;

    public static int topDownRecSubString(String s1, String s2, int i, int j){
        if(i < 0 || j < 0) return 0;

        int match = 0;

        if(s1.charAt(i) == s2.charAt(j)){
            match = 1 + topDownRecSubString(s1, s2, i-1, j-1);
            ans = Math.max(ans, match);
        }

        topDownRecSubString(s1, s2, i - 1, j);
        topDownRecSubString(s1, s2, i, j - 1);

        return match;
    }

//    static int topDownRecSubString2(String str1, String str2, int i, int j, int count){
//        if (i < 0 || j < 0) return count;
//
//        if (str1.charAt(i) == str2.charAt(j)){
//            count = topDownRecSubString2(str1, str2, i - 1, j - 1, count + 1);
//        }
//
//        count = Math.max(count, Math.max(topDownRecSubString2(str1, str2, i - 1, j, 0), topDownRecSubString2(str1, str2, i, j - 1, 0)));
//        return count;
//    }

    public static int topDownRecSubSequence(String s1, String s2, int i, int j){
        if(i < 0 || j < 0) return 0;


        if(s1.charAt(i) == s2.charAt(j)){
            return 1 + topDownRecSubSequence(s1, s2, i - 1, j - 1);
        } else {
            return 0 + Math.max(topDownRecSubSequence(s1, s2, i - 1, j), topDownRecSubSequence(s1, s2, i, j - 1));
        }
    }

    public int topDownRecSubSequence2(String str1, String str2, int i, int j, int count){
        if (i < 0 || j < 0) return count;

        if (str1.charAt(i) == str2.charAt(j))
            return topDownRecSubSequence2(str1, str2, i - 1, j - 1, count + 1);
        else
            return Math.max(topDownRecSubSequence2(str1, str2, i - 1, j, count), topDownRecSubSequence2(str1, str2, i, j - 1, count));
    }

    public static int longestCommonSubSequence(String str1, String str2){
        int n = str1.length();
        int m = str2.length();
        return topDownRecSubSequence(str1, str2, n - 1, m - 1);
    }

    public static int longestCommonSubString(String str1, String str2){
        int n = str1.length();
        int m = str2.length();

        topDownRecSubString(str1, str2, n - 1, m - 1);
        return ans;

//        return topDownRecSubString2(str1, str2, n - 1, m - 1, 0);
    }
}
