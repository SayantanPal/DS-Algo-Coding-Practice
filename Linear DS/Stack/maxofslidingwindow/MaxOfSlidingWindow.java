package maxofslidingwindow;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class MaxOfSlidingWindow {
    public ArrayList<Integer> maximums_of_sliding_window(ArrayList<Integer> nums, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        Deque<int[]> dq = new LinkedList<>();
        int left = 0, right = 0;
        while (right < nums.size()) {
            // 1) Ensure the values of the deque maintain a monotonic decreasing order
            // by removing candidates <= the current candidate.
            while (!dq.isEmpty() && dq.peekLast()[0] <= nums.get(right)) {
                dq.pollLast();
            }
            // 2) Add the current candidate.
            dq.offerLast(new int[] {nums.get(right), right});
            // If the window is of length 'k', record the maximum of the window.
            if (right - left + 1 == k) {
                // 3) Remove values whose indexes occur outside the window.
                if (!dq.isEmpty() && dq.peekFirst()[1] < left) {
                    dq.pollFirst();
                }
                // The maximum value of this window is the leftmost value in the deque.
                res.add(dq.peekFirst()[0]);
                // Slide the window by advancing both 'left' and 'right'. The right
                // pointer always gets advanced so we just need to advance 'left'
                left++;
            }
            right++;
        }
        return res;
    }
}
