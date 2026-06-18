package medium.ExpansionAroundIndex;



public class LengthOfLongestConsecutiveOnesWithAtMostOneSwapping {

    public int solve(String A) {
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
