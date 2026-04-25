export function geometric_sequence_triplets(nums, r) {
  // Use Maps to track frequencies, similar to defaultdict in Python
  const leftMap = new Map()
  const rightMap = new Map()
  let count = 0
  // Populate 'rightMap' with the frequency of each element in the array
  for (const x of nums) {
    rightMap.set(x, (rightMap.get(x) || 0) + 1)
  }
  // Search for geometric triplets that have x as the center
  for (const x of nums) {
    // Decrement the frequency of x in 'rightMap' since x is now being
    // processed and is no longer to the right
    rightMap.set(x, rightMap.get(x) - 1)
    if (x % r === 0) {
      // Get frequencies of potential left and right elements
      const leftFreq = leftMap.get(x / r) || 0
      const rightFreq = rightMap.get(x * r) || 0
      // Add the number of triplets formed with x as the middle element
      count += leftFreq * rightFreq
    }
    // Increment the frequency of x in 'leftMap' since it'll be a part of the
    // left side of the array once we iterate to the next value of `x`
    leftMap.set(x, (leftMap.get(x) || 0) + 1)
  }
  return count
}