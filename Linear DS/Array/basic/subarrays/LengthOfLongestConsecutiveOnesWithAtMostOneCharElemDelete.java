package basic.subarrays;

// Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
public class LengthOfLongestConsecutiveOnesWithAtMostOneCharElemDelete {

    public int findMaxLenOfConsecutiveOnesWithAtMostOneReplace_v2(int[] nums) {
        int maxLen = 0;
        boolean isAnyUnsetBitEncountered = false;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                isAnyUnsetBitEncountered = true;
                int l = i - 1;
                int r = i + 1;
                while(l >= 0 && nums[l]==1){
                    l--;
                }
                l++; // IMP - because you have one stepped down in series of 1 in the loop
                while(r < nums.length && nums[r]==1){
                    r++;
                }
                r--; // IMP - because you have one stepped up in series of 1 in the loop
                maxLen = Math.max(maxLen, r - l);
            }
        }
        // if no unset bit encountered and max length remains 0 - that means all are 1's in the array,
        // then ans becomes only deleting one element that is set bit 1
        // optionally you could also have calculated upfront if the length of array equals no of set bits - that also proves no unset bits
        // and you can return the same len - 1 in that case
        return !isAnyUnsetBitEncountered && maxLen == 0 ? nums.length - 1 : maxLen;
    }

    public int findMaxLenOfConsecutiveOnesWithAtMostOneReplace(String A) {
        int noOfOnes = 0;
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i) == '1'){
                noOfOnes++;
            }
        }
        if(noOfOnes == A.length()){
            return noOfOnes;
        }
        int maxLen = 0;
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i) == '0'){ // centre for element 0

                // Move extreme right till you find seq of 1
                // '0' ->->-> r = '1'
                int j = i+1;
                int r = 0;
                while(j < A.length() && A.charAt(j) == '1'){
                    j++;
                    r++;
                }

                // Move extreme left till you find seq of 1
                // l = '1' <-<-<- '0'
                j = i-1;
                int l = 0;
                while(j >=0 && A.charAt(j) == '1'){
                    j--;
                    l++;
                }

                // might be uneven radius
                // window: l = '1' <-<-<- '0' ->->-> r = '1'

                // we replace only one middle '0' with '1'
                maxLen = Math.max(maxLen, l + r); // then delete center 0 from the extra pool of '1'

            }
        }
        return maxLen;
    }
}
