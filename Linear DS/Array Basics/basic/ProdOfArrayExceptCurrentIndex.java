package basic;

import java.util.Arrays;

// Link: https://leetcode.com/problems/product-of-array-except-self/
public class ProdOfArrayExceptCurrentIndex {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        if(n == 0) return nums;

        int prefixSuffix[] = new int[n];
        Arrays.fill(prefixSuffix, 1);
        int prefix = 1;
        int suffix = 1;

        for(int i = 0; i < n; i++){
            prefixSuffix[i] *= prefix;
            prefixSuffix[n-1-i] *= suffix;
            prefix *= nums[i];
            suffix *= nums[n-1-i];
        }

        return prefixSuffix;
    }
}
