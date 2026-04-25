export function product_array_without_current_element(nums) {
  const n = nums.length
  const res = Array(n).fill(1)
  // Populate the output with the running left product.
  for (let i = 1; i < n; i++) {
    res[i] = res[i - 1] * nums[i - 1]
  }
  // Multiply the output with the running right product, from right to left.
  let rightProduct = 1
  for (let i = n - 1; i >= 0; i--) {
    res[i] *= rightProduct
    rightProduct *= nums[i]
  }
  return res
}