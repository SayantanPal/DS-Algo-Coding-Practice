import java.util.ArrayList;

public class MaxANDpairValInArr {

    //  AND needs both bits to be 1.
    //  So you can narrow down candidates — keep only elements with bit set at current position. Bascially, Survivors all share that bit.
    public int maxANDpair(int[] A) {
        int answer = 0;
//        int n = A.length;
        for(int i = 31; i >= 0; i--){
//            int[] temp = new int[n];
//            int tempSize = 0;
            ArrayList<Integer> temp = new ArrayList<>();

            for(int j = 0; j < A.length; j++){
                // create a new array/list with only selected elements having SET BIT at bit Pos
                if((A[j] & (1 << i)) != 0){
//                    temp[tempSize] = A[j];
//                    tempSize++;
                    temp.add(A[j]);
                }
            }

            // narrow down the array into cases where set bit is present more than pair
            // ignore formation when set bit set at a bit position remains unpaired
            if(temp.size() >= 2){//if(tempSize >= 2){
                // filter-1) eliminate other elements for which bit pos has 0
                A = temp.stream().mapToInt(e -> e).toArray(); //A = temp;
//                n = tempSize; // temp size

                // filter-2) do not consider for formation even when count of set bit at bit pos is 1 (ie less than 2)
                answer |= (1 << i);
            }
        }
        return answer;
    }
}
