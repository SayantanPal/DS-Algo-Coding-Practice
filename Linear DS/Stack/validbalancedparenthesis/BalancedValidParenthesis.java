package validbalancedparenthesis;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* Coding: Write a method to detect if a given string has balanced parentheses — {}, [], and ()
* */
// Link: https://leetcode.com/problems/valid-parentheses/
// link: https://www.geeksforgeeks.org/problems/parenthesis-checker2744/1
public class BalancedValidParenthesis {
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

    public static boolean isValidParenthesis_v1(String s) {
        // Write your code here.
        Deque<Character> stack = new ArrayDeque<>();
        char[] sArr = s.toCharArray();
        for(int i = 0; i < sArr.length; i++){
            if(!stack.isEmpty()){
                if(sArr[i] == ')' && stack.peekFirst() == '('){
                    stack.pop(); continue;
                }else if(sArr[i] == '}' && stack.peekFirst() == '{'){
                    stack.pop(); continue;
                }else if(sArr[i] == ']' && stack.peekFirst() == '['){
                    stack.pop(); continue;
                }
            }
            stack.push(sArr[i]);
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
        return stack.isEmpty(); // this check is needed when input is for ex say: ({[
    }
}
