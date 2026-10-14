package arithmeticexpressions.validbalancedparenthesis;

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

    public static boolean isValidBalancedParenthesis_v1(String s) {
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

    public boolean isValidBalancedParenthesis_v2(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if(!stack.isEmpty()) {
                if (c == ')' && stack.pop() != '(') return false;
                else if (c == '}' && stack.pop() != '{') return false;
                else if (c == ']' && stack.pop() != '[') return false;
            }
            stack.push(c);
        }
        return stack.isEmpty(); // this check is needed when input is for ex say: ({[
    }

    public boolean isValidBalancedParenthesis_v3(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty(); // this check is needed when input is for ex say: ({[
    }

    /*
    Without the isEmpty() guard, calling stack.pop() on an empty stack throws an exception when a closing bracket appears with no matching opener.

    For input ")", the stack is empty when ) is encountered (no opener was ever pushed)
    The code reaches stack.pop() with nothing on the stack
    ArrayDeque.pop() actually returns null rather than throwing, but then .equals() on null throws a NullPointerException — or if using element()/pop() directly, a NoSuchElementException
    The isEmpty() check short-circuits first, returning false safely to signal the string is invalid
    This is a classic defensive guard pattern: check the container is non-empty before accessing its top element
    * */
}
