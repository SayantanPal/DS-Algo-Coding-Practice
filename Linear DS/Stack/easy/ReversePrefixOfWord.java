package easy;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/reverse-prefix-of-word/description/
public class ReversePrefixOfWord {

    public String reversePrefix2(String word, char ch) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        int i = 0;
        boolean hasSearchChar = false;
        while(i < word.length()){
            stack.push(word.charAt(i));
            if(word.charAt(i) == ch){
                hasSearchChar = true;
                break;
            }
            i++;
        }

        while(!stack.isEmpty()) sb.append(stack.pop());

        if(!hasSearchChar) sb = sb.reverse();

        i++;
        while(i < word.length()){
            sb.append(word.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public String reversePrefix(String word, char ch) {

        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        boolean hasSearchChar = false;
        boolean forOnceFirstOccurance = true;
        for(char c: word.toCharArray()){
            if(c == ch){
                if(forOnceFirstOccurance){
                    hasSearchChar = true;
                    sb.append(ch);
                    while(!stack.isEmpty()) sb.append(stack.pop());
                    forOnceFirstOccurance = false;
                }else{
                    sb.append(c);
                }
            } else{
                if(forOnceFirstOccurance){
                    stack.push(c);
                }else{
                    sb.append(c);
                }
            }
        }

        while(!stack.isEmpty()) sb.append(stack.pop());

        if(!hasSearchChar) sb = sb.reverse();

        return sb.toString();
    }
}
