package all_possible_combination_of_subsequences.str_match_notmatch;

// https://www.geeksforgeeks.org/problems/wildcard-pattern-matching/1
public class WildcardMatching {

    // i => text
    // j => pattern
    public int topDownRecWithMem(int i, int j, String txt, String pat, int[][] dp){

        if(i < 0 && j < 0) return 1; // both strings are exhausted
        if(j < 0) return 0; // pattern is exhausted while rem chars left still in main string txt
        if(i < 0) { // to be matched str text is exhausted but if there are rem chars left in pattern except "*"
            for(int k = 0; k <= j; k++){
                if(pat.charAt(k) != '*'){
                    return 0;
                }
            }
            return 1;
        }

        if(dp[i][j] != -1) return dp[i][j];

        if(txt.charAt(i) == pat.charAt(j) || pat.charAt(j) == '?'){
            return dp[i][j] = topDownRecWithMem(i - 1, j - 1, txt, pat, dp);
        } else if(pat.charAt(j) == '*'){
            return dp[i][j] = topDownRecWithMem(i - 1, j, txt, pat, dp) // matches with multiple chars
                    + topDownRecWithMem(i, j - 1, txt, pat, dp); // matches with either 0 char
        } else{
            return dp[i][j] = 0;
        }
    }

    public boolean wildCard(String txt, String pat) {
        // Your code goes here
        int n = txt.length();
        int m = pat.length();
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = -1;
            }
        }

        return topDownRecWithMem(n - 1, m - 1, txt, pat, dp) >= 1 ? true: false ;
    }
}
