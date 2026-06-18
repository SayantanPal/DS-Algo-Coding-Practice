package medium.ExpansionAroundIndex;

/*
* Length of longest consecutive ones
*
* Given a binary string A. It is allowed to do at most one swap between any 0 and 1. Find and return the length of the longest consecutive 1’s that can be achieved.
Input Format: The only argument given is string A.
Output Format: Return the length of the longest consecutive 1’s that can be achieved.

* Constraints:
1 <= length of string <= 1000000
A contains only characters 0 and 1.
*
For Example:
Input 1:
    A = "111000"
Output 1:
    3

Input 2:
    A = "111011101"
Output 2:
    7
* */

public class LengthOfLongestConsecutiveOnesWithAtMostOneSwapping {

    public int findMaxLenOfConsecutiveOnesWithAtMostOneSwap(String A) {
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

                // if we run out of all ones while expanding,
                if(noOfOnes == l + r){
                    maxLen = Math.max(maxLen, l + r); // we swap center '0' with either of extreme l or r ends - '1'
                }else{ // if we have extra 1's remaining outside l<->r window
                    maxLen = Math.max(maxLen, l + r + 1); // then swap center 0 from the extra pool of '1'
                }
            }
        }
        return maxLen;
    }
}
