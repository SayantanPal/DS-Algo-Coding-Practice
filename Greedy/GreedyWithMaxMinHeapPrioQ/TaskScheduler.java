package GreedyWithMaxMinHeapPrioQ;

import java.util.Collections;
import java.util.PriorityQueue;

// Link: https://leetcode.com/problems/task-scheduler/
public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {

        int[] freq = new int[26];
        for(char c: tasks){
            freq[c - 'A']++;
        }

        // Create Max Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int f: freq){
            if(f > 0)
                pq.add(f);
        }

        int time = 0;
        int batchCount = 0;
        while(true){
            int cycle = n + 1; // max constant distinct members that needs to be picked up

            int taskCount = 0;
            boolean isBatchFormed = true;
            PriorityQueue<Integer> store = new PriorityQueue<>(Collections.reverseOrder());
            for(int i = 1; i <= cycle; i++){
                if(pq.isEmpty()){
                    isBatchFormed = false;
                    break;
                }
                int current = pq.poll();
                if(current - 1 > 0){
                    store.add(current - 1);
                }
                taskCount++;// taskcount increases irrespective of if members available or not since empty 0 members can be filled with idle
            }
            System.out.println(taskCount);
            // even if(pq.isEmpty) consider for the members which could be picked up, so dont break
            pq.addAll(store);
            time += (pq.isEmpty() ? taskCount : n + 1);
            if(isBatchFormed){
                batchCount++;
            }
            if(pq.isEmpty()) break;
        }

        return time;
    }
}
