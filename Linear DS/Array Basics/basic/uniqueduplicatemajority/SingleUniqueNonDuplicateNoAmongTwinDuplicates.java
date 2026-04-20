package basic.uniqueduplicatemajority;

// Link: https://leetcode.com/problems/single-number/
// concept: a^a = 0 while a^0 = a
// same element nullifies out in bitwise XOR
public class SingleUniqueNonDuplicateNoAmongTwinDuplicates {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num: nums){
            result ^= num;
        }
        return result;
    }
}
