package nextlargestnotorightusingstack;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

// Link: https://leetcode.com/problems/next-greater-element-i/
public class NextLargerNoToTheImmediateRight {

    // for all +ve numbers series, say, a,b,c,d,e => if for example, for any element arr[i] = d, d > e, then for all elements to the right of d can be safely eliminated,
    // because for all future numbers to the left of say, elem d, their largest number to the right can never be the smaller numbers to the right of d. So, at that point, d becomes eligible as one of the members to the right larger than those smaller numbers
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> monotonicallyDecreasingStack = new ArrayDeque<>();
        int[] answer = new int[nums2.length];
        int largest = -1;
        for(int i = nums2.length - 1; i >= 0; i--){
            largest = Math.max(largest, nums2[i]);
            while(!monotonicallyDecreasingStack.isEmpty() && monotonicallyDecreasingStack.peekFirst() <= nums2[i]){
                monotonicallyDecreasingStack.pollFirst();
            }
            if(!monotonicallyDecreasingStack.isEmpty())
                answer[i] = monotonicallyDecreasingStack.peekFirst();
            else
                answer[i] = -1;
            monotonicallyDecreasingStack.push(nums2[i]);
        }

        int[] lookUp = new int[largest + 1];

        for(int i = 0; i < answer.length; i++){
            lookUp[nums2[i]] = answer[i];
        }

        int[] result = new int[nums1.length];
        for(int i= 0; i < nums1.length; i++){
            result[i] = lookUp[nums1[i]];
        }

        return result;
    }

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
