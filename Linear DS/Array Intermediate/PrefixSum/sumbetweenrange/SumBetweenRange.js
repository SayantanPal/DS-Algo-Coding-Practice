export class SumBetweenRange {
  constructor(nums) {
    this.prefixSum = [nums[0]]
    for (let i = 1; i < nums.length; i++) {
      this.prefixSum.push(this.prefixSum[i - 1] + nums[i])
    }
  }

  sumRange(i, j) {
    if (i === 0) {
      return this.prefixSum[j]
    }
    return this.prefixSum[j] - this.prefixSum[i - 1]
  }
}