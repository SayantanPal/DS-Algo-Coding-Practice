package MinimizeTheMaximumLimitInPartitionDistribution;

/*
* Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:
1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
*
*
* */


// Link: https://leetcode.com/problems/koko-eating-bananas/
public class KokoEatingBananas {

    public boolean canEatAll(int[] piles, int hourlyRate, int allowedMaxHrs){
        int totalHoursReqd = 0;
//        int bananasEaten = 0;
        // in 1 hr, koko can eat min(hourlyRate, pile[i]) whichever is minimum because
        //  Given/Assume: If the pile has less than k bananas, she eats all of them from the pile instead and will not eat any more bananas during this hour.
        // Given/Assume: If the pile has more than k bananas, she chooses to eat k bananas from a pile during this hour.
        for(int i = 0; i < piles.length; i++){
            // int currentPile = piles[i];
            // while(currentPile > 0){
            //     bananasEaten += Math.min(hourlyRate, piles[i]);
            //     currentPile -= hourlyRate;
            //     totalHoursReqd++;
            //     if(totalHoursReqd > allowedMaxHrs) return false;
            // }
            totalHoursReqd += (piles[i] + hourlyRate - 1) / hourlyRate; // += Math.ceilDiv(piles[i], hourlyRate)
            if(totalHoursReqd > allowedMaxHrs) return false;
        }
        return true;
    }
    public int minEatingSpeed(int[] piles, int h) {
        // koko chooses to eat k bananas from that pile
        // means k <= n and k cannot be > n
        // Given/Assume: Koko cannot eat faster than the largest pile per hour.
        int maxPileVol = 0;
        // int minPileVol = Integer.MAX_VALUE;
        for(int i = 0; i < piles.length; i++){
            // minPileVol = Math.min(minPileVol, piles[i]);
            maxPileVol = Math.max(maxPileVol, piles[i]);
        }

        // int totalCnt = 0;
        // for(int i = 0; i < piles.length; i++){
        //     totalCnt += piles[i];
        // }

        // solution search space ranges from koko can eat min 1 banana in 1 hour
        // to max finish all bananas in 1 hour
        int l = 1, r = maxPileVol;

        // min rate means lower bound
        int lowerBound = maxPileVol;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(canEatAll(piles, mid, h)){
                lowerBound = mid;
                r = mid - 1; // move left
            }else{
                l = mid + 1;
            }
        }
        return lowerBound;
    }
}
