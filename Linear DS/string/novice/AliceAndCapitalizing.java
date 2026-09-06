package novice;
/*
* Alice and Capitalizing
Problem Description

Alice is developing a function that processes strings for a text analysis tool. She wants to write a function that capitalizes the segment of a String A starting from the first occurrence of a given character B to the end of the string. If the given character is not found, the string should remain unchanged.
For example, if the string is "good morning" and the given character is "m", then the function should capitalize the segment that starts from the first occurrence of "m" to the end of the string. The resulting string will be "good MORNING".
Complete the given function to help Alice accomplish this task.


Problem Constraints
1 <= A.length <= 105
A[i] contains lowercase English Alphabets and spaces.
B.length = 1
B contains a lowercase English Alphabet


Input Format
First argument is a string containing only lowercase alphabets and spaces.
Second argument is a string of size 1 containing the character.

Output Format
Return a string denoting the string after performing the given operation.


Example Input
Input 1:

A = "hello world"
B = "o"
Input 2:

A = "coding is love"
B = "l"
Input 3:

A = "scaler academy"
B = "z"

Example Output
Output 1: "hellO WORLD"
Output 2: "coding is LOVE"
Output 3: "scaler academy"


Example Explanation
Explanation 1:
The first occurence of 'o' is on index = 4(0 based indexing).
So Capitalizing the string from index 4 to 10(end) = "hellO WORLD".
Explanation 2:
The first occurence of 'l' is on index = 10(0 based indexing).
So Capitalizing the string from index 10 to 13(end) = "coding is LOVE".
Explanation 3:
The Given Character 'z' is not found, So returning the same string.
*
*
* */
public class AliceAndCapitalizing {
    public String capitalizeFromChar(String A, String B) {
        char[] ACharArr = A.toCharArray();
        char bChar = B.charAt(0);

        StringBuilder sb = new StringBuilder();
        int bIndex = A.indexOf(bChar);
        if(bIndex == -1) return A;
        sb.append(A.substring(0, bIndex));
        for(int i = bIndex; i < ACharArr.length; i++){
            if(ACharArr[i] >= 'a' && ACharArr[i] <= 'z'){
                char c = (char)(ACharArr[i] - 'a' + 'A');
                sb.append(c);
            }else{
                sb.append(ACharArr[i]);
            }
        }
        return sb.toString();
    }
}
