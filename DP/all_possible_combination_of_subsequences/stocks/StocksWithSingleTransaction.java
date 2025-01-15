package all_possible_combination_of_subsequences.stocks;

import java.util.ArrayList;

public class StocksWithSingleTransaction {
    public static int maximumProfit(ArrayList<Integer> prices){
        // Write your code here.
        int minCP = prices.get(0);
        int maxProfit = 0;
        for(int i = 1; i < prices.size(); i++){
            int currProfit = prices.get(i) - minCP;
            maxProfit = Math.max(maxProfit, currProfit);
            minCP = Math.min(minCP, prices.get(i));
        }
        return maxProfit;
    }

    public static int maximumProfit(int[] prices){
        // Write your code here.
        int minCP = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            int currProfit = prices[i] - minCP; // prices[i] is current selling price
            maxProfit = Math.max(maxProfit, currProfit);
            minCP = Math.min(minCP, prices[i]); // make prices[i] eligible for next cost price
        }
        return maxProfit;
    }
}
