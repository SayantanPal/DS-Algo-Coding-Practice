package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextImmediateSmallerNumberToTheRight {

    public static int[] next_immediate_smaller_number_index_to_the_right(int[] A) {
        Deque<Integer> nextImmediateSmallerOnRightIndex = new ArrayDeque<>();

        // answer array stores index of right wall ie index of next immediate smaller element on right of A[i]
        int[] nextImmediateSmallerOnRightIndexArr = new int[A.length];

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate smaller element on right
            // Note down the index for next immediate smaller element on right as index of right wall
            while(!nextImmediateSmallerOnRightIndex.isEmpty() && A[nextImmediateSmallerOnRightIndex.peek()] >= A[i]){
                nextImmediateSmallerOnRightIndex.pop(); // pop out all in between larger or equal elements
            }
            nextImmediateSmallerOnRightIndexArr[i] = nextImmediateSmallerOnRightIndex.isEmpty() ? A.length: nextImmediateSmallerOnRightIndex.peek();
            nextImmediateSmallerOnRightIndex.push(i);
        }

        return nextImmediateSmallerOnRightIndexArr;
    }
}
