package prodarraywithoutcurrelem;

import java.util.Arrays;

// Link: https://leetcode.com/problems/product-of-array-except-self/description/
public class ProdOfArrayExceptCurrentIndex {

    public int[] productExceptSelf(int[] A) {
        int n = A.length;
        int[] prefixProd = new int[n];
        int[] suffixProd = new int[n];
        prefixProd[0] = 1;
        suffixProd[n - 1] = 1;
        for(int i = 1; i < n; i++){
            prefixProd[i] = prefixProd[i - 1] * A[i - 1];
            suffixProd[n - 1 - i] = suffixProd[n - i] * A[n - i];
        }
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            result[i] = prefixProd[i] * suffixProd[i];
        }
        return result;
    }

    public int[] productExceptSelf_v2(int[] nums) {
        int n = nums.length;
        if(n == 0) return nums;

        int prefixSuffix[] = new int[n];
        Arrays.fill(prefixSuffix, 1);
        int prefix = 1;
        int suffix = 1;

        for(int i = 0; i < n; i++){
            prefixSuffix[i] *= prefix;
            prefix *= nums[i];

            prefixSuffix[n-1-i] *= suffix;
            suffix *= nums[n-1-i];
        }

        return prefixSuffix;
    }
}
