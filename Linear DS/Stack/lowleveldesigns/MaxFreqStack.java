package lowleveldesigns;

import java.util.ArrayDeque;
import java.util.HashMap;

/*
* 1. Frequency only and always changes by 1 — push increments by 1, pop decrements by 1. No jumps - always linear transition
- there is always a unit step increment of frequency. frequency of any element needs to cross 1 and then only 2 and then only 3 if at all it re-appears in new pushes. frequency is linear unit step incremental increase or decrease - there is no jump.
*
*
* 2. maxFreq only decrements when its stack is empty — so we never skip a level.
- maxFreq does not gets popped with every max freq elem popped out until all max freq elements are finished and only then the next lower frequency is very obvious 1 step lower frequency as frequency increment or decrement is always linear because we can push or pop only 1 element at a time.
*
*
* 3. The "stale" copies in lower stacks are not stale at all — they represent real earlier pushes that will naturally become relevant again when maxFreq drops back down to that level.
- So the copies aren't duplicates or garbage — they're the actual history of pushes, perfectly ordered by recency within each frequency level. The design is elegant precisely because of this.
- when higher frequency of any element is removed, ordering of recency still matters for the same element with lower frequency traces as well. any element with higher frequency has crossed all the lower frequencies since there is always a unit step increment of frequency.

* */

// Link: https://leetcode.com/problems/maximum-frequency-stack/
public class MaxFreqStack {
    HashMap<Integer, ArrayDeque<Integer>> groupMap;
    HashMap<Integer, Integer> freqMap;
    int maxFreq;

    public MaxFreqStack() {
        this.groupMap = new HashMap<Integer, ArrayDeque<Integer>>();
        this.freqMap = new HashMap<>();
    }

    public void push(int val) {
        int newFreq = this.freqMap.getOrDefault(val, 0) + 1;
        maxFreq = Math.max(maxFreq, newFreq);
        this.freqMap.put(val, newFreq);
        this.groupMap.computeIfAbsent(newFreq, k -> new ArrayDeque<Integer>()).push(val);
    }

    public int pop() {
        ArrayDeque<Integer> maxFreqElemStack = this.groupMap.get(maxFreq);
        int maxFreqElem = maxFreqElemStack.pop();
        this.freqMap.put(maxFreqElem, maxFreq - 1);
        if(maxFreqElemStack.isEmpty()) maxFreq--;
        return maxFreqElem;
    }
}
