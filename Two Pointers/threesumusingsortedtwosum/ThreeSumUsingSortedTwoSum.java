package threesumusingsortedtwosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Why not using two sum unsorted with hashset for every c?
// The killer is duplicate handling — without sorted order, you can't skip duplicates with simple pointer logic.
// You end up needing Set<List<Integer>> which hashes entire lists → much slower constant factor
// Or you sort each triplet before adding to the Set → you're sorting anyway
//  n log n you "save" costs you 50x in constant factor overhead from hashing and duplicate management


// Link: https://leetcode.com/problems/3sum/
public class ThreeSumUsingSortedTwoSum {

    public List<List<Integer>> calculateThreeSum(int[] nums, int c){
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        int left = c + 1;
        int right = n - 1;
        while(left < right){
            if(nums[left] + nums[right] + nums[c] < 0){
                left++;
            }else if(nums[left] + nums[right] + nums[c] > 0){
                right--;
            }else{
                result.add(Arrays.asList(nums[c], nums[left], nums[right]));
                // at least once first time, pointer traversal needed;
                // else infinite loop since same condition will keep on matching

                // left++;
                // right--;
                // while(left < right && nums[left] == nums[left - 1]) left++;
                // while(left < right && nums[right] == nums[right + 1]) right--;

                do{
                    left++;
                }while(left < right && nums[left] == nums[left - 1]);

                do{
                    right--;
                }while(left < right && nums[right] == nums[right + 1]);
            }
        }
        return result;
    }

    //  O(n log n) sort is negligible — it's dominated by O(n²) anyway. n log n + n² ≈ n²
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        // if(n < 3) return new ArrayList<>(); // already handled by given constraints
        Arrays.sort(nums); // O(n log n) - sorting mandatory for two sum using pointer
        List<List<Integer>> results = new ArrayList<>();

        for(int c = 0; c < n - 2; c++){ // O(n²)
            if(c > 0 && nums[c] == nums[c - 1]) continue; // skip duplicates using pointers - better performance than HashSet<List<Integer>>
            List<List<Integer>> answer = calculateThreeSum(nums, c);
            results.addAll(answer);
        }
        return results;
    }
}
