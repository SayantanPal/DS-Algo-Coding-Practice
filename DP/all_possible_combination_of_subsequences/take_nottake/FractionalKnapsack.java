package all_possible_combination_of_subsequences.take_nottake;

public class FractionalKnapsack {

    double topDownRec(int i, int[] values, int[] weights, int W){
        if(W == 0) return 0;
        if(i == 0) return (W >= weights[i]) ? values[i] : ((double) W / weights[i]) * values[i];

        // if(i == 0) {
        // if(W >= weights[i])
        //     return values[i];
        // else
        //     return ((double) W / weights[i]) * values[i];
        // }

        double notTake = topDownRec(i - 1, values, weights, W);
        double take = Integer.MIN_VALUE;
        if(W >= weights[i]){
            double takeFull = values[i] + topDownRec(i - 1, values, weights, W - weights[i]);
            double takeFraction = ((double) W / weights[i]) * values[i];
            take = Math.max(takeFull, takeFraction);
        }else {
            // Only fractional part can be taken (cannot recurse further as item exhausted)
            take = ((double) W / weights[i]) * values[i];
        }

        return Math.max(take, notTake);
    }



    double fractionalKnapsack(int[] values, int[] weights, int W) {
        // code here
        int n = values.length;
        return topDownRec(n - 1, values, weights, W);
    }
}
