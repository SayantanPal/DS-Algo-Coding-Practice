package all_possible_combination_of_subsequences.stocks;

public class StocksWithMultipleTransactionsAndNoConstraints {

    public static long getMaximumProfit (int n, long[] values) {
        // Your code goes here.
        long ans = 0;
        for(int i = 1; i < values.length; i++){
            if(values[i] > values[i-1])
                ans += values[i] - values[i-1];
        }
        return ans;
    }

}
