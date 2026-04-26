import { MaxPriorityQueue } from './helpers/heap/MaxPriorityQueue.js'
import { MinPriorityQueue } from './helpers/heap/MinPriorityQueue.js'

export class MedianOfAnIntegerStream {
  constructor() {
    // Max-heap for the values belonging to the left half.
    this.leftHalf = new MaxPriorityQueue((n) => n)
    // Min-heap for the values belonging to the right half.
    this.rightHalf = new MinPriorityQueue((n) => n)
  }

  add(num) {
    // If 'num' is less than or equal to the max of 'leftHalf', it belongs to the left half.
    if (this.leftHalf.isEmpty() || num <= this.leftHalf.front()) {
      this.leftHalf.enqueue(num)
      // Rebalance: if leftHalf has more than one extra element
      if (this.leftHalf.size() > this.rightHalf.size() + 1) {
        this.rightHalf.enqueue(this.leftHalf.dequeue())
      }
    } else {
      // Otherwise, it belongs to the right half.
      this.rightHalf.enqueue(num)
      // Rebalance: if rightHalf has more elements than leftHalf
      if (this.rightHalf.size() > this.leftHalf.size()) {
        this.leftHalf.enqueue(this.rightHalf.dequeue())
      }
    }
  }

  get_median() {
    if (this.leftHalf.size() === this.rightHalf.size()) {
      return (this.leftHalf.front() + this.rightHalf.front()) / 2.0
    }
    return this.leftHalf.front()
  }
}