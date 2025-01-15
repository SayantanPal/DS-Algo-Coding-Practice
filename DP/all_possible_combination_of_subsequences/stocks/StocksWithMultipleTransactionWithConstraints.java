package all_possible_combination_of_subsequences.stocks;

// Stocks - 2,3,4
public class StocksWithMultipleTransactionWithConstraints {
    public static int bottomUpRec(int i, boolean eligibleForBuy, int transactionFee, int k, int n, int[] prices){

        if(k == 0 || i == n) return 0;

        if(eligibleForBuy){ // buy
            int takeForBuy = -prices[i] + bottomUpRec(i + 1, false, transactionFee, k, n, prices);
            int notTakeForBuy = 0 + bottomUpRec(i + 1, true, transactionFee, k, n, prices);
            return Math.max(takeForBuy, notTakeForBuy);
        } else { // sell
//            int takeForSell = prices[i] + bottomUpRec(i + 1, true, transactionFee, k - 1, n, prices);
            // i+2 for cooldown before next buy instead of i+1
            // - transactionFee when transaction fee is incurred with each buy/sell pair
            int takeForSell = prices[i] - transactionFee + bottomUpRec(i + 2, true, transactionFee, k - 1, n, prices);
            int notTakeForSell = 0 + bottomUpRec(i + 1, false, transactionFee, k, n, prices);
            return Math.max(takeForSell, notTakeForSell);
        }
    }

    public static int topDownRec(int i, boolean eligibleForBuy, int transactionFee, int k, int[] prices) {

        if (k == 0 || i < 0) return 0;

        // If the array finishes much before within K transaction limit, then
        // if selling already accounted before as a possibility, i cannot have a selling price already accounted in place without having a prior buying price
        // so the last 0th element is either buying price(open for buying) or if looking for selling price, it cannot be a selling price because it will not have its previous buy
        if (i == 0) {
            if (eligibleForBuy) { // if searching for buy price, then last element is buying price
                return -prices[0]; // make this as the buy price as only option by deducting buy price
            } else { // if searching for another sell price, we will not take it because it will not have its previous buy. So add 0 profit
                return 0;
            }
        }

        if (eligibleForBuy) { // buy
//            int takeForBuy = -prices[i] + topDownRec(i - 1, false, transactionFee, k - 1, prices);
            // i-2 for cooldown after previous sell instead of i-1
            int takeForBuy = -prices[i] + topDownRec(i - 2, false, transactionFee, k - 1, prices);
            int notTakeForBuy = 0 + topDownRec(i - 1, true, transactionFee, k, prices);
            return Math.max(takeForBuy, notTakeForBuy);
        } else { // sell
            int takeForSell = prices[i] - transactionFee + topDownRec(i - 1, true, transactionFee, k, prices);
            int notTakeForSell = 0 + topDownRec(i - 1, false, transactionFee, k, prices);
            return Math.max(takeForSell, notTakeForSell);
        }
    }

}
