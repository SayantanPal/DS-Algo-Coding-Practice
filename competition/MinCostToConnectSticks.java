import java.util.*;

/*
* Problem statement
You are given an array/list ‘ARR’ of ‘N’ positive integers where each element describes the length of the stick.
* You have to connect all sticks into one.
* At a time, you can join any two sticks by paying a cost of ‘X’ and ‘Y’ where ‘X’ is the length of the first stick and ‘Y’ is the length of the second stick and the new stick we get will have a length equal to (X+Y).
* You have to find the minimum cost to connect all the sticks into one.
*
*
* */

// Link: https://www.naukri.com/code360/problems/minimum-cost-to-connect-sticks_1402396?leftPanelTabValue=PROBLEM
public class MinCostToConnectSticks {
    public static long minimumCostToConnectSticks(ArrayList<Integer> arr) {
        // Wrtie your code here.
        // .peek(), .poll(), .offer()/.add(), .clear(), .size(), .isEmpty()

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap
//        for(int i = 0; i < arr.size(); i++){
//            pq.offer(arr.get(i));
//        }
        pq.addAll(arr); // add all elements of arr to pq

        int totalMinCost = 0;

        while(pq.size() != 1){
            // draw 2 min cost sticks currently
            int a = pq.poll();
            int b = pq.poll();

            // calc min cost to connect those 2 sticks
            int minCost = a + b; // equals length of new stick as well

            // track min costs one by one
            totalMinCost += minCost;

            pq.offer(minCost); // add newly connected stick to prio q
        }

        return totalMinCost;
    }
}
