package advanced.missingandduplicatesanduniquesandmajority;

import java.util.ArrayList;
import java.util.List;

// Link: https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class AllDuplicatePositiveNaturalNosAmongOnlyPositives {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicateOccurance = new ArrayList<>();
        int n = nums.length;
        for(int i = 0; i < n; i++){
            // index to which element once or twice exist to get mapped
            int mappedIndex = Math.abs(nums[i]);
            // if(mappedIndex >= 1 && mappedIndex <= n){ // all integers are in already exclusive range [1,n]
                nums[mappedIndex - 1] *= -1;
                // after double neg if a number becomes positive again after mapping, this becomes separate automatically from the indexses which never got a chance to get mapped because of no element existing for their corresponding mapping
                if(nums[mappedIndex - 1] > 0){ // mappedIndex has already appeared once earlier when it was neg; so now +ve
                    duplicateOccurance.add(mappedIndex);
                }
            // }
        }

        List<Integer> missingAndSingleOccurance = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(nums[i] < 0){ // whichever is marked as negative is the missing one
                missingAndSingleOccurance.add(i + 1);
            }
        }

        return duplicateOccurance;
    }
}
