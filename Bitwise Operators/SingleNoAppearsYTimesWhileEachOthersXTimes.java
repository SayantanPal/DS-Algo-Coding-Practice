
public class SingleNoAppearsYTimesWhileEachOthersXTimes {

    public int singleNumberOccuringYTimesWhileEachOtherXTimes(final int[] A, int X, int Y) {
        int ans = 0;
        for(int i = 0; i < 32; i++){
            int cntSetBits = 0;
            for(int j = 0; j < A.length; j++){
                if( (A[j] & (1 << i)) != 0 ) cntSetBits++;
            }
            if(cntSetBits % X == Y){
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
