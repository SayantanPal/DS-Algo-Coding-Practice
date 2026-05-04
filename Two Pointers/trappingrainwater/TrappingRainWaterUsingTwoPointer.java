package trappingrainwater;

// Link: https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWaterUsingTwoPointer {

    public int trap(int[] height) {
        int leftIndex = 1, rightIndex = height.length - 2;
        int leftMax = height[0]; //  leftMax represents: the tallest wall to the left that can hold water at the current position
        int rightMax = height[height.length - 1]; //  rightMax represents: the tallest wall to the right that can hold water at the current position
        int trappedWater = 0;


        // with < instead of <= in case of equal leftMax and rightMax like [5, 0, 0, 0, 5] or [5, 0, 0, 5] - any one of the element remains unprocessed by one pointer (here in this case by leftpointer)
        // because loop stops when left and right index converge - thereby allowing neither left nor right index to process for that index
        while (leftIndex <= rightIndex) {
            leftMax = Math.max(leftMax, height[leftIndex - 1]); // track: left max starting from index 0 till leftIndex - 1 for leftIndex
            rightMax = Math.max(rightMax, height[rightIndex + 1]); // track: right max till rightIndex + 1 starting from index n - 1 for rightIndex

            // 3 possibilities leftMax is minimum or right max is minimum or both same
            // when same - any of left or right pointer can process

            // min among (max to the immediate left, at least min. max to the immediate right which is equal to max at farthest right)
            // or min among (max to the right, at least min. max to the immediate left which is equal to max at farthest right)
            if (leftMax < rightMax) { // immediate right max for the left index can be >= rightMax; but CANNOT be less than rightMax
                if (height[leftIndex] < leftMax) // If height[leftIndex] itself is taller than everything to its left, then no water can sit on top of it — it IS the wall.
                    trappedWater += leftMax - height[leftIndex];
                leftIndex++;
            } else { // if(rightMax <= leftMax){ // immediate left max for the right index can be >= leftMax; but CANNOT be less than leftMax
                if (height[rightIndex] < rightMax) // If height[rightIndex] itself is taller than everything to its right, then no water can sit on top of it — it IS the wall.
                    trappedWater += rightMax - height[rightIndex];
                rightIndex--;
            }
        }
        return trappedWater;
    }
}
