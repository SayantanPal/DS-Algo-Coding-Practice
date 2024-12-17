package subsequences_or_subsets;

import java.util.ArrayList;

// Link: https://www.naukri.com/code360/problems/house-robber_839733?leftPanelTabValue=PROBLEM
public class HouseRobberTwo {

    public static long DPTabSpaceOptimise_v4(ArrayList<Integer> valueInHouse){

        int n = valueInHouse.size();

        long secondLast = 0;
        // if(n==0) return secondLast;

        long last = valueInHouse.get(0);
        // if(n==1) return last;


        for(int i = 1; i < n; i++){
            long maxCost = Math.max(last, secondLast + valueInHouse.get(i));
            secondLast = last;
            last = maxCost;
        }

        return last;
    }


    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.

        // if(valueInHouse.length == 0) return 0;
        // if(valueInHouse.length == 1) return valueInHouse[0];

        // ArrayList<Integer> excludingFirst = new ArrayList<>(Arrays.asList(Arrays.stream(valueInHouse).boxed().toArray(Integer[]::new)));
        // excludingFirst.remove(0);
        // ArrayList<Integer> excludingLast = new ArrayList<>(Arrays.asList(Arrays.stream(valueInHouse).boxed().toArray(Integer[]::new)));
        // excludingLast.remove(excludingLast.size() - 1);
        // return Math.max(DPTabSpaceOptimise_v4(excludingFirst), DPTabSpaceOptimise_v4(excludingLast));''

        int n = valueInHouse.length;

        ArrayList<Integer> withoutFirstHouse = new ArrayList<>();
        ArrayList<Integer> withoutLastHouse = new ArrayList<>();
        if(n==1) return valueInHouse[0];

        for(int i = 0 ; i < n ; i++) {
            if(i!=0) withoutFirstHouse.add(valueInHouse[i]);
            if(i!=n-1) withoutLastHouse.add(valueInHouse[i]);
        }
        return Math.max(DPTabSpaceOptimise_v4(withoutFirstHouse),DPTabSpaceOptimise_v4(withoutLastHouse));
    }
}
