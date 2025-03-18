/*
There are n books ordered sequentially as costs from 1,2...n, where the ith book has a cost of cost[i].
A customer wants to purchase all the books, and there is a scheme to minimise the cost as follows:
1. Let i = start of the array. The customer can buy the leftmost book at any moment for cost[i], this book is then removed from the array.
2. Let j = end of the array, the customer can buy the rightmost book at any moment for cost[j], this book is then removed from the array.
3. The customer can buy both the book at the start and the book at the end together for a special amount called “paircost”, then both the book at the start and the book at the end of the array are removed.
Option 3 can be used up to K times.

Design the function getMinimumCost which takes in an array of costs, the positive integer paircost, and K. The function should return the minimum possible cost to buy all the books in costs
 */


public class BuyBooksWithMinCost {
}
