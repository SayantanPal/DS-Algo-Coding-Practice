package all_possible_combination_of_subsequences.str_match_notmatch;

// https://www.geeksforgeeks.org/problems/edit-distance3702/1
public class EditDistance {

    public int topDownRecWithMem(int i, int j, String s1, String s2, int[][] dp){

        if(i < 0) return j + 1; // when s1 exhausts, then j + 1 rem chars copied from s2 needs insertion to S1
        if(j < 0) return i + 1; // when s2 exhausts, then i + 1 extra chars from s1 needs deletion

        if(dp[i][j] != -1) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = topDownRecWithMem(i - 1, j - 1, s1, s2, dp);
        } else{
            int deleteFromS1 = 1 + topDownRecWithMem(i - 1, j, s1, s2, dp);
            int insertToS1 = 1 + topDownRecWithMem(i, j - 1, s1, s2, dp);
            int replace = 1 + topDownRecWithMem(i - 1, j - 1, s1, s2, dp);
            return dp[i][j] = Math.min(Math.min(deleteFromS1, insertToS1), replace);
        }

    }

    public int editDistance(String s1, String s2) {
        // Code here
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i][j] = -1;
            }
        }

        return topDownRecWithMem(n - 1, m - 1, s1, s2, dp);
    }
}
