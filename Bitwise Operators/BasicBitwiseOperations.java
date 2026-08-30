public class BasicBitwiseOperations {

    public int setBitPosInNumZero(int bitPos){
        return (1 << bitPos);
    }

    public int setBitPossInNumZero(int bitPos1, int bitPos2){
        return (1 << bitPos1) | (1 << bitPos2);
    }

    // Only unset if set, else leave unset
    // at bitPos, if 1 -> then 0
    // use toggle selectively for 1 -> 0 and leave 0 as it is
    public int unsetBitPos(int num, int bitPos){
        return (num & (1 << bitPos)) != 0 ? (num ^ (1 << bitPos)) : num;
    }

    // unset if set, set if unset
    // at bitPos, 1 -> 0 , 0 -> 1
    public int toggleBitPos(int num, int bitPos){
        return (num ^ (1 << bitPos));
    }

    public boolean isOdd(int num){
        return (num & (1 << 0)) == 1; // simplified: (num & 1) == 1
    }

    public int unsetTillBitsFromLSB(int num, int totalBitsFromLSB){
        for(int bitPos = 0; bitPos < totalBitsFromLSB; bitPos++){
            num = unsetBitPos(num, bitPos);
        }
        return num;
    }

    // TC = O(N + log(2)(Max_Elem))
    public int getMaxPossibleMSBForAllArrElem(int[] A){
        int maxElem = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            maxElem = Math.max(maxElem, A[i]);
        }
        return (int)Math.floor(Math.log(maxElem)/Math.log(2)) + 1;
    }
}
