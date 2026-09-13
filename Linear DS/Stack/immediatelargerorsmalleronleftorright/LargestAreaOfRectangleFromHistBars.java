package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestAreaOfRectangleFromHistBars {

    // Using 2 stacks - Not Space Optimized
    public int largestRectangleArea_v1(int[] A) {

        Deque<Integer> prevImmediateSmallerOnLeft = new ArrayDeque<>();
        Deque<Integer> nextImmediateSmallerOnRight = new ArrayDeque<>();

        int[] prevImmediateSmallerOnLeftArr = new int[A.length]; // left wall
        int[] nextImmediateSmallerOnRightArr = new int[A.length]; // right wall

        for(int i = 0; i < A.length; i++){
            // Try to find the previous immediate smaller element on left
            // Note down the index for previous immediate smaller element on left as index of left wall
            while(!prevImmediateSmallerOnLeft.isEmpty() && A[prevImmediateSmallerOnLeft.peek()] >= A[i]){
                prevImmediateSmallerOnLeft.pop();
            }
            prevImmediateSmallerOnLeftArr[i] = prevImmediateSmallerOnLeft.isEmpty() ? -1: prevImmediateSmallerOnLeft.peek();
            prevImmediateSmallerOnLeft.push(i);
        }

        for(int i = A.length - 1; i >=0; i--){
            // Try to find the next immediate smaller element on right
            // Note down the index for next immediate smaller element on right as index of right wall
            while(!nextImmediateSmallerOnRight.isEmpty() && A[nextImmediateSmallerOnRight.peek()] >= A[i]){
                nextImmediateSmallerOnRight.pop();
            }
            nextImmediateSmallerOnRightArr[i] = nextImmediateSmallerOnRight.isEmpty() ? A.length: nextImmediateSmallerOnRight.peek();
            nextImmediateSmallerOnRight.push(i);
        }

        int maxArea = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            // everything in between left and right wall becomes eligible for width of height A[i]
            // left and right walls are strictly smaller heights than A[i].
            // since between left previous smaller and right next smaller, all the bars are of either equal or greater length
            // So the desired range is (L, R) and not [L, R]. that is everything inclusive from (L + 1) to (R - 1)
            maxArea = Math.max(maxArea, ( (nextImmediateSmallerOnRightArr[i] - 1) - (prevImmediateSmallerOnLeftArr[i] + 1) + 1) * A[i] );
        }
        return maxArea;
    }
}
