package GreedyWithMaxMinHeapPrioQ;

import java.util.Comparator;
import java.util.PriorityQueue;

// Given a set of variables on items in a store,
//find the minimum cost to grab m items from the store.
//
//The quanity of each item is infinite and
//Cost of an item is given by the formula:
//cost[i] = a[i] + (j-1)*b[i];
//where, a[] → Base cost for each item.
//	b[] → Incremental cost increase when the same item is picked again.
//1<=j<=n where j is the count of that distinct item.
//
//NOTE : Here cost changes if same item is picked up again.
//
//
//Constraints:
//a is a list of size n
//b is a list of size n
//m is a number with bound till 10^5
//


public class MinCostToBuyMProducts {

    // Method to calculate the next cost of an item
    static int cost(int[] a, int[] b, int i, int j) {
        return a[i] + (j - 1) * b[i];
    }

    public static int solution(int[] a, int[] b, int m) {
        // Priority queue to store (cost, index, count)
        PriorityQueue<int[]> pq = new PriorityQueue<>(
//                (x, y) -> Integer.compare(x[0], y[0])
                Comparator.comparingInt(x -> x[0])
        );

        // Initialize the heap with the base costs
        for (int i = 0; i < a.length; i++) {
            pq.offer(new int[]{a[i], i, 1});
        }

        int totalCost = 0;

        // Greedy selection for m items
        for (int i = 0; i < m; i++) {
            // Extract the minimum cost element
            int[] current = pq.poll();
            int currentCost = current[0];
            int index = current[1];
            int count = current[2];

            // Add the cost to the total
            totalCost += currentCost;

            // Calculate the next cost for the same item
            int nextCost = cost(a, b, index, count + 1);

            // Push the updated cost back into the heap
            pq.offer(new int[]{nextCost, index, count + 1});
        }
        return totalCost;
    }
}
