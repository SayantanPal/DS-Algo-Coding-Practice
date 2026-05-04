package rainwatertrapping;

// Link: https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {

    public int trapRainwater_usingPrefixSum(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = -1;
        rightMax[n - 1] = -1;

        int leftMaximum = -1;
        int rightMaximum = -1;
        for(int i = 1; i < n; i++){
            leftMaximum = Math.max(leftMaximum, height[i - 1]);
            leftMax[i] = leftMaximum;

            rightMaximum = Math.max(rightMaximum, height[n - i]);
            rightMax[n - i - 1] = rightMaximum;
        }
        // for(int i = 0; i < n; i++){
        //     System.out.println(i + " => " +leftMax[i] + " , " + rightMax[i]);
        // }
        int trappedRainWater = 0;
        for(int i = 1; i < n - 1; i++){
            if(height[i] < Math.min(leftMax[i], rightMax[i]))
                trappedRainWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return trappedRainWater;
    }

}
