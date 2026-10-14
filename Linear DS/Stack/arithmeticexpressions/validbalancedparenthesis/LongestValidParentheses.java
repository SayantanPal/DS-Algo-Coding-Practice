package arithmeticexpressions.validbalancedparenthesis;

import java.util.Stack;

// Link: https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // base for length calc
        int maxLength = 0;

        // if the length of longest valid parenthesis span from index L to R
        // top of stack contains (L-1) index as the starting baseline from which it is getting valid
        // Keep track of indexes of unmatched (
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop(); // pop out index of matching opening parenthesis of a valid pair
                if (stack.isEmpty()) { // after popping out index of opening parenthesis if the stack is empty, that means it is extra redundant outer closing bracket
                    stack.push(i); // then reset base as (L-1)th index of next probable valid balanced parenthesis
                } else { // if index exists, then that index is (L-1)th index of so-far formed continuous valid balanced parenthesis
                    maxLength = Math.max(maxLength, i - stack.peek()); // R - (L - 1) = R - L + 1. here tos is L - 1
                }
            }
        }
        return maxLength;
    }
}
