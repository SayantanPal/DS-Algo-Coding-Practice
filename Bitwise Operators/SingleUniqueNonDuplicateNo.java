// Link: https://leetcode.com/problems/single-number/
// concept: a^a = 0 while a^0 = a
// same element nullifies out in bitwise XOR
public class SingleUniqueNonDuplicateNo {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num: nums){
            result ^= num;
        }
        return result;
    }

    public int singleNumberAmongTriples(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++){
            int cntSetBits = 0;
            for(int j = 0; j < nums.length; j++){
                if( (nums[j] & (1 << i)) != 0 ) cntSetBits++;
            }
            if(cntSetBits % 3 == 1){
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
