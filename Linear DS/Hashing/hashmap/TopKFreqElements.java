package hashmap;
import java.util.*;

// Link: https://leetcode.com/problems/top-k-frequent-elements/
// link: https://www.geeksforgeeks.org/problems/subarrays-with-sum-k/1
public class TopKFreqElements {

    // NOTE: BELOW ONLY WORKS IF ORDERING OF ELEMENTS HAVING SAME FREQ DOES NOT MATTER
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

    // NOTE: IF SORTED ORDERING OF ELEMENTS HAVING SAME FREQ DO MATTER, THEN
    public ArrayList<Integer> topKFreq_withSortedElem(int[] arr, int k) {
        // Code here

        Map<Integer, Integer> freqLookUp = new HashMap<>();

        // num: freq => 1:1
        for(int i = 0; i<arr.length; i++){
            freqLookUp.put(arr[i], freqLookUp.getOrDefault(arr[i], 0) + 1);
        }


        @SuppressWarnings("unchecked")
        Set<Integer>[] freqBucket = new TreeSet[arr.length + 1];

        // freq: num => 1 : n
        for(int freq: freqLookUp.keySet()){
            if(freqBucket[freqLookUp.get(freq)] == null){
                freqBucket[freqLookUp.get(freq)] = new TreeSet<>((a,b) -> (b - a));
            }
            freqBucket[freqLookUp.get(freq)].add(freq);
        }

        ArrayList<Integer> result = new ArrayList<>();

        int counter = 0;

        for(int i = freqBucket.length - 1; i >= 0; i--){
            if(freqBucket[i] != null){
                for(int elem: freqBucket[i]){
                    result.add(elem);
                    counter++;
                    if(counter == k) break;
                }
            }
            if(counter == k) break;
        }

        return result;

    }

}
