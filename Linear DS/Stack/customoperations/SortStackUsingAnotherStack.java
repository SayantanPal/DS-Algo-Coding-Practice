package customoperations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// Link: https://www.geeksforgeeks.org/problems/sort-a-stack/1
public class SortStackUsingAnotherStack {

    public void sortStack(Stack<Integer> st) {
        // sort in st2 in desc order
        Stack<Integer> st2 = new Stack<>();
        while(!st.isEmpty()){
            int num = st.pop();
            while(!st2.isEmpty() && st2.peek() < num) st.push(st2.pop());
            st2.push(num);
        }

        // st gets re-populated in ascending order since st2 is in desc order
        while(!st2.isEmpty()) st.push(st2.pop());
    }

    public int[] solve(int[] A) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        for(int num: A) stack1.push(num);

        while(!stack1.isEmpty()){
            int elem = stack1.pop();
            while(!stack2.isEmpty() && stack2.peek() > elem){
                stack1.push(stack2.pop());
            }
            stack2.push(elem);
        }

        int j = A.length - 1;
        int[] ans = new int[A.length];
        while(!stack2.isEmpty()){
            ans[j--] = stack2.pop();
        }

        return ans;
    }
}
