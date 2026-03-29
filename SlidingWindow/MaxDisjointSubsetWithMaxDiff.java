// Problem: Maximum Disjoint (Non-Overlapping) number of subsets of size n with max-min ≤ K

/*
* Problem: Given a list of colors with their chromatic values,
*          select exactly N colors for a palette so that the difference between the maximum chromatic value and minimum chromatic value is no more than a threshold.
*          Find the maximum number of (non-overlapping) palettes that can be made from the given colors.

Example -1
Input:
colors = [6, 2, 10, 2, 11, 1, 3, 2)
paletteSize = 3
threshold = 4

Output: 2

Reason/Explanation:
At most 2 palettes can be made: [1, 2, 2], [2, 3, 6] with a difference (max - min) no more than 4.

Example -2
Input:
colors = [10, 15, 9, 10, 9, 1, 3, 3]
paletteSize = 4
threshold = 2

Output: 1

Reason/Explanation:
The only palette [9, 9, 10, 10] with max difference no more than 2.
*
* */

/*
* Good practice problems:
* Leetcode 2966. Divide Array Into Arrays With Max Difference
* 1296. Divide Array in Sets of K Consecutive Numbers
* 846. Hand of Straights
* Minimum Number of Arrows to Burst Balloons
* Interval Scheduling of Job
* */

// Practice: https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/

import java.util.Arrays;

public class MaxDisjointSubsetWithMaxDiff {

    public int maxPalette(int[] nums, int paletteSize, int threshold) {

        Arrays.sort(nums);

        int left = 0;
        int right = 0;
        int count = 0;

       // start at the left, expand the window to the right until the rule breaks.
        while(right < nums.length){
            int difference = nums[right] - nums[left];
            if(difference > threshold){ // When constraint rule breaks, shrink from the left until it’s okay again.
                left++;
                right++;
            } else {
                int windowSize = right - left + 1;
                if(windowSize == paletteSize){
                    count++;
                    left = right+1;
                    right = left;
                } else{
                    right++;
                }
            }
        }
        return count;
    }

    public int maxPalette_v2(int[] colors, int paletteSize, int threshold) {

        Arrays.sort(colors);

        int left = 0;
        int right = 0;
        int count = 0;

        while(right < colors.length){
            int difference = colors[right] - colors[left];
            if(difference > threshold){
                left++;
                right++;
            } else {
                int windowSize = right - left + 1;
                right++;
                if(windowSize == paletteSize){
                    count++;
                    left = right;
                }
            }
        }
        return count;
    }
}
