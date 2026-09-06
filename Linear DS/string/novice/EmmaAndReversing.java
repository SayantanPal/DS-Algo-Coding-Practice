package novice;

/*
* Emma and Reversing
Problem Description

Emma is working on a software that manipulates strings. She wants to write a function that reverses the segment of a string A starting from index 0 and ending at the index of the first occurrence of a given character. If the given character is not found, the string should remain unchanged.
For example, if the string is "hello world" and the given character is "o", then the function should reverse the segment that starts at 0 and ends at 4 (inclusive). The resulting string will be "olleh world".
Write a function to help Emma accomplish this task.

Problem Constraints
1 <= A.length <= 105

A[i] contains lowercase English alphabets.


Input Format
First argument is a string containing only lowercase alphabets. Second argument is a string of size 1 containing the character.


Output Format
Return a string denoting the string after performing the reverse operation.


Example Input
Input 1:
A = "hello world"
B = "o"


Input 2:
A = "coding is love"
B = "l"


Example Output
Output 1: "olleh world"
Output 2: "l si gniodcove"


Example Explanation
Explanation 1: - The first occurence of 'o' is on index = 4(0 based indexing) so reversing the string from index 0 to 4 = "olleh world".
Explanation 2: - The first occurence of 'l' is on index = 10(0 based indexing) so reversing the string from index 0 to 10 = "l si gniodcove".
*
* */

public class EmmaAndReversing {
    public String solve(String A, String B) {
        char targetChar = B.charAt(0);
        int targetIndex = -1;
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i) == targetChar){
                targetIndex = i;
                break;
            }
        }
        if(targetChar == -1) return A;
        StringBuilder result = new StringBuilder();
        for(int i = targetIndex; i >=0 ; i--){
            result.append(A.charAt(i));
        }
        for(int i = targetIndex + 1; i < A.length(); i++){
            result.append(A.charAt(i));
        }
        return result.toString();
    }
}
