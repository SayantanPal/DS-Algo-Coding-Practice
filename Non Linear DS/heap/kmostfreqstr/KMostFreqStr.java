package heap.kmostfreqstr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Pair {
    String str;
    int freq;

    public Pair(String str, int freq) {
        this.str = str;
        this.freq = freq;
    }
}

class Pair2 {
    String str;
    int freq;

    public Pair2(String str, int freq) {
        this.str = str;
        this.freq = freq;
    }
}

public class KMostFreqStr {

    public ArrayList<String> k_most_frequent_strings_max_heap(ArrayList<String> strs, int k) {
        // We use a HashMap to count the frequency of each string.
        Map<String, Integer> freqs = new HashMap<>();
        for (String s : strs) {
            freqs.put(s, freqs.getOrDefault(s, 0) + 1);
        }
        // Create a max heap using a custom comparator.
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> {
            // Prioritize strings with higher frequencies.
            if (a.freq != b.freq) {
                return b.freq - a.freq;
            }
            // If frequencies are equal, prioritize lexicographically smaller strings.
            return a.str.compareTo(b.str);
        });
        // Add all string-frequency pairs to the max heap.
        for (Map.Entry<String, Integer> entry : freqs.entrySet()) {
            maxHeap.offer(new Pair(entry.getKey(), entry.getValue()));
        }
        // Pop the most frequent strings off the heap 'k' times.
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            result.add(maxHeap.poll().str);
        }
        return result;
    }

    public ArrayList<String> k_most_frequent_strings_min_heap(ArrayList<String> strs, int k) {
        // Count the frequency of each string.
        Map<String, Integer> freqs = new HashMap<>();
        for (String s : strs) {
            freqs.put(s, freqs.getOrDefault(s, 0) + 1);
        }
        // Min-heap with a custom comparator: lower frequencies have higher priority.
        PriorityQueue<Pair2> minHeap = new PriorityQueue<>((a, b) -> {
            // If frequencies are equal, prioritize lexicographically larger strings.
            if (a.freq == b.freq) {
                return b.str.compareTo(a.str);
            }
            return a.freq - b.freq;
        });
        // Maintain a heap of size 'k' with the most frequent strings so far.
        for (Map.Entry<String, Integer> entry : freqs.entrySet()) {
            minHeap.offer(new Pair2(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // Pop elements from the heap and reverse the result to return most frequent first.
        ArrayList<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(minHeap.poll().str);
        }
        // Reverse the list since it's a min-heap and we want the most frequent first.
        ArrayList<String> reversed = new ArrayList<>();
        for (int i = res.size() - 1; i >= 0; i--) {
            reversed.add(res.get(i));
        }
        return reversed;
    }
}
