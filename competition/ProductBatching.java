import java.util.Arrays;
import java.util.*;

/*
* In Amazon’s massive warehouse inventory, there are ‘n’ different types of products.
* You are given an array products of size ‘n’, where products[i] represents the number of items of product type i (where i ranges from 0 to n-1).
* These products need to be packed into batches for shipping.

The batch packing must adhere to the following conditions:
1.	No two items in the same batch can be of the same product type, meaning all items in a batch must be of distinct types.
2.	The number of items packed in the current batch must be strictly greater than the number packed in the previous batch.

Additionally, note that each item can be packed only once, and it is not required to pack every item.

Determine the maximum number of batches that can be prepared for shipment.
Note: The product types are numbered from 0 to n-1. Thus, the number of items of product type i (0 <= i <= n – 1) is given by products[i].

Function Description:
Complete the given function maximizeGroups(int[] products) where
int[] products: the no. of items of each product type

Returns: int: the max no of batches that can be prepared for shipment

Constraints:
1 <= n <= 10^5
0 <= products[i] <= 10^9

Example Walkthrough:

Example 1. n = 5. products = [2, 3, 1, 4, 2]
An optimal way to prepare the batches is as follows:
1.	The 1st batch contains 1 item of product type 4.
The remaining items = [2, 3, 1, 4, 1]
2.	The 2nd batch contains 2 items of product types: 0 and 1.
The remaining items = [1, 2, 1, 4, 1]
3.	The 3rd batch contains 3 items of product types 0, 1, and 3.
The remaining items = [0, 1, 1, 3, 1]
4.	The 4th batch contains 4 items of product types: 1, 2, 3, and 4.
The remaining items = [0, 0, 0, 2, 0]

Even though 2 items remain, a batch requires 5 items of different product types. Therefore, only 4 batches can be prepared, which is the maximum possible.
Hence, the answer is 4.

Example 2. n = 3. products = [1, 2, 7]
An optimal way to prepare the batches is as follows:
1.	The 1st batch contains 1 item of product type 2.
The remaining items = [1, 2, 6]
2.	The 2nd batch contains 2 items of product types: 1 and 2.
The remaining items = [1, 1, 5]
3.	The 3rd batch contains 3 items of product types 0, 1, and 3.
The remaining items = [0, 0, 4]

Four items are required for the next batch, all remaining of the same product types. Therefore, 3 batches can be prepared.

Example 3. n = 4. products = [1, 2, 8, 9]
The optimal way to prepare the batches is as follows:
1.	The 1st batch contains 1 item of product type 3.
The remaining items = [1, 2, 8, 8]
2.	The 2nd batch contains 2 items of product types: 2 and 3.
The remaining items = [1, 2, 7, 7]
3.	The 3rd batch contains 3 items of product types 1, 2, and 3.
The remaining items = [1, 2, 6, 6]
4.	The 4th batch contains 4 items of product types: 0, 1, 2, and, 3.
The remaining items = [0, 0, 5, 5]

Even though the next batch requires 5 items of different product types, only items of 2 distinct types are left. Hence, no more batches can be prepared.

Thus, the answer is 4.

*
* */
public class ProductBatching {

    public static int maximizeGroupsForShipment(int[] products){

        System.out.println("\n\n**********************************************************");
        System.out.println("\nProducts: " + Arrays.toString(products));

        int n = products.length;
        Arrays.sort(products);
        System.out.println("\nProducts After Sorting Greedily: " + Arrays.toString(products));
        int batchCount = 0;
        for (int pick = 1; pick <= n; pick++) {
            int tempPick = pick;
            int i = n - 1; // start from the end of the array greedily to pick the product of distinct type having higher counts
            System.out.println("\nIn Batch: " + (batchCount + 1));
            System.out.println("Trying to pick " + pick + " distinct product(s) in this batch...");

            // Keep on forming batches in round while the distinct items can be picked and it follows the strictly increasing order of pick
            while(tempPick > 0 && i >= 0) {
                if (products[i] > 0) {
                    products[i]--;
                    tempPick--; // once picked, the goal of reaching to pick distinct items in this batch round becomes closer
                    System.out.println("Picked Product: " + i + " index");
                }
                i--; // scan through all i to pick each product of distinct type if available in stock
            }
            // if there is still more items left which needs to be picked in order to form a batch with strictly increasing counf of distinct item pick
            // but if also at the same time the products are out of stock since no product left to pack
            // then no more batches can be formed
            if(i < 0 && tempPick > 0) {
                System.out.println("Reverting this batch picks since cannot pick as per strictly increasing count of distinct product pick in this batch.");
                System.out.println("No of distinct picks needed in this batch to pick: " + pick+" products but cannot pick");
                break;
            }
            // else the batch is formed once target pick is reached
            System.out.println("Remaining Products After Batch " + (batchCount + 1) +" : " + Arrays.toString(products)+"\n");
            batchCount++;
        }
        return batchCount;
    }


    public static int maximizeGroupsForShipment2(int[] products) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int product : products) {
            if (product > 0) {
                pq.add(product);
            }
        }
        int n = products.length;
        int batchCount = 0;

        for(int pick = 1; pick <= n; pick++){

            PriorityQueue<Integer> temp = new PriorityQueue<>(Collections.reverseOrder());
            boolean batchFormed = true;

            System.out.println("\nIn Batch: " + (batchCount + 1));
            System.out.println("Trying to pick " + pick + " distinct product(s) in this batch...");

            for (int i = 0; i < pick; i++) {
                if (pq.isEmpty()) {
                    batchFormed = false;
                    break;
                }
                int current = pq.poll();
                System.out.println("Picked product with count: " + current);
                if (current - 1 > 0) {
                    temp.add(current - 1);
                }
            }

            if (!batchFormed) {
                System.out.println("Reverting this batch pick since cannot pick strictly increasing distinct products.");
                break;
            }

            // Put remaining back into the queue
            pq.addAll(temp);

            batchCount++;
            pick++;
            System.out.println("Remaining products after Batch " + batchCount + ": " + pq);
        }

        return batchCount;
    }

    public static void main(String[] args) {

        int[] products = {2, 3, 1, 4, 2}; // output: 4
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{1, 2, 7}; // output: 3
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{1, 2, 8, 9}; // output: 4
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{1, 1, 1, 1, 1}; // output: 2
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{0, 0, 0, 0, 5}; // output: 1
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{0, 0, 0, 0, 0}; // output: 0
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");

        products = new int[]{1, 2, 3, 4, 5}; // output: 5
        System.out.println("\n\nTotal "+maximizeGroupsForShipment(products)+" batch(es) can be prepared");
    }
}
