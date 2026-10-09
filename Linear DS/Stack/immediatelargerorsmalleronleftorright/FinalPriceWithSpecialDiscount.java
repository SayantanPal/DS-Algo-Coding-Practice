package immediatelargerorsmalleronleftorright;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/
public class FinalPriceWithSpecialDiscount {

    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] nearestSmallerOnRight = new int[n];
        Deque<Integer> monotonicIncreasingStack = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--){
            while(!monotonicIncreasingStack.isEmpty() && prices[i] < prices[monotonicIncreasingStack.peek()]){
                monotonicIncreasingStack.poll();
            }

            if(monotonicIncreasingStack.isEmpty()) nearestSmallerOnRight[i] = 0;
            else nearestSmallerOnRight[i] = prices[monotonicIncreasingStack.peek()];

            monotonicIncreasingStack.push(i);
        }

        for(int i = 0; i < n; i++){
            // System.out.print(nearestSmallerOnRight[i] + " ");
            nearestSmallerOnRight[i] = prices[i] - nearestSmallerOnRight[i];
        }

        return nearestSmallerOnRight;
    }
}
