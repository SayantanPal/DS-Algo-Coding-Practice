package lowleveldesigns;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/min-stack/
class MinStackUsing2Stacks {
    int currMin = Integer.MAX_VALUE;
    Deque<Integer> minimumStack, stack;
    public MinStackUsing2Stacks() {
        stack = new ArrayDeque<>();
        minimumStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.offerFirst(val);
        if(val < currMin){
            currMin = val;
        }
        minimumStack.offerFirst(currMin);
    }
    
    public void pop() {
        stack.pollFirst();
        minimumStack.pollFirst();
        if(!minimumStack.isEmpty())
            currMin = minimumStack.peekFirst();
        else
            currMin = Integer.MAX_VALUE;
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minimumStack.peekFirst();
    }
}
