import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// Link: https://leetcode.com/problems/implement-queue-using-stacks/
public class QueueUsingStacks {

    Stack<Integer> stack1; //Deque<Integer> stack1;
    Stack<Integer> stack2; // Deque<Integer> stack2;

    public QueueUsingStacks() {
        stack1 = new Stack<>(); //new ArrayDeque<>();
        stack2 = new Stack<>(); // new ArrayDeque<>();
    }

    public void push(int x) {
        stack1.push(x); //stack1.offerFirst(x);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty())
                stack2.push( //stack2.offerFirst(
                        stack1.pop() //stack1.pollFirst()
                );
        }
        return stack2.pop(); // stack2.pollFirst();
    }

    public int peek() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty())
                stack2.push( // stack2.offerFirst(
                        stack1.pop() // stack1.pollFirst()
                );
        }
        return stack2.peek();// stack2.peekFirst();
    }

    public boolean empty() {
        // System.out.println("op stack: " + outputStack);
        // System.out.println("ip stack: " + inputStack);
        return stack1.isEmpty() && stack2.isEmpty();
    }

}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */