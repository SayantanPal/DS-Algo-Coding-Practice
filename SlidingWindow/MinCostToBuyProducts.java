/*
* You have a list of integers of size n, that represents costs of products. You have a discountPrice which can be used at most k times, to buy products.

You can buy the products using any one of below options:

Buy left most product and remove it from list
Buy right most product and remove it from list
Buy left most and right most products at a price of discountPrice, and remove both from list.
Find the minimum price to purchase all products.

Example 1:
Input: cost = [1, 2, 3], discountPrice = 2, and k = 1.
Expected output: 3.
Explanation:

Purchase left most at price 1, then cost = [2,3]
Purchase left most and right most at discountPrice 3, then cost = []
So minimum cost to buy products is 1+2 = 3

Example 2:
Input: cost = [9,11,13,15,17], discountPrice = 6, k = 2
Expected output: 21
Explanation:

Purchase left most at 9, then cost = [11,13,15,17]
Purchase left most and right most at discountPrice 6, then cost = [13,15]
Purchase left most and right most at discountPrice 6, then cost = []
So minimum cost to buy products is 9+6+6 = 21

Example 3:
Input: cost = [1,1,1], discountPrice = 3, k = 1
Expected: output = 3
Constraints:
1 <= n <= 105
1 <= discountPrice <= 109
1 <= k <= 105
1 <= cost[i] <= 109
Input can be in any order, but has positive integers
*
* */

/*
There are n books ordered sequentially as costs from 1,2...n, where the ith book has a cost of cost[i].
A customer wants to purchase all the books, and there is a scheme to minimise the cost as follows:
1. Let i = start of the array. The customer can buy the leftmost book at any moment for cost[i], this book is then removed from the array.
2. Let j = end of the array, the customer can buy the rightmost book at any moment for cost[j], this book is then removed from the array.
3. The customer can buy both the book at the start and the book at the end together for a special amount called “paircost”, then both the book at the start and the book at the end of the array are removed.
Option 3 can be used up to K times.

Design the function getMinimumCost which takes in an array of costs, the positive integer paircost, and K. The function should return the minimum possible cost to buy all the books in costs
 */

public class MinCostToBuyProducts {

    public static int minCost(int[] cost, int discountPrice, int k) {
        int left = 0;
        int right = cost.length - 1;
        int totalCost = 0;

        while (left <= right) {
            if (k > 0 && left < right) {
                int combinedCost = cost[left] + cost[right];

                // Use discount if beneficial
                if (discountPrice <= combinedCost) {
                    totalCost += discountPrice;
                    left++;
                    right--;
                    k--;
                } else {
                    // Buy the cheaper one from left or right
                    if (cost[left] <= cost[right]) {
                        totalCost += cost[left];
                        left++;
                    } else {
                        totalCost += cost[right];
                        right--;
                    }
                }
            } else {
                // If no discounts left, buy the cheaper one directly
                if (cost[left] <= cost[right]) {
                    totalCost += cost[left];
                    left++;
                } else {
                    totalCost += cost[right];
                    right--;
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(minCost(new int[]{1, 2, 3}, 2, 1)); // Output: 3
        System.out.println(minCost(new int[]{9, 11, 13, 15, 17}, 6, 2)); // Output: 21
        System.out.println(minCost(new int[]{1, 1, 1}, 3, 1)); // Output: 3
        System.out.println(minCost(new int[]{1}, 2, 1)); // Output: 1
        System.out.println(minCost(new int[]{10, 1, 2, 1, 10}, 5, 2)); // Output: 6
    }
}
