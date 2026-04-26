import java.util.PriorityQueue;
import java.util.Collections;

class MedianOfAnIntegerStream {
    // Max-heap for the values belonging to the left half.
    private PriorityQueue<Integer> leftHalf;
    // Min-heap for the values belonging to the right half.
    private PriorityQueue<Integer> rightHalf;

    public MedianOfAnIntegerStream() {
        leftHalf = new PriorityQueue<>(Collections.reverseOrder());
        rightHalf = new PriorityQueue<>();
    }

    public void add(Integer num) {
        // If 'num' is less than or equal to the max of 'leftHalf', it belongs to the
        // left half.
        if (leftHalf.isEmpty() || num <= leftHalf.peek()) {
            leftHalf.offer(num);
            // Rebalance the heaps if the size of the 'leftHalf' exceeds the size of
            // the 'rightHalf' by more than one.
            if (leftHalf.size() > rightHalf.size() + 1) {
                rightHalf.offer(leftHalf.poll());
            }
        } else {
            // Otherwise, it belongs to the right half.
            rightHalf.offer(num);
            // Rebalance the heaps If 'rightHalf' is larger than 'leftHalf'.
            if (rightHalf.size() > leftHalf.size()) {
                leftHalf.offer(rightHalf.poll());
            }
        }
    }

    public Double getMedian() {
        if (leftHalf.size() == rightHalf.size()) {
            return (leftHalf.peek() + rightHalf.peek()) / 2.0;
        }
        return (double) leftHalf.peek();
    }
}