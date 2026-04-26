from typing import List
from collections import Counter
import heapq

class Pair:
   def __init__(self, str, freq):
       self.str = str
       self.freq = freq

   # Define a custom comparator.
   def __lt__(self, other):
       # Prioritize lexicographical order for strings with equal frequencies.
       if self.freq == other.freq:
           return self.str < other.str
       # Otherwise, prioritize strings with higher frequencies.
       return self.freq > other.freq

class Pair2:
    def __init__(self, str, freq):
        self.str = str
        self.freq = freq
    # Since this is a min-heap comparator, we can use the same comparator as the one
    # used in the max-heap, but reversing the inequality signs to invert the priority.
    def __lt__(self, other):
        if self.freq == other.freq:
            return self.str > other.str
        return self.freq < other.freq

def k_most_frequent_strings_max_heap(strs: List[str], k: int) -> List[str]:
   # We use 'Counter' to create a hash map that counts the frequency of each string.
   freqs = Counter(strs)
   # Create the max heap by performing heapify on all string-frequency pairs.
   max_heap = [Pair(str, freq) for str, freq in freqs.items()]
   heapq.heapify(max_heap)
   # Pop the most frequent string off the heap 'k' times and return these 'k' most
   # frequent strings.
   return [heapq.heappop(max_heap).str for _ in range(k)]

def k_most_frequent_strings_min_heap(strs: List[str], k: int) -> List[str]:
    freqs = Counter(strs)
    min_heap = []
    for str, freq in freqs.items():
        heapq.heappush(min_heap, Pair2(str, freq))
        # If heap size exceeds 'k', pop the lowest frequency string to ensure the heap
        # only contains the 'k' most frequent words so far.
        if len(min_heap) > k:
            heapq.heappop(min_heap)
    # Return the 'k' most frequent strings by popping the remaining 'k' strings from
    # the heap. Since we're using a min-heap, we need to reverse the result after
    # popping the elements to ensure the most frequent strings are listed first.
    res = [heapq.heappop(min_heap).str for _ in range(k)]
    res.reverse()
    return res
