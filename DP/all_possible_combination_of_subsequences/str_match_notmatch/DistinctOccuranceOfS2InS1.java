package all_possible_combination_of_subsequences.str_match_notmatch;

// https://www.geeksforgeeks.org/problems/distinct-occurrences/1
public class DistinctOccuranceOfS2InS1 {
    public static int topDownRecSubstring(String s1, String s2, int i, int j, int m) {
        // Base cases
        if(j < 0) return 1 + topDownRecSubstring(s1, s2, i - 1, m, m);  // Everytime, when the substring match happens add 1 and go ahead to search another occurrence of search string in other part of the main string
        if(i < 0) return 0;  // If s1 is exhausted but s2 is not, no match is possible.

        if(s1.charAt(i) == s2.charAt(j)) {
            // If characters match, move both indices forward (for substring, both must be contiguous)
            return topDownRecSubstring(s1, s2, i - 1, j - 1, m);
        } else {
            return topDownRecSubstring(s1, s2, i - 1, m, m); //  no need to check further for rem char match since it is a substring and not a subsequence. hence we donot move (i,j) -> (i-1,j) to find other discontinuous matching characters in sequence. Rather we restart searching for j again from end/start of search string
        }
    }


    public static int topDownRecSubsequence(String s1, String s2, int i, int j) {
        // Base cases
        if(j < 0) return 1;  // If we've scanned through all of s2, then it's a valid substring match.
        if(i < 0) return 0;  // If s1 is exhausted but s2 is not, no match is possible.

        if(s1.charAt(i) == s2.charAt(j)) {
            // Either take this matching character and move both pointers (matching subsequence)
            // or ignore current character in s1 and keep looking.
            return topDownRecSubsequence(s1, s2, i - 1, j - 1) + topDownRecSubsequence(s1, s2, i - 1, j);
        } else {
            // If characters don't match, simply continue searching for s2 in other parts of s1
            return topDownRecSubsequence(s1, s2, i - 1, j);
        }
    }

    public static void main(String[] args) {
        String s1 = "AEBFCDABC";
        String s2 = "AEBFABCDABC";
        String s3 = "ABC";
        String s4 = "ABCDEBC";
        String s5 = "XXXDEXXDEXXX";
        String s6 = "XXX";
        System.out.println("Distinct subsequences of s3 in s1: " + topDownRecSubsequence(s1, s3, s1.length() - 1, s3.length() - 1));
        System.out.println("Distinct substrings of s3 in s1: " + topDownRecSubstring(s1, s3, s1.length() - 1, s3.length() - 1, s3.length() - 1));
        System.out.println("Distinct subsequences of s3 in s2: " + topDownRecSubsequence(s2, s3, s2.length() - 1, s3.length() - 1));
        System.out.println("Distinct substrings of s3 in s2: " + topDownRecSubstring(s2, s3, s2.length() - 1, s3.length() - 1, s3.length() - 1));
        System.out.println("Distinct substrings of s3 in s4: " + topDownRecSubstring(s4, s3, s4.length() - 1, s3.length() - 1, s3.length() - 1));
        System.out.println("Distinct substrings of s6 in s5: " + topDownRecSubstring(s5, s6, s5.length() - 1, s6.length() - 1, s6.length() - 1));

    }
}
