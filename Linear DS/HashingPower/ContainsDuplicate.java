import java.util.HashSet;
import java.util.Set;

// Link: https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> lookUp = new HashSet<>();
        for(int num: nums){
            if(lookUp.contains(num)){
                return true;
            }
            lookUp.add(num);
        }
        return false;
    }
}
