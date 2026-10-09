package easy;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/crawler-log-folder/description/
public class DepthOfFolderStack {
    public int minOperations(String[] logs) {
        int stackDepth = 0;
        for(String s: logs){
            if(s.equals("../")){
                if(stackDepth > 0){
                    stackDepth--;
                }
            }
            else if(s.equals("./")) continue;
            else stackDepth++;
        }
        return stackDepth;
    }

    public int minOperationsUsingStack(String[] logs) {
        Deque<String> stack = new ArrayDeque<>();
        for(String s: logs){
            if(s.equals("../")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else if(s.equals("./")) continue;
            else stack.push(s);
        }
        return stack.size();
    }
}