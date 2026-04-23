package nextlargestnotorightusingstack;
import java.util.ArrayList;
import java.util.Stack;

public class NextLargestNoToTheRight {
    public ArrayList<Integer> next_largest_number_to_the_right(ArrayList<Integer> nums) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        // Initialize result list with zeros.
        for (int i = 0; i < nums.size(); i++) {
            res.add(0);
        }
        // Find the next largest number of each element, starting with the rightmost
        // element.
        for (int i = nums.size() - 1; i >= 0; i--) {
            // Pop values from the top of the stack until the current value's next largest
            // number is at the top.
            while (!stack.isEmpty() && stack.peek() <= nums.get(i)) {
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
