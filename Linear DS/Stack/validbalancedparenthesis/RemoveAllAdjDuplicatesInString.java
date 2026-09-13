package validbalancedparenthesis;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAllAdjDuplicatesInString {

    public String solve(String A) {
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
}
