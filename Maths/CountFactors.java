import java.util.ArrayList;
import java.util.Collections;

public class CountFactors {

    public ArrayList<Integer> getDivisors(int n) {
        // code here
        ArrayList<Integer> factors = new ArrayList<Integer>();
        int i = 1;
        for(; i * i < n; i++){
            if(n % i == 0){ // factors till less than square of n occurs in pairs
                factors.add(i);
                factors.add(n/i);
            }
        }
        // edge case: for perfect square number, its square root becomes its only factor without pair
        if(i == n/i){
            factors.add(i);
        }
        // after crossing square root of the number, factor pairs (i, N/i) repeats as (N/i, i)
        Collections.sort(factors);
        return factors;
    }
}
