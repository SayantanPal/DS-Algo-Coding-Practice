export function longest_chain_of_consecutive_numbers_brute_force(nums) {
  if (!nums || nums.length === 0) {
    return 0
  }
  let longestChain = 0
  // Look for chains of consecutive numbers that start from each number.
  for (const num of nums) {
    let currentNum = num
    let currentChain = 1
    // Continue to find the next consecutive numbers in the chain.
    while (nums.includes(currentNum + 1)) {
      currentNum += 1
      currentChain += 1
    }
    longestChain = Math.max(longestChain, currentChain)
  }
  return longestChain
}

export function longest_chain_of_consecutive_numbers(nums) {
  if (!nums || nums.length === 0) {
    return 0
  }
  // Convert array to a Set for O(1) lookups
  const numSet = new Set(nums)
  let longestChain = 0
  for (const num of numSet) {
    // If the current number is the smallest number in its chain, search for
    // the length of its chain.
    if (!numSet.has(num - 1)) {
      let currentNum = num
      let currentChain = 1
      // Continue to find the next consecutive numbers in the chain.
      while (numSet.has(currentNum + 1)) {
        currentNum += 1
        currentChain += 1
      }
      longestChain = Math.max(longestChain, currentChain)
    }
  }
  return longestChain
}
