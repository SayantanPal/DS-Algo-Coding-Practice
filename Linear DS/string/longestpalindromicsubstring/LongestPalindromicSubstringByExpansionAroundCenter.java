package longestpalindromicsubstring;

/*
* Problem Description

Given a string A of size N, find and return the longest palindromic substring in A.
Substring of string A is A[i...j] where 0 <= i <= j < len(A)

Palindrome string:
A string which reads the same backwards. More formally, A is palindrome if reverse(A) = A.

Incase of conflict, return the substring which occurs first ( with the least starting index).

Problem Constraints: 1 <= N <= 6000
Input Format: First and only argument is a string A.
Output Format: Return a string denoting the longest palindromic substring of string A.
*
* Example Input
Input 1: A = "aaaabaaa"
Output 1: "aaabaaa"
* Explanation 1:
We can see that longest palindromic substring is of length 7 and the string is "aaabaaa".
*
* Input 2: A = "abba
*Output 2: "abba"
* Explanation 2: We can see that longest palindromic substring is of length 4 and the string is "abba".
*
* */

// Link: https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstringByExpansionAroundCenter {
    public String expandAroundCenter(String A, int l, int r){
        while((l >= 0 && r < A.length()) && (A.charAt(l) == A.charAt(r))){
            l--;
            r++;
        }
        return A.substring(l + 1, r);
    }
    public String longestPalindrome(String A) {
        int maxLen = 0;
        String longestPalindromicSubStr = "";
        for(int c = 0; c < A.length(); c++){
            String oddLengthPalindrome = expandAroundCenter(A, c, c);
            if(oddLengthPalindrome.length() >  maxLen){
                maxLen = oddLengthPalindrome.length();
                longestPalindromicSubStr = oddLengthPalindrome;
            }

            String evenLengthPalindrome = expandAroundCenter(A, c, c + 1);
            if(evenLengthPalindrome.length() > maxLen){
                maxLen = evenLengthPalindrome.length();
                longestPalindromicSubStr = evenLengthPalindrome;
            }
        }
        return longestPalindromicSubStr;
    }
}
