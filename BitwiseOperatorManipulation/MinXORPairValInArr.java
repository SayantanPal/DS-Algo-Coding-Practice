import java.util.Arrays;

public class MinXORPairValInArr {

    public int getMaxPossibleMSBForAllArrElem(int[] A){
        int maxElem = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            maxElem = Math.max(maxElem, A[i]);
        }
        return (int)Math.floor(Math.log(maxElem)/Math.log(2)) + 1;
    }

    public int findMinXor(int[] A) {
        // Min Xor Value means maximising ones from LSB(right) to MSB(left)

        int answer = 0;
        for(int bitPos = 0; bitPos < getMaxPossibleMSBForAllArrElem(A); bitPos++){ // min XOR -> start from LSB to MSB
            // We are interested to know how many bits are set
            int cntOfOnes = 0;

            for(int j = 0; j < A.length; j++){
                if( (A[j] & (1 << bitPos)) != 0 ){
                    cntOfOnes++;
                }
            }


            // maximise paired set bits on right and keep on narrowing down set bit towards left

            // we donot need to set 0; only we need to set 1 in answer bits
            // to get a 1 at bit pos:
            // cond for 1 -> if cntOfOnes is odd ie cntOfOnes % 2 != 0
            // because all zeros irrespective of even or odd count can never contribute to 1 on xor operation

            // when cntOfOnes is odd
            if((cntOfOnes % 2 != 0)){
                answer |= (1 << bitPos);

                // eliminate zero containing element for next scan itr
                for(int j = 0; j < A.length; j++ ){
                    if( (A[j] & (1 << bitPos)) == 0 ){ // if bit is unset or 0
                        A[j] = 0;
                    }
                }
            }
        }
        return answer;
    }

    public int findMinXor_v2(int[] A) {
        Arrays.sort(A);
        int minXor = Integer.MAX_VALUE;
        for(int i = 0; i < A.length - 1; i++){
            minXor = Math.min(minXor, A[i] ^ A[i + 1]);
        }
        return minXor;
    }
}
