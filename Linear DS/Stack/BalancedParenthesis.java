import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
* Coding: Write a method to detect if a given string has balanced parentheses — {}, [], and ()
* */
// Link: https://leetcode.com/problems/valid-parentheses/
public class BalancedParenthesis {
    private boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public boolean isBalanced(String str) {
        Deque<Character> stack = new ArrayDeque<>(); //Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if (!isMatching(open, ch)) return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isBalanced_v2(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }
}
