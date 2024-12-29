package all_possible_combination_of_subsequences;

public class UnboundedKnapsack {

    public static int TopDownRec(int i, int[] weight, int[] value, int maxWeight){
        // since in each recursive step, we add one by one all the items hence
        // by the time one subset combination is complete, we donot have anything to mark as valid subset count as such
        // so return is 0
        if(maxWeight == 0) return 0;

        // In unbounded knapsack, the knapsack is of max weight and
        // there is no hard-fast rule that we have to fill full bag
        // we can fill it as optimally we can but we should not exceed the total bag weight capacity
        // we have infinite supply of each item
        // so when 0th item is the last item remaining to be picked
        // we can take it as much as we can without exceeding the total bag weight capacity
        // hence, always we can take maxWeight/weight[0] once we see some remaining maxWeight value of the bag empty
        // hence, no invalid case 1e9 + 7 or 10^9 + 7 since no hard fast rule of filling entire bag capacity
        // and for each count pick of item, we gain value[0] profit, hence value[0]*countsThatCanBePickedUp(=maxWeight/weight[0])
        // even if remaining maxWeight balance is less than weight[0], still the calculation is valid to give value 0 since we cannot pick further in such case
        if(i == 0) return value[0]*(maxWeight/weight[0]);

        int notTake = 0 + TopDownRec(i - 1, weight, value, maxWeight);
        int take = Integer.MIN_VALUE;
        if(maxWeight >= weight[i])
            // we stay at index i and do not decrease further just after one take because
            // we have infinite supply of each item so, we keep on reducing the weight of same item again and again
            // until the remaining weight becomes less than that particular item weight
            // after that, we automatically move on not to take
            // if we still have some remaining sack weight which is less than current item weight
            take = value[i] + TopDownRec(i, weight, value, maxWeight - weight[i]);
        return Math.max(notTake, take);
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.

        return TopDownRec(n-1, weight, profit, w);
    }

}
