package twosortedtargetsum;

// Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSumSorted {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int n = numbers.length;

        int slow = 0, fast = n - 1;
        while(slow < fast){
            if(numbers[slow] + numbers[fast] > target){
                fast--;
            }else if(numbers[slow] + numbers[fast] < target){
                slow++;
            }else{
                result[0] = slow + 1;
                result[1] = fast + 1;
                break;
            }
        }
        return result;
    }
}
