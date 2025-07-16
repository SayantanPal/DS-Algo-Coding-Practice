import java.util.Stack;

/*
* Coding: Write a method to detect if a given string has balanced parentheses — {}, [], and ()
* */
public class BalancedParenthesis {
    private boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    public boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
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
}
