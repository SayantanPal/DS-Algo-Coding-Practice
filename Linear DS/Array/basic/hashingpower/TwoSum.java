package basic.hashingpower;

import java.util.*;

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


    // Link: https://www.naukri.com/code360/problems/two-sum_839653?leftPanelTabValue=PROBLEM
    public record Pair<K, V>(K key, V value) {}
    public static ArrayList<Pair<Integer, Integer>> findAllTwoSumsAtDiffIndexContainingDuplicateElem(ArrayList<Integer> arr, int target, int n) {
        // Write your code here.
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        Map<Integer, Integer> lookUp = new HashMap<>();
        for(int i = 0; i < arr.size(); i++){
            if(lookUp.containsKey(target - arr.get(i))){
                result.add(new Pair(arr.get(i), target - arr.get(i)));
                // once current pair contributes to match finding, reduce the count of other existing pair
                if(lookUp.get(target - arr.get(i)) > 1){
                    lookUp.put(target - arr.get(i), lookUp.get(target - arr.get(i)) - 1);
                }else if(lookUp.get(target - arr.get(i)) == 1){
                    lookUp.remove(target - arr.get(i));
                }
            }else{ // once current pair contributes to match finding, also do not consider current pair anymore
                lookUp.put(arr.get(i), lookUp.getOrDefault(arr.get(i), 0) + 1);
            }
        }
        if(result.isEmpty()){
            result.add(new Pair(-1, -1));
        }
        return result;
    }

    /*
    * Given an array arr[] of integers and another integer target.
    * Determine if there exist two distinct indices such that the sum of their elements is equal to the target.
    * */
    // Link: https://www.geeksforgeeks.org/problems/key-pair5616/1
    boolean isTwoSumEqualsTargetPresent(int arr[], int target) {
        // code here
        Set<Integer> lookUp = new HashSet<>();
        for(int i = 0; i < arr.length; i++){
            if(lookUp.contains(target - arr[i])){
                return true;
            }
            lookUp.add(arr[i]);
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] result = twoSumWithDuplicates(nums, target);
        System.out.println("Indices: " + result[0] + ", " + result[1]);
    }
}
