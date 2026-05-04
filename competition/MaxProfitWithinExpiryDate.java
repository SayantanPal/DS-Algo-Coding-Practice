import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaxProfitWithinExpiryDate {

    // constraint 1 - in 1 day, ONLY 1 item is sellable
    // constraint 2 - in every day, 1 item has to be sold to maximize profit if any available within its expiry date
    //                ie it is not the case that only for dates with expiry deadlines, the item becomes eligible for selling
    public static int calculateMaxPossibleProfitWithinExpiry(int[] expiryDates, int[] profit){
        int maxExpiryDate = -1;
        for(int expiryDate: expiryDates){
            maxExpiryDate = Math.max(maxExpiryDate, expiryDate);
        }
        List<Integer>[] expiryDateBucket = new ArrayList[maxExpiryDate + 1];

        for(int i = 0; i < expiryDates.length; i++){
            if(expiryDateBucket[expiryDates[i]] == null){
                expiryDateBucket[expiryDates[i]] = new ArrayList<>();
            }
            expiryDateBucket[expiryDates[i]].add(profit[i]);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int maxProfit = 0;
        for(int i = expiryDateBucket.length - 1; i > 0; i--){
            if(expiryDateBucket[i] != null) {
                maxHeap.addAll(expiryDateBucket[i]);


                System.out.print("At expiry date: " + i + ", items are: "
                        + expiryDateBucket[i] //String.join(", ", expiryDateBucket[i].stream().map(String::valueOf).toList())
                        + ". "

                );
            }
            if(!maxHeap.isEmpty()){
                int currPossibleMaxProfitForThisExpiryDate = maxHeap.poll(); // pick item for every days till max expiry dates if available
                maxProfit += currPossibleMaxProfitForThisExpiryDate;
                System.out.print("current max profit possible is: " + currPossibleMaxProfitForThisExpiryDate);
            }
            System.out.println();
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] expiryDates = {3, 1, 3, 2, 3};
        int[] profit = {6, 5, 3, 1, 9};

        System.out.println("Max Profit possible within expiry dates: " + calculateMaxPossibleProfitWithinExpiry(expiryDates, profit) + "\n\n");

        int[] expiryDates1 = {3, 3, 3};
        int[] profit1 = {10, 20, 30};

        System.out.println("Max Profit possible within expiry dates: " + calculateMaxPossibleProfitWithinExpiry(expiryDates1, profit1));
    }
}
