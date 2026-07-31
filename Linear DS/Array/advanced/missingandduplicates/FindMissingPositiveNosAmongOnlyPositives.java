package advanced.missingandduplicates;

import java.util.ArrayList;
import java.util.List;

// Link: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
public class FindMissingPositiveNosAmongOnlyPositives {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            // index to which element occurs at least once get mapped
            if(nums[Math.abs(nums[i]) - 1] > 0){// this means only for first time occurance mark it; ignore on later occurance
                nums[Math.abs(nums[i]) - 1] *= -1;
            }
        }

        List<Integer> missingOccurance = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(nums[i] > 0){ // whichever remains marked as positive, their corresponding mapped index is the missing one
                missingOccurance.add(i + 1);
            }
        }

        return missingOccurance;
    }
}
