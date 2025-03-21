package GreedyWithMaxMinHeapPrioQ;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// Link: https://leetcode.com/problems/distant-barcodes/?envType=problem-list-v2&envId=heap-priority-queue
public class DistantBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;

        // Initialise a Max Heap which stores elements in decreasing order of frequencies and add all the elements to max heap.
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);// max heap

        Map<Integer, Integer> frequency = new HashMap<>();

        for(int num: barcodes){
            // if(frequency.containsKey(num)){
            //     frequency.put(num, frequency.get(num) + 1);
            // } else{
            //     frequency.put(num, 1);
            // }
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // Initialise a HashMap to store elements and their frequency.
        for(int num: frequency.keySet()){
            pq.offer(new int[]{num, frequency.get(num)});
        }

        System.out.println(Arrays.deepToString(pq.toArray()));

        int[] result = new int[n];
        int i = 0;
        // while(!pq.isEmpty()){
        //     int[] maxFreqElem = pq.poll();
        //     boolean hasFreq1 = false;
        //     if(maxFreqElem[1] > 0){
        //         result[i++] = maxFreqElem[0];
        //         maxFreqElem[1]--;
        //         hasFreq1 = true;
        //     }
        //     boolean hasFreq2 = false;
        //     if(pq.isEmpty()) break;
        //     int[] nextFreqElem = pq.poll();
        //     if(nextFreqElem[1] > 0){
        //         result[i++] = nextFreqElem[0];
        //         nextFreqElem[1]--;
        //         hasFreq2 = true;
        //     }
        //     if(hasFreq1) pq.offer(maxFreqElem);
        //     if(hasFreq2) pq.offer(nextFreqElem);
        // }

        // return result;
        while(!pq.isEmpty()){ // pop elements till max heap is not empty and return the resultant array.
            // Elements with higher frequencies should be filled in the resultant array.
            int[] maxFreqElem = pq.remove(); // pq.poll();

            //to fill alternatively, start with index 0 and fill at even indices.
            // If at any time, index exceeds the length of resultant array, start filling from index 1 and subsequent odd positions
            while(maxFreqElem[1]-- > 0){
                if(i>=n) i = 1;
                result[i] = maxFreqElem[0];
                i+=2;
            }
            // in 1st pass inner while say, it will be 1, _, 1, _, 1, _, 1, _, 1, _, 1 (1's are filled starting from index 0)
            // in 2nd pass inner while, it will be     1, 2, 1, 2, 1, 2, 1, _, 1, _, 1 (2's are filled starting from index 1)
            // in 3rd pass inner while, it will be     1, 2, 1, 2, 1, 2, 1, 3, 1, 3, 1 (3's are filled starting till where filling from index 1 was left in earlier pass)
        }

        return result;
    }
}
