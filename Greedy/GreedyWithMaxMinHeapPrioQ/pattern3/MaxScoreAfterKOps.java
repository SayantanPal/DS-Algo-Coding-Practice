package GreedyWithMaxMinHeapPrioQ.pattern3;

import java.util.Comparator;
import java.util.PriorityQueue;

// Link: https://leetcode.com/problems/maximal-score-after-applying-k-operations/?source=submission-noac
public class MaxScoreAfterKOps {
    public long maxKelements(int[] nums, int k) {

        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0; i < nums.length; i++)
            pq.offer(nums[i]*1L);

        long maxScore = 0;
        while(!pq.isEmpty() && k-->0){
            long max = pq.poll();
            maxScore += max;
            pq.offer((long)Math.ceil(max/3d));
        }
        return maxScore;
    }
}
