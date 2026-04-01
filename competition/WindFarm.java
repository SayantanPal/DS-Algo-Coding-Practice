import java.util.*;

// Greedy + Math/Geometry
/*
* Problem: Wind Farms — Optimal Control Center Placement

  An energy company needs to place a control center to manage a distributed wind farm.
  Your task is to determine the optimal location for the control center that minimizes the total connection cost.

  For each wind farm i:
  - The farm is located at coordinates (x[i], y[i])
  - The control center is at coordinates (cx, cy)
  - The distance is calculated as |x[i] - cx| + |y[i] - cy| (Manhattan distance)
  - The connection cost is premium[i] * distance

  The control center can be placed at any integer coordinate point.

  ---
  Function Signature:
  public static int windFarms(List<Integer> premium, List<Integer> x, List<Integer> y)

  Parameters:
  1. INTEGER_ARRAY premium — the distance multiplier for the cost to connect
  2. INTEGER_ARRAY x — x coordinates of the wind farms
  3. INTEGER_ARRAY y — y coordinates of the wind farms

  Returns: int — the minimum cost to connect the wind farms

  Constraints:
  - 1 ≤ n ≤ 10⁵
  - 1 ≤ x[i], y[i] ≤ 10⁴
  - 1 ≤ premium[i] ≤ 50

  ---
  Example:
  x = [1, 3, 2, 4]
  y = [1, 2, 3, 4]
  premium = [1, 3, 2, 4]

  The optimal location is at (3, 3), resulting in:


  ┌──────┬──────────┬─────────────────┬─────────┬─────────┐
  │ Farm │ Location │    Distance     │ Premium │  Cost   │
  ├──────┼──────────┼─────────────────┼─────────┼─────────┤
  │ 0    │ (1,1)    │ |1-3|+|1-3| = 4 │ 1       │ 1*4 = 4 │
  ├──────┼──────────┼─────────────────┼─────────┼─────────┤
  │ 1    │ (3,2)    │ |3-3|+|2-3| = 1 │ 3       │ 3*1 = 3 │
  ├──────┼──────────┼─────────────────┼─────────┼─────────┤
  │ 2    │ (2,3)    │ |2-3|+|3-3| = 1 │ 2       │ 2*1 = 2 │
  ├──────┼──────────┼─────────────────┼─────────┼─────────┤
  │ 3    │ (4,4)    │ |4-3|+|4-3| = 2 │ 4       │ 4*2 = 8 │
  └──────┴──────────┴─────────────────┴─────────┴─────────┘

  Total connection cost = 17 (minimum possible)

*   ---
  Test Cases:

  ┌─────┬──────────────────────────────────────┬─────────────────┐
  │ TC  │                Input                 │ Expected Output │
  ├─────┼──────────────────────────────────────┼─────────────────┤
  │ 1   │ n=3, p=[1,1,1], x=[2,3,5], y=[1,1,1] │ 3               │
  ├─────┼──────────────────────────────────────┼─────────────────┤
  │ 2   │ n=2, p=[1,10], x=[1,2], y=[1,2]      │ 2               │
  ├─────┼──────────────────────────────────────┼─────────────────┤
  │ 3   │ n=3, p=[1,1,1], x=[5,2,3], y=[3,4,7] │ 7               │
  ├─────┼──────────────────────────────────────┼─────────────────┤
  │ 4   │ n=20, large input                    │ 2608479         │
  └─────┴──────────────────────────────────────┴─────────────────┘

*
* */

/*
*   Time: O(n log n) for sorting. Space: O(n).

  The pattern to remember:
  1. Manhattan distance → split x and y independently
  2. Minimize weighted absolute deviation → weighted median
  3. Weighted median = sort by coordinate, walk until cumulative weight ≥ half of total
  *
The one rule to memorize:
  ▎ Minimize sum of absolute differences → median
  ▎ Minimize sum of weighted absolute differences → weighted median
  ▎ Manhattan distance in 2D → split into x and y, solve independently
*
*
* */

/*
* The core patterns this problem tests:

  1. Weighted Median / Minimum Distance
  - LC #296 — Best Meeting Point (Hard) — exact same pattern. Grid with people, find meeting point minimizing total Manhattan distance. Split x and y, find median.
  - LC #462 — Minimum Moves to Equal Array Elements II (Medium) — find a number to minimize total absolute differences. Direct median application.

  2. Manhattan Distance + Median Insight
  - LC #2033 — Minimum Operations to Make a Uni-Value Grid (Medium) — median minimizes absolute deviation
  - LC #2448 — Minimum Cost to Make Array Equal (Hard) — almost identical to your Walmart problem. Weighted cost minimization. Weighted median.
  - LC #2607 — Make K-Subarray Sums Equal (Medium) — cyclic median application

  3. Foundation Problems (build intuition)
  - LC #215 — Kth Largest Element (Medium) — finding median efficiently
  - LC #56 — Merge Intervals (Medium) — you've done this, interval thinking
  - LC #1478 — Allocate Mailboxes (Hard) — DP + median for facility placement

  Priority order for you:
  1. LC #462 — solve this first, simplest median problem
  2. LC #2448 — this IS the Walmart problem essentially
  3. LC #296 — 2D version, exact same split x/y technique
*
*
* */
public class WindFarm {

    //   Time: O(n log n) for sorting. Space: O(n).
    public static int windFarms(List<Integer> premium, List<Integer> x, List<Integer> y) {
        int n = premium.size();

        // Solve x and y independently
        return minCostForAxis(premium, x) + minCostForAxis(premium, y);
    }

    private static int minCostForAxis(List<Integer> weights, List<Integer> coords) {
        int n = weights.size();

        // Pair (coordinate, weight) and sort by coordinate
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = coords.get(i);
            pairs[i][1] = weights.get(i);
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        // Find weighted median: walk until cumulative weight >= totalWeight / 2
        int totalWeight = 0;
        for (int[] p : pairs) totalWeight += p[1];

        int cumulative = 0;
        int median = pairs[0][0];
        for (int[] p : pairs) {
            cumulative += p[1];
            if (cumulative * 2 >= totalWeight) {
                median = p[0];
                break;
            }
        }

        // Compute cost at the median
        int cost = 0;
        for (int[] p : pairs) {
            cost += p[1] * Math.abs(p[0] - median);
        }
        return cost;
    }

}
