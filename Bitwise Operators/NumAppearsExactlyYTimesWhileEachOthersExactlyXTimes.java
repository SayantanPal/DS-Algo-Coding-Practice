
public class NumAppearsExactlyYTimesWhileEachOthersExactlyXTimes {

    public int findNumOccuringExactlyYTimesWhileEachOtherExactlyXTimes(int[] A, int X, int Y) {
        int number = 0;
        // for each bit pos
        for(int bitPos = 0; bitPos < 32; bitPos++){
            int cntTotalNosWithBitPosSet = 0;
            // count how many numbers has bit pos set at that particular bit pos
            for(int j = 0; j < A.length; j++){
                // count set bit
                if( (A[j] & (1 << bitPos)) != 0 ) // if set bit
                    cntTotalNosWithBitPosSet++; // count
            }
            // if set bit at that bit pos follows the pattern
            // then set bit at that position contributes towards formation of that number
            if(cntTotalNosWithBitPosSet % X == Y){
                number |= (1 << bitPos);
            }
        }
        return number;
    }
}
