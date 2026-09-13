package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestAreaOfRectangleFromHistBars {

    // Using 2 stacks - Not Space Optimized
    public int largestRectangleArea_v1(int[] A) {

        int[] nextImmediateSmallerOnRightArr = NextImmediateSmallerNumberToTheRight.next_immediate_smaller_number_index_to_the_right(A);
        int[] prevImmediateSmallerOnLeftArr = PreviousImmediateSmallerNoToTheLeft.previous_immediate_smaller_number_index_to_the_left(A);

        int maxArea = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            // everything in between left and right wall becomes eligible for width of height A[i]
            // left and right walls are strictly smaller heights than A[i].
            // since between left previous smaller and right next smaller, all the bars are of either equal or greater length than A[i]
            // So the desired range is (L, R) and not [L, R]. that is everything inclusive from (L + 1) to (R - 1)
            maxArea = Math.max(maxArea, ( (nextImmediateSmallerOnRightArr[i] - 1) - (prevImmediateSmallerOnLeftArr[i] + 1) + 1) * A[i] );
        }
        return maxArea;
    }
}
