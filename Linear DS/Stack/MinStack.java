import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/min-stack/
class MinStack {
    int currMin = Integer.MAX_VALUE;
    Deque<Integer> minimumTrack, stack;
    public MinStack() {
        stack = new ArrayDeque<>();
        minimumTrack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        stack.offerFirst(val);
        if(val < currMin){
            currMin = val;
        }
        minimumTrack.offerFirst(currMin);
    }
    
    public void pop() {
        stack.pollFirst();
        minimumTrack.pollFirst();
        if(!minimumTrack.isEmpty())
            currMin = minimumTrack.peekFirst();
        else
            currMin = Integer.MAX_VALUE;
    }
    
    public int top() {
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minimumTrack.peekFirst();
    }
}
