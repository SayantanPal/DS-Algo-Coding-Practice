package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextImmediateGreaterNumberToTheRight {

    public static int[] next_immediate_greater_number_index_to_the_right(int[] A) {
        Deque<Integer> nextImmediateGreaterOnRightIndex = new ArrayDeque<>();

        int[] nextImmediateGreaterOnRightArrIndex = new int[A.length]; // right wall

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate greater element on right
            // Note down the index for next immediate greater element on right as index of right wall
            while(!nextImmediateGreaterOnRightIndex.isEmpty() && A[nextImmediateGreaterOnRightIndex.peek()] <= A[i]){
                nextImmediateGreaterOnRightIndex.pop(); // pop out all in between larger or equal elements
            }
            nextImmediateGreaterOnRightArrIndex[i] = nextImmediateGreaterOnRightIndex.isEmpty() ? A.length: nextImmediateGreaterOnRightIndex.peek();
            nextImmediateGreaterOnRightIndex.push(i);
        }

        return nextImmediateGreaterOnRightArrIndex;
    }
}
