package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

/*

LC - 1544. Make The String Great

Given a string s of lower and upper case English letters.
A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
0 <= i <= s.length - 2
s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.

Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
Notice that an empty string is also good.


Example 1:
Input: s = "leEeetcode"
Output: "leetcode"
Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".

Example 2:
Input: s = "abBAcC"
Output: ""
Explanation: We have many possible scenarios, and all lead to the same answer. For example:
"abBAcC" --> "aAcC" --> "cC" --> ""
"abBAcC" --> "abBA" --> "aA" --> ""

Example 3:
Input: s = "s"
Output: "s"


Constraints:
1 <= s.length <= 100
s contains only lower and upper case English letters.
* */

// Link: https://leetcode.com/problems/make-the-string-great/description/
public class MakingStringGreat {
    public boolean isUpperCaseAlpha(char c){
        return (c >= 'A' && c <= 'Z');
    }
    public boolean isLowerCaseAlpha(char c){
        return (c >= 'a' && c <= 'z');
    }

    public char toUpperCaseAlpha(char c){
        if(isLowerCaseAlpha(c)) return (char)(c - 'a' + 'A');
        else return c;
    }

    public char toLowerCaseAlpha(char c){
        if(isUpperCaseAlpha(c)) return (char)(c - 'A' + 'a');
        else return c;
    }

    public boolean isTogglingCaseEquals(char c1, char c2){
        return
                (isUpperCaseAlpha(c1) && isLowerCaseAlpha(c2) && (toLowerCaseAlpha(c1) == c2))
             || (isLowerCaseAlpha(c1) && isUpperCaseAlpha(c2) && (toUpperCaseAlpha(c1) == c2));
    }

    public String makeGood(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(!stack.isEmpty() && isTogglingCaseEquals(stack.peek(), c)) stack.pop();
            else stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
