package patternmatch;

// Link: https://www.naukri.com/code360/problems/longest-prefix-which-is-suffix_3146849?leftPanelTabValue=PROBLEM
public class LongestPrefixSuffix {

    public static String longestPrefixSuffix(String str){
        // Write your code here.
        int n = str.length();
        int[] lps = new int[n];

        lps[0] = 0;
        for(int i = 1; i < n; i++){
            int x = lps[i-1];

            // while character at i is not equal to character at the split referred from LPS[i-1
            // keep on backtrack chaining LPS until you find such match
            while(str.charAt(i)!=str.charAt(x)){// check with probable breaking pattern character
                if(x == 0){
                    x = -1; // in case value is 0, reset the value as -1 so that lps[i] = x + 1; at later stage can rebalance it
                    break;
                }
                x = lps[x-1]; // back track
            }
            lps[i] = x + 1;
        }

        if(lps[n-1] == 0){
            return "-1";
        }


        String lpsStr = "";
        for(int i = n - lps[n-1]; i < n; i++){
            lpsStr+=str.charAt(i);
        }
        return lpsStr;
    }
}
