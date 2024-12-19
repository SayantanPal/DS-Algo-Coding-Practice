package linear_subsequences_or_subsets_constant_transition;

// Link: https://www.naukri.com/code360/problems/house-robber_6211018?leftPanelTabValue=PROBLEM

// max loot starting from first house to current House = max of
// max loot starting from first house to house before prev(prev to prev) House i.e., current House Index - 2 + cost to loot current house(i.e., cost[currHouseIndex])
// max loot starting from first house to prev House i.e., current House Index - 1


public class HouseRobber {

     public static int DPRec_v1(int currHouseIndex, int[] a){

         if(currHouseIndex == 0) return a[0];
         if(currHouseIndex == 1) return Math.max(a[0], a[1]);


         return Math.max(DPRec_v1(currHouseIndex - 1, a), DPRec_v1(currHouseIndex - 2, a) + a[currHouseIndex]);
     }

     public static int DPRecMem_v2(int i, int[] a, int[] dp){

         if(i == 0) return a[0];
         if(i == 1) return Math.max(a[0], a[1]);

         if(dp[i] != -1) return dp[i];
         dp[i] = Math.max(DPRecMem_v2(i-1, a, dp), DPRecMem_v2(i-2, a, dp) + a[i]);

         return dp[i];
     }

     public static int DPTab_v3(int[] a){

         int[] dp = new int[a.length];
         dp[0] = a[0];
         dp[1] = Math.max(a[0], a[1]);

         for(int i = 2; i < dp.length; i++){
             dp[i] = Math.max(dp[i-1], dp[i-2] + a[i]);
         }

         return dp[dp.length - 1];
     }

    public static int DPTabSpaceOptimise_v4(int[] a){

        int n = a.length;

        if(n==0) return 0;

        int secondLast = a[0];
        if(n==1) return secondLast;

        int last = Math.max(a[0], a[1]);
        // if(n == 2) return last ;

        for(int i = 2; i < n; i++){
            int maxCost = Math.max(last, secondLast + a[i]);
            secondLast = last;
            last = maxCost;
        }

        return last;
    }

    public static int DPTabSpaceOptimise_v4_1(int[] valueInHouse){

        int n = valueInHouse.length;

        int secondLast = 0;
        if(n==0) return secondLast;

        int last = valueInHouse[0];
        if(n==1) return last;


        for(int i = 1; i < n; i++){
            int maxCost = Math.max(last, secondLast + valueInHouse[i]);
            secondLast = last;
            last = maxCost;
        }

        return last;
    }

    public static int houseRobber(int []a) {
        // Write your code here.

        // return DPRec_v1(a.length - 1, a);


        // int[] dp = new int[a.length + 1];
        // Arrays.fill(dp, -1);
        // return DPRecMem_v2(a.length - 1, a, dp);


        // return DPTab_v3(a);

        return DPTabSpaceOptimise_v4_1(a);
    }
}
