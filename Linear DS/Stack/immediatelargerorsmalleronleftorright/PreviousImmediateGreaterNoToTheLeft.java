package immediatelargerorsmalleronleftorright;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

// Link:
public class PreviousImmediateGreaterNoToTheLeft {

    public static int[] previous_immediate_greater_number_index_to_the_left(int[] A) {
        Deque<Integer> prevImmediateGreaterOnLeftIndex = new ArrayDeque<>();

        int[] prevImmediateGreaterOnLeftArrIndex = new int[A.length]; // left wall

        for(int i = 0; i < A.length; i++){
            // Try to find the previous immediate greater element on left
            // Note down the index for previous immediate greater element on left as index of left wall
            while(!prevImmediateGreaterOnLeftIndex.isEmpty() && A[prevImmediateGreaterOnLeftIndex.peek()] <= A[i]){
                prevImmediateGreaterOnLeftIndex.pop(); // pop out all in between larger or equal elements
            }
            prevImmediateGreaterOnLeftArrIndex[i] = prevImmediateGreaterOnLeftIndex.isEmpty() ? -1: prevImmediateGreaterOnLeftIndex.peek();
            prevImmediateGreaterOnLeftIndex.push(i);
        }
        return prevImmediateGreaterOnLeftArrIndex;
    }
}
