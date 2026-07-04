package medium.MinMax;

import java.util.ArrayList;
import java.util.Collections;

public class TwoStrictlyGreaterElemDivBy7 {

    public int solve(ArrayList<Integer> A){
        if(A.size() < 2) return 0;
        Collections.sort(A, (x, y) -> (y - x)); //Arrays.sort(Arrays.stream( new int[]{} ).boxed().toArray( Integer[]::new ), Collections.reverseOrder());
        int count = 0;
        for(int i = 0; i < A.size(); i++){
            if(A.get(i) != A.get(i - 1) && A.get(i) % 7 == 0){
                count++;
            }
        }
        return count;
    }
}
