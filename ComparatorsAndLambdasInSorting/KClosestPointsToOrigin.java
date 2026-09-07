import java.util.Arrays;
/*
* 973. K Closest Points to Origin
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

* Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.

Constraints:
1 <= k <= points.length <= 104
-104 <= xi, yi <= 104
*
* */
// Link: https://leetcode.com/problems/k-closest-points-to-origin/description/
public class KClosestPointsToOrigin {
    public long calculateEucideanDist(int x1, int y1, int x2, int y2){
        return ((long)(x1 - x2) * (x1 - x2)) + ((long)(y1 - y2) * (y1 - y2));
    }
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> {
            int x1 = a[0];
            int y1 = a[1];

            int x2 = b[0];
            int y2 = b[1];
            return (int)(calculateEucideanDist(x1, y1, 0, 0) - calculateEucideanDist(x2, y2, 0, 0));
        });
        int[][] result = new int[k][2];
        for(int i = 0; i < k; i++){
            result[i][0] = points[i][0];
            result[i][1] = points[i][1];
        }
        return result;
    }
}
