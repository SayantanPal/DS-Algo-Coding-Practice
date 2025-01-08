package all_possible_combination_of_subsequences;

// Link: https://leetcode.com/problems/target-sum/description/
public class CountSubsetsWithTargetSumTwoPlusMinus {

    public int topDownRec(int i, int[] nums, int currSum, int target){
        if(i == 0){
            if((currSum + nums[0] == target) && (currSum - nums[0] == target)){
                return 2;
            } else if((currSum + nums[0] == target) || (currSum - nums[0] == target)){
                return 1;
            } else{
                return 0;
            }
        }

        int plus = topDownRec(i - 1, nums, currSum + nums[i], target);
        int minus = topDownRec(i - 1, nums, currSum - nums[i], target);
        return plus + minus;
    }

    public int topDownRec2(int i, int[] nums, int target){
        if(i == 0){
            if((nums[0] == target) && (- nums[0] == target)){
                return 2;
            } else if((nums[0] == target) || (- nums[0] == target)){
                return 1;
            } else{
                return 0;
            }
        }

        int plus = topDownRec2(i - 1, nums, target - nums[i]);
        int minus = topDownRec2(i - 1, nums, target + nums[i]);
        return plus + minus;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        // int result = topDownRec(n - 1, nums, 0, target);
        int result = topDownRec2(n - 1, nums, target);
        return result;
    }

}
