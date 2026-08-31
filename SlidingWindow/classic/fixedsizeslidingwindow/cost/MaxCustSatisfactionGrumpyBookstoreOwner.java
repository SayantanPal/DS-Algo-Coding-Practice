package classic.fixedsizeslidingwindow.cost;

// Link: https://leetcode.com/problems/grumpy-bookstore-owner/description/
// grumpy[] binary array stores the default behaviour of bookshop owner where 1 being grumpy and 0 being not-grumpy
// when grumpy[i] -> 0 => customers unsatisfied; when grumphy[i] -> 1 => customers satisfied
// Goal is to try to find a fixed window of size consecutive minutes where
// inside the window when the owner is expected to remain grumpy will trick secretly to act non-grumpy for satisfying/recovering more customers which might be lost due to default behaviour
// and we need to find such window where the count of customers being satisfied ie customer satisfaction  is maximised
public class MaxCustSatisfactionGrumpyBookstoreOwner {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;

        // out of whole timelines array, how many customer are already satisfied with owner being not grumphy by default behaviour
        int noOfCustSatisfiedByDefault = 0;
        for(int i = 0; i < n; i++){
            if(grumpy[i] == 0){ // owner not grumphy -> customer already satisfied
                noOfCustSatisfiedByDefault += customers[i];
            }
        }

        int maxAdditionalCustomers = 0;
        // with additional secret technique of window of minutes, how many additional customers can be recovered to be satisfied ?
        // done by converting grumpy(1) -> not-grumpy(0)
        // problem narrows down to finding no of contribution of customers for each window where 1 exists for grumpy
        int noOfAdditionalCustSatisfied = 0;
        for(int i = 0; i < minutes; i++){
            if(grumpy[i] == 1){ // grumpy -> not-grumpy via secret technique
                noOfAdditionalCustSatisfied += customers[i]; // extra delta recoverable customers
            }
        }
        maxAdditionalCustomers = Math.max(maxAdditionalCustomers, noOfAdditionalCustSatisfied);

        for(int i = minutes; i < n; i++){
            // shrink the window for older extra recoverable customers
            if(grumpy[i - minutes] == 1){
                noOfAdditionalCustSatisfied -= customers[i - minutes];
            }
            // expand the window for new extra recoverable customers
            if(grumpy[i] == 1){ // grumpy -> not-grumpy via secret technique
                noOfAdditionalCustSatisfied += customers[i]; // extra delta recoverable customers
            }
            maxAdditionalCustomers = Math.max(maxAdditionalCustomers, noOfAdditionalCustSatisfied);
        }

        return noOfCustSatisfiedByDefault + maxAdditionalCustomers;
    }
}
