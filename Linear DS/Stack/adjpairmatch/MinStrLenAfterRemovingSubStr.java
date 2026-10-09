package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/minimum-string-length-after-removing-substrings/description/
public class MinStrLenAfterRemovingSubStr {
    public int minLength(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(c == 'B' && !stack.isEmpty() && stack.peek() == 'A') stack.pop();
            else if(c == 'D' && !stack.isEmpty() && stack.peek() == 'C') stack.pop();
            else stack.push(c);
        }
        return stack.size();
    }
}
