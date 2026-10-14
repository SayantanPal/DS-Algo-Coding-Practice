package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

/*
You are given a string s.

Your task is to remove all digits by doing this operation repeatedly:
Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.
Note that the operation cannot be performed on a digit that does not have any non-digit character to its left.

Example 1:

Input: s = "abc"
Output: "abc"
Explanation: There is no digit in the string.

Example 2:
Input: s = "cb34"
Output: ""

Explanation: First, we apply the operation on s[2], and s becomes "c4".
Then we apply the operation on s[1], and s becomes "".

Constraints:

1 <= s.length <= 100
s consists only of lowercase English letters and digits.
The input is generated such that it is possible to delete all digits.
* */


// Link: https://leetcode.com/problems/clear-digits/
public class ClearDigits {

    public String clearDigits_v2(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            int top = sb.length() - 1;
            if(Character.isDigit(c)){
                if(top >= 0)
                    sb.deleteCharAt(top);
            }else
                sb.append(c);
        }
        return sb.toString();
    }

    public boolean isLowerCaseAlpha(char c){
        return (c >= 'a' && c <= 'z');
    }
    public String clearDigits(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(isLowerCaseAlpha(c)) stack.push(c);
            else if(!stack.isEmpty() && isLowerCaseAlpha(stack.peek())) stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());

        return sb.reverse().toString();
    }
}