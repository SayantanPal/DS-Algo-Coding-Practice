package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC - 844. Backspace String Compare
Given two strings s and t, return true if they are equal when both are typed into empty text editors.
'#' means a backspace character.
Note that after backspacing an empty text, the text will continue empty.

Example 1:
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Constraints:
1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(n) time and O(1) space?
* */

// Link: https://leetcode.com/problems/backspace-string-compare/description/
public class BackSpaceCompare {

    public boolean backspaceCompare_v3(String s, String t) {
        StringBuilder sb1 = new StringBuilder();
        for(char c: s.toCharArray()){
            int top = sb1.length() - 1;
            if(c == '#'){
                if(top >= 0)
                    sb1.deleteCharAt(top);
                // nothing to do for else since: Note that after backspacing an empty text, the text will continue empty.
                // if(c == '#' && top >= 0) would be bug because when top < 0 with c == # we need to ignore and should not move to else block
            }
            else sb1.append(c);
        }

        StringBuilder sb2 = new StringBuilder();
        for(char c: t.toCharArray()){
            int top = sb2.length() - 1;
            if(c == '#'){
                if(top >= 0)
                    sb2.deleteCharAt(top);
                // nothing to do for else since: Note that after backspacing an empty text, the text will continue empty.
                // if(c == '#' && top >= 0) would be bug because when top < 0 with c == # we need to ignore and should not move to else block
            }
            else sb2.append(c);
        }
        return sb1.toString().equals(sb2.toString());
    }

    public boolean backspaceCompare_v2(String s, String t) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            if(c != '#') stack.push(c);
            else if(!stack.isEmpty()) stack.pop();
        }

        StringBuilder sb1 = new StringBuilder();

        while(!stack.isEmpty()) sb1.append(stack.pop());


        for(char c: t.toCharArray()){
            if(c != '#') stack.push(c);
            else if(!stack.isEmpty()) stack.pop();
        }

        StringBuilder sb2 = new StringBuilder();
        while(!stack.isEmpty()) sb2.append(stack.pop());

        return sb1.toString().equals(sb2.toString());
    }

    public boolean backspaceCompare_v1(String s, String t) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            if(c == '#'){
                if(!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(c);
        }

        StringBuilder sb1 = new StringBuilder();

        while(!stack.isEmpty()) sb1.append(stack.pop());


        for(char c: t.toCharArray()){
            if(c == '#'){
                if(!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(c);
        }

        StringBuilder sb2 = new StringBuilder();
        while(!stack.isEmpty()) sb2.append(stack.pop());

        return sb1.toString().equals(sb2.toString());
    }
}
