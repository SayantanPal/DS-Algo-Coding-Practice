import { MinPriorityQueue } from './helpers/heap/index.js'

export function sort_a_k_sorted_array(nums, k) {
  // Populate a min-heap with the first k + 1 values in 'nums'.
  const minHeap = new MinPriorityQueue((x) => x)
  for (let i = 0; i <= k && i < nums.length; i++) {
    minHeap.enqueue(nums[i])
  }
  // Replace elements in the array with the minimum from the heap at each iteration.
  let insertIndex = 0
  for (let i = k + 1; i < nums.length; i++) {
    nums[insertIndex] = minHeap.dequeue()
    insertIndex++
    minHeap.enqueue(nums[i])
  }
  // Pop the remaining elements from the heap to finish sorting the array.
  while (!minHeap.isEmpty()) {
    nums[insertIndex] = minHeap.dequeue()
    insertIndex++
  }
  return nums
}