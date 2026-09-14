import java.util.Arrays;

/*
* Increasing Order words
Problem Description

Given an array of strings A of size N. Your task is to rearrange the words in A such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.
Return the new array of strings sorted as mentioned above.

Problem Constraints
1 <= N <= 105

1 <= |A[i]| <= 10
A[i] consists of lowercase english letters

Input Format
Only argument A is an array of strings.

Output Format
Return an array of strings.

Example Input
Input 1: A = ["hi", "hello", "he"]
Input 2: A = ["could", "bat", "cat", "but"]

Example Output
Output 1: ["hi", "he", "hello"]
Output 2: ["bat", "cat", "but", "could"]

Example Explanation
Explanation 1:
The word "hello" has size 5 so it comes last. The other two have size 2, so the initial ordering is maintained.

* Explanation 2:
The word "could" has size 5 so it comes last. The other three have size 3, so the initial ordering is maintained.
*
* */

public class IncreasingOrderWords {
    public String[] solve(String[] A) {
        Arrays.sort(A, (a, b) -> a.length() - b.length());
        return A;
    }
}
