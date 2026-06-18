package medium.ExpansionAroundIndex;



public class LengthOfLongestConsecutiveOnesWithAtMostOneReplace {

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
                maxLen = Math.max(maxLen, l + r + 1); // then swap center 0 from the extra pool of '1'

            }
        }
        return maxLen;
    }
}
