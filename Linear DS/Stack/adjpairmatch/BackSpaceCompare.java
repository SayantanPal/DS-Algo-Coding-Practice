package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/backspace-string-compare/description/
public class BackSpaceCompare {

    public boolean backspaceCompare2(String s, String t) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            if(c != '#') stack.push(c);
            else if(!stack.isEmpty()) stack.pop();
        }

        StringBuilder sb1 = new StringBuilder();

        while(!stack.isEmpty()) sb1.append(stack.pop());


        for(char c: t.toCharArray()){
            if(c != '#') stack.push(c);
            else if(!stack.isEmpty()) stack.pop();
        }

        StringBuilder sb2 = new StringBuilder();
        while(!stack.isEmpty()) sb2.append(stack.pop());

        return sb1.toString().equals(sb2.toString());
    }

    public boolean backspaceCompare(String s, String t) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c: s.toCharArray()){
            if(c == '#'){
                if(!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(c);
        }

        StringBuilder sb1 = new StringBuilder();

        while(!stack.isEmpty()) sb1.append(stack.pop());


        for(char c: t.toCharArray()){
            if(c == '#'){
                if(!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(c);
        }

        StringBuilder sb2 = new StringBuilder();
        while(!stack.isEmpty()) sb2.append(stack.pop());

        return sb1.toString().equals(sb2.toString());
    }
}
