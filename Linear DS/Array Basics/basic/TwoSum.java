package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Used in caching, duplicate detection, distributed systems.
// Production Use: Deduplication of logs/messages before indexing in Elasticsearch.
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){

            if(map.containsKey(target - nums[i])){
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }

            // always update map after checking if other element in pair exists or not
            // else if one element, say is 3 and target is 6,
            // then it will find 6-3 = 3 already present if map updated previously
            map.put(nums[i], i);

        }
        return new int[]{-1, -1};
    }

    public static int[] twoSumWithDuplicates(int[] nums, int target) {
        HashMap<Integer, ArrayList<Integer>> countOccurance = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            ArrayList<Integer> indexes = countOccurance.getOrDefault(nums[i], new ArrayList<Integer>());
            indexes.add(i);
            countOccurance.put(nums[i], indexes);
        }

        for(int a: nums){
            int b = target - a;
            if(countOccurance.containsKey(b)){
                if(a == b){
                    ArrayList<Integer> indexes = countOccurance.get(b);
                    if(indexes.size() > 1){
                        return new int[]{indexes.get(0), indexes.get(1)};
                    }
                } else {
                    return new int[]{countOccurance.get(a).getFirst(), countOccurance.get(b).getFirst()};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] result = twoSumWithDuplicates(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }
}
