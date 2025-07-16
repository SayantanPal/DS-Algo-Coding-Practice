package basic;

import java.util.HashMap;
import java.util.Map;

// Used in caching, duplicate detection, distributed systems.
// Production Use: Deduplication of logs/messages before indexing in Elasticsearch.
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {


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
}
