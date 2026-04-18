package patternmatch;

// Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
// T(n) = O(m + n)
public class FindMultiplePatternSearchSubstringUsingRabinKarp {


    /*
    *   - text = "aaa", searchSubStr = "aa" → overlapping matches at index 0 and 1
        - If overlapping allowed: i++ (as above)
        - If non-overlapping only: i += searchSubStr.length() on match to skip past
    *
    * */

    // Using KMP - Longest Prefix Suffix
    public int strStr(String text, String searchSubStr) {
        if(searchSubStr.length() > text.length()) return -1;
        String transformed = searchSubStr + "$" + text;
        int m = text.length();
        int n = searchSubStr.length();
        int[] lps = new int[m+n+1];
        lps[0] = 0;
        for(int i = 1; i < lps.length; i++){
            int x = lps[i - 1];
            while(transformed.charAt(i) != transformed.charAt(x)){
                if(x == 0){
                    x = -1;
                    break;
                }
                x = lps[x - 1];
            }
            lps[i] = x + 1;
            if(lps[i] == n){
                return i - (n - 1) - (1 + n);
            }
        }
        return -1;
    }

    // Alternative - MOST OPTIMAL
    /*
    * For strings: substring().equals() works because Java's String.equals() is heavily optimized (intrinsics, SIMD in modern JVMs). So it's practically fast even though it's still O(m) per comparison.

    For int[] or ArrayList: No such shortcut. You can't do substring or equals on a subarray. Your options:

      1. Sliding window with char-by-char (or element-by-element) comparison — O(n*m) worst case, simple to code
      2. KMP with LPS — O(n+m), worth it when pattern is large or has repeating prefixes
      3. Rabin-Karp (rolling hash) — O(n+m) average, computes a hash over the window and slides it in O(1). This is actually the array-friendly version of what equals() does for strings under the hood.

    * */
    public int strStr_v2(String text, String searchSubStr) {
        int count = 1; // count = which occurrence you want; here we are interested in 1st occurance
        for(int i = 0; i <= text.length() - searchSubStr.length(); i++){
            if(text.substring(i, i + searchSubStr.length()).equals(searchSubStr)){
                count--;
                if (count == 0) return i;
            }                                                                                                                                                                                               }
        return -1;
    }
}
