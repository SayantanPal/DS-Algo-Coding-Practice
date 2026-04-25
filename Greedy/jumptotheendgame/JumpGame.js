export function jump_to_the_end(nums) {
  // Set the initial destination to the last index in the array
  let destination = nums.length - 1
  // Traverse the array in reverse
  for (let i = nums.length - 1; i >= 0; i--) {
    // If we can reach the destination from the current index
    if (i + nums[i] >= destination) {
      destination = i
    }
  }
  // If the destination is 0, we can jump to the end from the start
  return destination === 0
}