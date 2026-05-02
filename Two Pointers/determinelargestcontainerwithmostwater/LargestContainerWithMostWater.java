package determinelargestcontainerwithmostwater;

import java.util.ArrayList;


/*
* Concept - 1: We would want both the height and width to be as large as possible to have the largest container.
* Concept - 2: Varying heights are spreaded out - so we donot know how to start from maximum height at first. It’s not needed to find the container with the largest height, as the heights of the lines in the array don’t follow a clear pattern due to which largest height container is not guranteed to be placed at largest width which is len of array (ie. at two extreme ends of array - 0 and n-1).
* Concept - 3: We do know the container with the maximum width: the one starting at index 0 and ending at index n - 1.
* Concept - 4: intuition is for all possible fixed widths, we need to greedily find the optimal height pair - we will not restrict checking all possibilities but with checking all possibilites, we will make sure that we do not miss out on any optimal pair
* Concept - 5: Remember for any pair of walls, the mimimum among them limits/caps/restricts water height because water overflows through that extra height difference - so, always smaller one among the pair determines the height. So, we find the max possible left and max possible right heights for the constant width -> so that Min(max_possible_left, max_possible_right) -> most optimal height for that particular fixed width
* Concept - 6: Moving either pointer inward yields a container of shorter width, leaving height as the only determining factor for that particular shorter width.
* Concept - 7: We aim to move inward towards the center( shorter height are eliminated outside container boundary and with longer height at/inside container boundary), because irrespective of whichever pointer we move forward, the other pointer points at same wall and even if a pointer is moved to a taller line, smaller of those two pointer walls only decides the final height of water
* Concept - 8: when we get equal max height left and max height right for a constant width, we consider the area for that as well and move both pointers inward with an aim for more height within the container
* */

// Link: https://leetcode.com/problems/container-with-most-water/
public class LargestContainerWithMostWater {

    public int determineLargestContainerWithMostWater(ArrayList<Integer> heights) {
        int max_water = 0;
        int left = 0, right = heights.size() - 1;
        while (left < right) {
            // Calculate the water contained between the current pair of lines.
            int water = Math.min(heights.get(left), heights.get(right)) * (right - left);
            max_water = Math.max(max_water, water);
            // Move the pointers inward, always moving the pointer at the shorter line.
            // if both lines have the same height, move both pointers inward.
            if (heights.get(left) < heights.get(right)) {
                left++;
            } else if (heights.get(left) > heights.get(right)) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return max_water;
    }
}
