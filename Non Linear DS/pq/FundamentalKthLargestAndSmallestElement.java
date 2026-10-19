package pq;

import java.util.PriorityQueue;

/*
* Implement a class that continuously returns the Kth largest element in a stream of integers.
* */
// Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
// Constraints: num contain duplicates
// Constraints: Time complexity should be O(n log k) - No sorting allowed - Sorting takes O(nlogn)
// Constraint: Not Kth distinct element;its kth largest elem - CANNOT USE TREESET
public class FundamentalKthLargestAndSmallestElement {

    // Less time optimized: O(n log n + (n - k) log n) - NOT ALLOWED
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> (b - a));

        for(int num: nums)
            maxHeap.offer(num);

        for(int i = 1; i <= k - 1; i++)
            maxHeap.poll();

        return maxHeap.peek();
    }

    // Less time optimized: O(n log n - k log n) - NOT ALLOWED
    public int findKthLargest_v2(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // first add n elements
        for(int i = 0; i < nums.length; i++){
            minHeap.offer(nums[i]);
        }

        // then evict smaller (n - k) smaller elements
        for(int i = 1; i <= nums.length - k; i++){
            minHeap.poll();
        }

        return minHeap.peek();
    }

    public int findKthLargest_v3(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->(a - b));
        int n = nums.length;
        for(int i = 0; i < nums.length; i++){
            minHeap.offer(nums[i]);
            // evict smaller (n - k) elements so that we end up having k larger elements
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    // More time optimized: O(n log k)
    public int findKthLargest_v4(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // O(k log k)
        for(int i = 0; i < k; i++){
            minHeap.offer(nums[i]);
        }

        // O((n - k) log k)
        // evict smaller (n - k) elements so that we end up having k larger elements
        for(int i = k; i < nums.length; i++){
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        return minHeap.peek();
    }

    // Link: https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1
    // T(n) = O(n log k)
    public int kthSmallest_v2(int[] arr, int k) {
        // Code here

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> (b - a));

        for(int i = 0; i < k; i++){
            maxHeap.offer(arr[i]);
        }

        // evict n - k larger elements so that we end up having k smaller elements
        // leaving behind k smaller elements
        for(int i = k; i < arr.length; i++){
            if(arr[i] < maxHeap.peek()){
                maxHeap.poll();
                maxHeap.offer(arr[i]);
            }
        }
        return maxHeap.peek();
    }
}