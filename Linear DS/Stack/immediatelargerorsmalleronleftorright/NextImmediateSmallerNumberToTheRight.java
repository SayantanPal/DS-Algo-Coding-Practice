package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextImmediateSmallerNumberToTheRight {

    public static int[] next_immediate_smaller_number_to_the_right(int[] A) {
        Deque<Integer> nextImmediateSmallerOnRightIndex = new ArrayDeque<>();

        int[] nextImmediateSmallerOnRightArr = new int[A.length]; // right wall

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate smaller element on right
            // Note down the index for next immediate smaller element on right as index of right wall
            while(!nextImmediateSmallerOnRightIndex.isEmpty() && A[nextImmediateSmallerOnRightIndex.peek()] >= A[i]){
                nextImmediateSmallerOnRightIndex.pop(); // pop out all in between larger or equal elements
            }
            nextImmediateSmallerOnRightArr[i] = nextImmediateSmallerOnRightIndex.isEmpty() ? A.length: nextImmediateSmallerOnRightIndex.peek();
            nextImmediateSmallerOnRightIndex.push(i);
        }

        return nextImmediateSmallerOnRightArr;
    }
}
