
/*
Given an array prices[] of length n, representing the prices of the stocks on different days.
*The task is to find the maximum profit possible by buying and selling the stocks on different days
*when at most one transaction is allowed. Here one transaction means 1 buy + 1 Sell.
*If it is not possible to make a profit then return 0.
*Note: Stock must be bought before being sold.
*/

public class Stocks_BuySell_OneTransaction {

    public int maximumProfit(int prices[]) {
        // Code here
        int noOfDays = prices.length;
        if(noOfDays < 2) return 0; // constraint: buy and sell cannot happen in 1 day
        int maxProfit = 0;
        int minBuyingPrice = prices[0]; //because 0th day is the min buying price for 2nd selling day at index 1
        for(int sellingPriceDay = 1; sellingPriceDay < noOfDays; sellingPriceDay++ ){
            int sellingPrice = prices[sellingPriceDay];
            int currProfit = sellingPrice - minBuyingPrice;
            maxProfit = Math.max(maxProfit, currProfit);
            minBuyingPrice = Math.min(minBuyingPrice, sellingPrice); //today's selling price can be among one of next day's buying price
        }
        return maxProfit;
    }
}
