import java.util.Deque;
import java.util.LinkedList;

// Problem: Sliding Window Maximum with K elements
// I/P: nums = [1,3,-1,-3,5,3,6,7], k = 3
// O/P: [3,3,5,5,6,7]
// Efficient Solution: Use Deque to store useful elements in current window for O(n) solution

public class SlidingWindowMaximumWithKElem {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1]; // Non-overlapping window -> n/k; since overlapping window -> n - k + 1
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {


            // Left(Head/Front/First) Side of Window: Remove elements out of window
            while (!deque.isEmpty() && deque.peek() < i - k + 1) // deque.peekFirst() < i - k + 1
                deque.poll();      //deque.pollFirst() or deque.removeFirst();

            // Right(Rear/Last) Side of Window: If new unseen upcoming number is greater, remove smaller elements
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast(); // deque.removeLast();

            deque.offer(i); // deque.add(i)

            if (i >= k - 1)
                result[i - k + 1] = nums[deque.peek()];
        }
        return result;
    }

}
