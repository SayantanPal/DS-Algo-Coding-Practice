package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/clear-digits/
public class ClearDigits {

    public boolean isLowerCaseAlpha(char c){
        return (c >= 'a' && c <= 'z');
    }
    public String clearDigits(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(isLowerCaseAlpha(c)) stack.push(c);
            else if(!stack.isEmpty() && isLowerCaseAlpha(stack.peek())) stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.append(stack.pop());

        return sb.reverse().toString();
    }
}