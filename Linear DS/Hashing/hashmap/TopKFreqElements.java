package hashmap;
import java.util.*;

// Link: https://leetcode.com/problems/top-k-frequent-elements/
// link: https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
public class TopKFreqElements {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqLookUp = new HashMap<>();

        // count frequency of all elements (key: num, val: occurance/freq count)
        for(int num: nums){
            freqLookUp.put(num, freqLookUp.getOrDefault(num, 0) + 1);
        }

        // in case of all unique element in nums array in worst case,
        // count of elements = length of nums array
        List<Integer>[] freqBucket = new List[nums.length + 1]; //array of lists

        // for multiple elements having same occurance,
        // reverse lookup map (index: occurance count, val: multiple num having same occurance count)
        // which is bucket of frequency counts of multiple numbers
        for(int num: freqLookUp.keySet()){
            int occurance = freqLookUp.get(num);
            if(freqBucket[occurance] == null){
                freqBucket[occurance] = new ArrayList<>();
            }
            freqBucket[occurance].add(num);
        }

        // top k frequent elements only
        int result[] = new int[k];
        int l = 0;

        // since top K (i.e., in descending order of freq, first k elements)
        // hence, scan in reverse order pass
        for(int freqCount = freqBucket.length - 1; freqCount >= 1; freqCount--){
            // if null, then there is no number with that freq count
            if(freqBucket[freqCount] != null){
                // for each freq count bucket, there can be multiple numbers with same freq count
                for(int j = 0; j < freqBucket[freqCount].size(); j++){
                    result[l++] = freqBucket[freqCount].get(j);
                    if(l == k){
                        break;
                    }
                }
                if(l == k){
                    break;
                }
            }
        }

        return result;
    }
}
