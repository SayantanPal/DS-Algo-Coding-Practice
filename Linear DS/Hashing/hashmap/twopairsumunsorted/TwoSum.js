export function pair_sum_unsorted_two_pass(nums, target) {
  const numMap = {}
  // First pass: Populate the hash map with each number and its index.
  for (let i = 0; i < nums.length; i++) {
    numMap[nums[i]] = i
  }
  // Second pass: Check for each number's complement in the hash map.
  for (let i = 0; i < nums.length; i++) {
    const complement = target - nums[i]
    if (complement in numMap && numMap[complement] !== i) {
      return [i, numMap[complement]]
    }
  }
  return []
}


export function pair_sum_unsorted(nums, target) {
  const hashmap = {}
  for (let i = 0; i < nums.length; i++) {
    const x = nums[i]
    if (target - x in hashmap) {
      return [hashmap[target - x], i]
    }
    hashmap[x] = i
  }
  return []
}