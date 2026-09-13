package immediatelargerorsmalleronleftorright;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

// Link:
public class PreviousImmediateSmallerNoToTheLeft {


    public static int[] previous_immediate_smaller_number_to_the_left(int[] A) {
        Deque<Integer> prevImmediateSmallerOnLeftIndex = new ArrayDeque<>();

        int[] prevImmediateSmallerOnLeftArr = new int[A.length]; // left wall

        for(int i = 0; i < A.length; i++){
            // Try to find the previous immediate smaller element on left
            // Note down the index for previous immediate smaller element on left as index of left wall
            while(!prevImmediateSmallerOnLeftIndex.isEmpty() && A[prevImmediateSmallerOnLeftIndex.peek()] >= A[i]){
                prevImmediateSmallerOnLeftIndex.pop(); // pop out all in between larger or equal elements
            }
            prevImmediateSmallerOnLeftArr[i] = prevImmediateSmallerOnLeftIndex.isEmpty() ? -1: prevImmediateSmallerOnLeftIndex.peek();
            prevImmediateSmallerOnLeftIndex.push(i);
        }
        return prevImmediateSmallerOnLeftArr;
    }

    public ArrayList<Integer> previous_immediate_smaller_number_to_the_left(ArrayList<Integer> nums) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        // Initialize result list with zeros.
        for (int i = 0; i < nums.size(); i++) {
            res.add(0);
        }

        for (int i = 0; i < nums.size(); i++) {
            // keep on searching smaller no than current num[i] in stack
            // the moment we find a number which is < current num[i], we stop
            // that means we keep on popping while stack elements are >= current num[i]
            while (!stack.isEmpty() && stack.peek() >= nums.get(i)) {
                stack.pop();
            }
            // Record the current value's next largest number, which is at the top of the
            // stack. If the stack is empty, record -1.
            res.set(i, stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums.get(i));
        }
        return res;
    }
}
