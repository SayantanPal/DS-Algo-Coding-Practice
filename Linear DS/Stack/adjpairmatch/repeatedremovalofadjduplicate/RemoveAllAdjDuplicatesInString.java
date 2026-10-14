package adjpairmatch.repeatedremovalofadjduplicate;

import java.util.ArrayDeque;
import java.util.Deque;


/*
LC - 1047. Remove All Adjacent Duplicates In String
You are given a string s consisting of lowercase English letters.
A duplicate removal consists of choosing two adjacent and equal letters and removing them.
We repeatedly make duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made.
It can be proven that the answer is unique.

Example 1:
Input: s = "abbaca"
Output: "ca"
Explanation: For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".

Example 2:
Input: s = "azxxzy"
Output: "ay"

Constraints:
1 <= s.length <= 105
s consists of lowercase English letters.
* */
// Link: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAllAdjDuplicatesInString {

    public String removeAdjacentDuplicates(String A) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(char c: A.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == c) stack.pop();
            else {
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    /** Repeatedly remove adjacent pairs of identical characters until
     *  none remain, using a single-pass stack. */
    public static String removeAdjacentDuplicates_v2(String s) {
        StringBuilder stack = new StringBuilder();
        for (char c : s.toCharArray()) {
            int top = stack.length() - 1;
            if (top >= 0 && stack.charAt(top) == c) stack.deleteCharAt(top);
            else stack.append(c);
        }
        return stack.toString();
    }
}
/*
when remove adjacent_duplicates method is called on "abcde", first result stack appends 'a' and then on scanning 'b', it appens 'b', and so on and so forth same for 'c', 'd' and 'e' resuling into whole string "abcde".
here in this example there is no adjacent duplicate, hence nothing to remove.

But for "abba", on first 'a', it appends 'a' and similarly for 2nd 'b'.
But then when again 'b' is scanned, its last appended char is 'b' which instead will get popped out from the earlier occurrence without any extra pushes and then in the stack only first 'a' remains.
On scanning  the last and final 'a', the remaining 'a' in the stack forms again another adjacent pair, then the existing 'a' will be popped out to empty the stack.
Hence the resultant string also becomes empty "".
*
*
* */