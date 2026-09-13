export function maximums_of_sliding_window(nums, k) {
  const res = []
  const dq = []
  let left = 0,
    right = 0
  while (right < nums.length) {
    // 1) Ensure the values of the deque maintain a monotonic decreasing order
    // by removing candidates <= the current candidate.
    while (dq.length > 0 && dq[dq.length - 1][0] <= nums[right]) {
      dq.pop()
    }
    // 2) Add the current candidate.
    dq.push([nums[right], right])
    // If the window is of length 'k', record the maximum of the window.
    if (right - left + 1 === k) {
      // 3) Remove values whose indexes occur outside the window.
      if (dq.length > 0 && dq[0][1] < left) {
        dq.shift()
      }
      // The maximum value of this window is the leftmost value in the
      // deque.
      res.push(dq[0][0])
      // Slide the window by advancing both 'left' and 'right'. The right
      // pointer always gets advanced so we just need to advance 'left'
      left += 1
    }
    right += 1
  }
  return res
}