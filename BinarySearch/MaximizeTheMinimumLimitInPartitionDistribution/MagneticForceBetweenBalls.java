package MaximizeTheMinimumLimitInPartitionDistribution;
/*
* 1552. Magnetic Force Between Two Balls
*
* Problem Description
In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
Given the integer array position and the integer m. Return the required force.

Example 1: Input: position = [1,2,3,4,7], m = 3
Output: 3
Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

* Example 2:
Input: position = [5,4,3,2,1,1000000000], m = 2

* Output: 999999999
Explanation: We can use baskets 1 and 1000000000.


Constraints:
n == position.length
2 <= n <= 105
1 <= position[i] <= 109
All integers in position are distinct.
2 <= m <= position.length
*
* */
import java.util.Arrays;

// Link: https://leetcode.com/problems/magnetic-force-between-two-balls/description/
public class MagneticForceBetweenBalls {

    public boolean checkIfAllBallsCanBePlaced(Integer[] position, long minDistanceThresholdBetween, long totalNoNeedToBePlaced){
        long cnt = 1L;
        long previousDist = position[0];
        for(int i = 1; i < position.length; i++){
            if(position[i] - previousDist >= minDistanceThresholdBetween){
                cnt++;
                previousDist = position[i];
            }
            if(cnt == totalNoNeedToBePlaced) return true;
        }
        return false;
    }
    public int maximizeMinDistanceBetweenTwoBalls(int[] position, int m) {
        Integer[] A1 = Arrays.stream(position).boxed().toArray(Integer[]::new);
        Arrays.sort(A1);


        long minDistBetween = Long.MAX_VALUE;
        long maxDistBetween = A1[A1.length - 1] - A1[0];

        for(int i = 1; i < A1.length; i++){
            minDistBetween = Math.min(minDistBetween, A1[i] - A1[i - 1]);
        }

        long l = minDistBetween, r = maxDistBetween;
        long maxPossibleMinDist = -1;
        while(l <= r){
            long mid = l + (r - l)/2;
            if(checkIfAllBallsCanBePlaced(A1, mid, m)){
                maxPossibleMinDist = mid;
                l = mid + 1; // move right to maximise the minimum distance
            }else{
                r = mid - 1; // move left to fit in all cows
            }
        }
        return (int)maxPossibleMinDist;
    }
}
