package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/
public class IntersectionOfTwoArraysWithAndWithoutDuplicates {

    public int[] intersectWithDuplicates(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> lookUp = new HashMap<>();
        for(int i: nums1){
            lookUp.put(i, lookUp.getOrDefault(i, 0) + 1);
        }

        List<Integer> result = new ArrayList<Integer>();

        for(int i: nums2){
            if(lookUp.containsKey(i) && lookUp.get(i) > 0){
                result.add(i);
                lookUp.put(i, lookUp.get(i) - 1);
            }
        }

        int[] result2 = new int[result.size()];
        for(int i = 0; i < result2.length; i++){
            result2[i] = result.get(i);
        }
        return result2;
    }

    // since input nos are in bounded range from 0 to 1000,
    // replacing hashmap with freq array is faster
    // Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/
    public int[] intersectWithDuplicates_optimized(int[] nums1, int[] nums2) {
        int[] lookUp = new int[1001]; //hashmap<Integer, Integer> lookUp = new hashmap<>();
        for(int i: nums1){
            lookUp[i]++; // lookUp.put(i, lookUp.getOrDefault(i, 0) + 1);
        }

        List<Integer> result = new ArrayList<Integer>();

        for(int i: nums2){
            if(lookUp[i] > 0){ //  if(lookUp.containsKey(i) && lookUp.get(i) > 0){
                result.add(i);
                lookUp[i]--; // lookUp.put(i, lookUp.get(i) - 1);
            }
        }

        int[] result2 = new int[result.size()];
        for(int i = 0; i < result2.length; i++){
            result2[i] = result.get(i);
        }
        return result2;
    }

    // Link: https://leetcode.com/problems/intersection-of-two-arrays/
    public int[] intersectionWithoutDuplicates(int[] nums1, int[] nums2) {
        // Set<Integer> lookUp = new HashSet<>();
        boolean[] lookUp = new boolean[1001];
        for(int i: nums1){
            lookUp[i] = true;// lookUp.add(i);
        }

        List<Integer> result = new ArrayList<>();
        for(int i: nums2){
            if(lookUp[i]){//if(lookUp.contains(i)){
                result.add(i);
                lookUp[i] = false;// lookUp.remove(i);
            }
        }

        int[] result2 = new int[result.size()];
        for(int i = 0; i < result2.length; i++){
            result2[i] = result.get(i);
        }

        return result2; // return result.stream().mapToInt(i -> i).toArray();
    }
}
