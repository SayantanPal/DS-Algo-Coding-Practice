package heap.sortksortedarr;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SortKSortedArr {

    public ArrayList<Integer> sort_a_k_sorted_array(ArrayList<Integer> nums, int k) {
        // Populate a min-heap with the first k + 1 values in 'nums'.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = nums.size();
        for (int i = 0; i <= Math.min(k, n - 1); i++) {
            minHeap.offer(nums.get(i));
        }
        // Replace elements in the array with the minimum from the heap at each
        // iteration.
        int insertIndex = 0;
        for (int i = k + 1; i < n; i++) {
            nums.set(insertIndex++, minHeap.poll());
            minHeap.offer(nums.get(i));
        }
        // Pop the remaining elements from the heap to finish sorting the array.
        while (!minHeap.isEmpty()) {
            nums.set(insertIndex++, minHeap.poll());
        }
        return nums;
    }
}
