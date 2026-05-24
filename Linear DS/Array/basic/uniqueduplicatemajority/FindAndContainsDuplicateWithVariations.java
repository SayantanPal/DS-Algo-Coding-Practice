package basic.uniqueduplicatemajority;

/*
* Given an array arr[] of size n, filled with numbers from 1 to n-1 in random order. The array has only one repetitive element. Your task is to find the repetitive element.
Note: It is guaranteed that there is a repeating element present in the array.
* */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAndContainsDuplicateWithVariations {
    /*
    * * array of size n contains elements within range [1, n-1]
     * contsraint: which contains 1 extra duplicate number(no missing number)
     * Duplicate here means ONLY 1 repeating char(not more than 1)
    * */
    // Link: https://www.geeksforgeeks.org/problems/find-repetitive-element-from-1-to-n-1/1
    public int findDuplicate(int[] arr) {
        // code here
        int n = arr.length;

        int duplicateNo = 0;
        for(int i = 0; i < n; i++){
            duplicateNo ^= i;
            duplicateNo ^= arr[i];
        }
        return duplicateNo;
    }

    // Link: https://leetcode.com/problems/find-the-duplicate-number/
    public int findDuplicate2(int[] nums) {
        // Valid Indexes are 0 to n - 1
        // given: array of size n can contains numbers within range [1 to n]
        // in case of range: [0, n - 1], [1, n - 1] -> it is straightforward using mark visited technique
        // in case of range: [1, n] -> we need to shift the mapping by 1 step to apply concept of mark visited technique

        // any such number which is the answer(ie duplicate) or not, the numbers can act as a map for other array elements
        // so, all numbers before checking the map location or position needs to be absolute values since they can be individually be marked as a mapped element for any other element
        // only mapping makes 1 displacement minus because of shift in range
        for(int i = 0; i < nums.length; i++){
            if(nums[Math.abs(nums[i]) - 1] < 0) return Math.abs(nums[i]);
            nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
        }
        return -1;
    }

    /*
     * * when array contains random numbers, i.e., not following any range within n
     * and the numbers can be -ve as well
     * then we count on hashmap where we adjust with hash collision as well
     * */
    // Link: https://leetcode.com/problems/contains-duplicate/
    public boolean containsDuplicate3(int[] nums) {
        Set<Integer> lookUp = new HashSet<>();
        for(int num: nums){
            if(lookUp.contains(num)){
                return true;
            }
            lookUp.add(num);
        }
        return false;
    }

    // when both duplicate and missing element
    // Link: https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1
    ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        long n = arr.length;
        int actualSum = 0;
        for(int i = 0; i < n; i++){
            actualSum += arr[i];
        }
        int duplicateNo = 0;
        for(int i = 0; i < n; i++){
            if(arr[Math.abs(arr[i]) - 1] < 0){
                duplicateNo = Math.abs(arr[i]);
                break;
            }
            arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
        }

        long sum = n*(n+1) / 2;
        // System.out.println(sum);
        // System.out.println(actualSum);
        // System.out.println(duplicateNo);
        int missing =  (int)(sum - (actualSum - duplicateNo));
        return new ArrayList<>(List.of(duplicateNo, missing));
    }
}
