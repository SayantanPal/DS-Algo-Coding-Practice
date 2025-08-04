package pq;

import java.util.PriorityQueue;

/*
* Implement a class that continuously returns the Kth largest element in a stream of integers.
* */
public class KthLargestElement {
    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargestElement(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }
    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }
}