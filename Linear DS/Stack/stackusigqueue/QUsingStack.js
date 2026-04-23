export class Queue {
  constructor() {
    this.enqueueStack = []
    this.dequeueStack = []
  }

  enqueue(x) {
    this.enqueueStack.push(x)
  }

  transferEnqueueToDequeue() {
    // If the dequeue stack is empty, push all elements from the enqueue stack
    // onto the dequeue stack. This ensures the top of the dequeue stack
    // contains the most recent value.
    if (this.dequeueStack.length === 0) {
      while (this.enqueueStack.length > 0) {
        this.dequeueStack.push(this.enqueueStack.pop())
      }
    }
  }

  dequeue() {
    this.transferEnqueueToDequeue()
    // Pop and return the value at the top of the dequeue stack.
    return this.dequeueStack.length > 0 ? this.dequeueStack.pop() : null
  }

  peek() {
    this.transferEnqueueToDequeue()
    return this.dequeueStack.length > 0
      ? this.dequeueStack[this.dequeueStack.length - 1]
      : null
  }
}