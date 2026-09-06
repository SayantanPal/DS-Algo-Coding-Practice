package minimizethemaximumlimit;

public class MinNoOfDaysToMakeMBouquets {

    public boolean canMakeBouquets(int[] bloomDay, long maxDaysLimitToWait, long maxFlowerLimitInEachBouquet, long noOfBouquetsNeeded){
        long flowerCount = 0L;
        long bouquetCount = 0L;
        for(int i = 0; i < bloomDay.length; i++){
            if(bloomDay[i] <= maxDaysLimitToWait) // when blood day is well within the limit of target days
                flowerCount++; // count that flower in for bouquet formation
            else // when it breaks maxFlowerLimitInEachBouquet adjacent sequence
                flowerCount = 0L; // reset flower count to 0 for tracking the next adjacent sequence
            if(flowerCount == maxFlowerLimitInEachBouquet){ // eligible for one bouquet formation
                bouquetCount++; // bouquet formed
                flowerCount = 0L; // reset flower count to 0 for the next bouquet formation
            }
            if(bouquetCount == noOfBouquetsNeeded) return true; // if target bouquet count already achieved
        }
        return false;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        if(m * k > bloomDay.length) return -1; // if no of flower supply needed is more than the availability from garden
        // when m * k <= n ie no of flowers supply needed is less than or equals availability garden

        long minDaysToWait = 0;
        long maxDaysToWait = 0;

        for(int i = 0; i < bloomDay.length; i++){
            minDaysToWait = Math.min(minDaysToWait, bloomDay[i]);
            maxDaysToWait = Math.max(maxDaysToWait, bloomDay[i]);
        }
        long minDaysToWaitForAllBouquets = -1;
        long l = minDaysToWait, r = maxDaysToWait;
        while(l <= r){
            long mid = l + (r - l)/2;
            if(canMakeBouquets(bloomDay, mid, k, m)){
                minDaysToWaitForAllBouquets = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return (int) minDaysToWaitForAllBouquets;
    }
}
