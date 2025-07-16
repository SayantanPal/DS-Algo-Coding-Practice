import java.util.Stack;

public class LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // base for length calc
        int maxLength = 0;

        // Keep track of indexes of unmatched (
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop(); // Use a stack to match ) with (
                if (stack.isEmpty()) {
                    stack.push(i); // reset base
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }
}
