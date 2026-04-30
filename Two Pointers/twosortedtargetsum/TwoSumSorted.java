package twosortedtargetsum;

// Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
public class TwoSumSorted {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int n = numbers.length;

        int left = 0, right = n - 1;
        while(left < right){
            if(numbers[left] + numbers[right] > target){
                right--;
            }else if(numbers[left] + numbers[right] < target){
                left++;
            }else{
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }
        return result;
    }
}
