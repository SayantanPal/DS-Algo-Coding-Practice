export function next_largest_number_to_the_right(nums) {
  const res = new Array(nums.length).fill(0)
  const stack = []
  // Find the next largest number for each element, starting from the right.
  for (let i = nums.length - 1; i >= 0; i--) {
    // Pop values from the stack until the current value's next largest
    // number is found or the stack is empty.
    while (stack.length && stack[stack.length - 1] <= nums[i]) {
      stack.pop()
    }
    // If the stack is empty, no greater number to the right exists.
    res[i] = stack.length ? stack[stack.length - 1] : -1

    // Push the current number onto the stack.
    stack.push(nums[i])
  }
  return res
}