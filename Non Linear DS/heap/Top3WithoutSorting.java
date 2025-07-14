package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Top3WithoutSorting {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 5, 30, 20, 50);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(nums);
        for(int i = 0; i < 3; i++) {
            System.out.println("Top " + (i + 1) + ": " + maxHeap.poll());
        }
    }
}
