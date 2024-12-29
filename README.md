# DS-Algo-Coding-Practice
Coding Practice for clearing Interviews

## Dynamic Programming(DP) 
* Minimum Cost of Climbing Stairs
* House Robber
* House Robber II
* Maximum Score which Questions to solve
* Minimum Ticket Pass Booking Cost to travel

## Dynamic Programming(DP) on every possible subsequences/subsets
* 0/1 Knapsack(values given array, each weight to reach target given array) - single supply of each item, NOT hard fast to achieve the target but as much as possible, min/max
* Unbounded Knapsack(values given array, each weight to reach target given array) - infinite supply of each item, NOT hard fast to achieve the target but as much as possible, min/max
* Rod Cutting(values given array, each weight to reach target is index) - infinite supply of each item, hard fast to achieve the target, min/max
* Coin Change With Min Coins(values given array, each weight to reach target is values ie weight = values) - infinite supply of each item, hard fast to achieve the target, min/max
* Total Ways of Coin Change(values given array, each weight to reach target is values ie weight = values) - infinite supply of each item, hard fast to achieve the target, total ways/count
* Count Subset with Target Sum(values given array, each weight to reach target is values ie weight = values) - single supply of each item, hard fast to achieve the target, total ways/count

* Note: General Thumb Rule of such problem:
    i) If infinite supply, during take each item, hold on to same index i after each take, and at base condition for last element check:
          If hard fast to reach the target check full condition if rem target % weight of 0th elem == 0(can fullfill the target or not), then only take all counts with rem target/weight of 0th elem, else if min/max then invalid state, for total ways 0
          If not hard fast, then simply rem target/weight of 0th elem(irrespective of fulfill the target or not)
       Else in case of single supply, move to previous index i-1 after each take
          If hard fast to reach the target check full condition if rem target >= weight of 0th elem(can fullfill the target or not), then only take all counts with rem target/weight of 0th elem, else if min/max then invalid state, for total ways 0
          If not hard fast, then simply value of 0th element(irrespective of fulfill the target or not) or else 0 for both min/max or total ways

    ii) If min/max, mostly every counts of items are considered. So, do 1 + each recursive call and at the end of subset formation at base condition target == 0, return 0 always and return main logic as Math.min/max(take, notTake)
       If count total ways, then count of 1 is needed at the end of subset formation. So, at the end of subset formation at base condition target == 0, return 1 to mark it as a possible valid subset and return main logic as take + notTake

In all the problems mostly, the values are given in terms of array
and the partly forming weights are either separate weight array(Knapsack) or values itself(coin change) or index itself(rod cutting) 
