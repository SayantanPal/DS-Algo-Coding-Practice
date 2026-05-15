import java.util.Deque;
import java.util.LinkedList;

// Problem: Sliding Window Maximum with K elements
// I/P: nums = [1,3,-1,-3,5,3,6,7], k = 3
// O/P: [3,3,5,5,6,7]
// Efficient Solution: Use Deque to store useful elements in current window for O(n) solution

// Leetcode: https://leetcode.com/problems/sliding-window-maximum/
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

    // windows of k elems are (0 to k-1), (1 to k), (2 to k + 1),...so on
    // For each window of k elem, there is a double ended queue where we maintain only index of visited elements in below particular order
    // while inserting new element into de-queue, we make sure we maintain monotonous decreasing order sequence of elements
    // to fit a new incoming element into existing de-queue, we try to put it in queue such that all numbers before or left to that new incoming element are greater in decreasing order from left to right
    // we make sure that the newly incoming element is always the last one added to the rear end last of queue at right
    // and also we make sure that the front head first at left of queue always contain the biggest element within the window of size k
    // that's why before adding and positioning a new element index to the de-queue, we check if the front head first biggest element at left end is within window size k or not, followed by tracking + adding new incoming element index
    public int[] maxSlidingWindow_v2(int[] nums, int k) {
        Deque<Integer> monotonicDecreasingDeque = new LinkedList<Integer>();
        int[] result = new int[nums.length - k + 1];
        int j = 0;

        for(int i = 0; i < k; i++){
            // maintain monotonous decreasing order of deque
            while(!monotonicDecreasingDeque.isEmpty() && nums[i] > nums[monotonicDecreasingDeque.peekLast()]){
                monotonicDecreasingDeque.pollLast();
            }
            monotonicDecreasingDeque.offerLast(i);
        }
        // pick the maximum of current window
        result[j++] = nums[monotonicDecreasingDeque.peekFirst()];

        for(int i = k; i < nums.length; i++){
            // 1. squeeze the window from the left side to get current valid window
            while(!monotonicDecreasingDeque.isEmpty() && monotonicDecreasingDeque.peekFirst() <= (i - k)){
                // if(!monotonicDecreasingDeque.isEmpty() && monotonicDecreasingDeque.peekFirst() == (i - k)){
                monotonicDecreasingDeque.pollFirst();
            }

            if(!monotonicDecreasingDeque.isEmpty() && monotonicDecreasingDeque.peekFirst() == (i - k)){
                monotonicDecreasingDeque.pollFirst();
            }

            // 2. maintain monotonous decreasing order of deque
            while(!monotonicDecreasingDeque.isEmpty() && nums[i] > nums[monotonicDecreasingDeque.peekLast()]){
                monotonicDecreasingDeque.pollLast();
            }
            monotonicDecreasingDeque.offerLast(i);

            // 3. pick the maximum of current window
            result[j++] = nums[monotonicDecreasingDeque.peekFirst()];
        }

        //     for(int i = 0; i < nums.length; i++){
        //         //sqeeze the window from the left side to get current valid window
        //         while(!monotonicDecreasingDeque.isEmpty() && monotonicDecreasingDeque.peekFirst() <= (i - k)){
        //             monotonicDecreasingDeque.pollFirst();
        //         }

        //         // maintain monotonous decreasing order of deque
        //         while(!monotonicDecreasingDeque.isEmpty() && nums[i] > nums[monotonicDecreasingDeque.peekLast()]){
        //             monotonicDecreasingDeque.pollLast();
        //         }
        //         monotonicDecreasingDeque.offerLast(i);

        //         // pick the maximum of current window starting with index 0 to k-1 as first k elem windows
        //         if(i >= k - 1){
        //             result[j++] = nums[monotonicDecreasingDeque.peekFirst()];
        //         }
        //    }
        return result;
    }

}
