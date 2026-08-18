export function k_sum_subarrays(nums, k) {
  const n = nums.length
  let count = 0
  // Populate the prefix sum array, setting its first element to 0.
  const prefixSum = [0]
  for (let i = 0; i < n; i++) {
    prefixSum.push(prefixSum[prefixSum.length - 1] + nums[i])
  }
  // Loop through all valid pairs of prefix sum values to find all subarrays that sum to 'k'.
  for (let j = 1; j <= n; j++) {
    for (let i = 1; i <= j; i++) {
      if (prefixSum[j] - prefixSum[i - 1] === k) {
        count++
      }
    }
  }
  return count
}

export function k_sum_subarrays_optimized(nums, k) {
  let count = 0
  const prefixSumMap = new Map()
  prefixSumMap.set(0, 1) // To handle subarrays that sum to 'k' from the start
  let currPrefixSum = 0
  for (const num of nums) {
    // Update the running prefix sum by adding the current number
    currPrefixSum += num
    // If a subarray with sum 'k' exists, increment 'count' by the number of times it has been found
    if (prefixSumMap.has(currPrefixSum - k)) {
      count += prefixSumMap.get(currPrefixSum - k)
    }
    // Store the 'currPrefixSum' value in the hash map
    prefixSumMap.set(currPrefixSum, (prefixSumMap.get(currPrefixSum) || 0) + 1)
  }
  return count
}