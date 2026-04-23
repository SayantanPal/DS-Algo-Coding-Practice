import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> lookUp = new HashSet<>();
        for(int i: nums1){
            lookUp.add(i);
        }

        List<Integer> result = new ArrayList<>();
        for(int i: nums2){
            if(lookUp.contains(i)){
                result.add(i);
                lookUp.remove(i);
            }
        }

        int[] result2 = new int[result.size()];
        for(int i = 0; i < result2.length; i++){
            result2[i] = result.get(i);
        }

        return result2; // return result.stream().mapToInt(i -> i).toArray();
    }

    // ONLY IF range is bounded, say from 0 to some 10^9 - 1
    public int[] intersection_v2(int[] nums1, int[] nums2) {
        boolean[] lookUp = new boolean[1001]; // Set<Integer> lookUp = new HashSet<>();
        for(int i: nums1){
            lookUp[i] = true; // lookUp.add(i);
        }

        List<Integer> result = new ArrayList<>();
        for(int i: nums2){
            if(lookUp[i]){ // if(lookUp.contains(i)){
                result.add(i);
                lookUp[i] = false; // lookUp.remove(i);
            }
        }

        int[] result2 = new int[result.size()];
        for(int i = 0; i < result2.length; i++){
            result2[i] = result.get(i);
        }

        return result2; // return result.stream().mapToInt(i -> i).toArray();
    }
}
