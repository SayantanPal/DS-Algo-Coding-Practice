package easy;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
public class maxDepthOfParenthesisStack {
    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = -1;
        for(char c: s.toCharArray()){
            if(c == '(') depth++;
            else if(c == ')') depth--;
            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

    public int maxDepthUsingStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int maxDepth = -1;
        for(char c: s.toCharArray()){
            if(c == '(') stack.push(c);
            else if(c == ')') stack.pop();
            maxDepth = Math.max(maxDepth, stack.size());
        }
        return maxDepth;
    }
}
