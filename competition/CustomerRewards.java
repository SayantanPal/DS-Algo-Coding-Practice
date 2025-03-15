import java.util.*;

/**
 * Amazon Shopping is running a reward collection event for its customers.
 * There are ‘n’ customers and the ith customer has collected inititalRewards[i] points so far.
 *
 * One final tournament is to take place where:
 * the winner will be awarded n points,
 * the runner-up will be awarded n – 1 points,
 * the customer in third place will get n – 2 points, and so on
 * until the one in last place gets 1 point.
 *
 * Given an integer array initialRewards of length ‘n’, representing the initial reward points of the customer initially before the final tournament.
 * Find the number of such customers ‘i' (1 <= i <= n) such that, if the ith customer wins the final tournament, i.e., they would have the highest total points.
 * Note: The total points for a customer are calculated as the sum of their initialRewards points and the points they would receive from the final tournament (with the winner receiving ‘n’ points)
 *
 *
 * Example Walkthrough:
 *
 * Example-1. n = 3, inititalRewards = [1, 3, 4]
 *
 * 1st customer (0th index) has initial Reward Point of 1
 * 2nd Customer (1st index) has initial Reward Point of 3
 * 3rd Customer (2nd index) has initial Reward Point of 4
 *
 * Case -1) If 1st customer wins, then the reward point of 1st customer becomes 1+(n=3 ) = 4 which is not the highest because if 2nd customer becomes 2nd or 3rd, then the person would have rewarded total (inititalRewards[1] = 3) + (n-1 = 2) = 5 or ( inititalRewards[1] = 3) + (n - 2 = 1) = 4 points while for 3rd customer it is (inititalRewards[2] = 4) + 2 or (inititalRewards[1] = 4) +1 which is still greater than what 1st person has achieved.
 * So, even if 1st customer wins, then the person is not reaching highest total points and there are customers like 2nd customer who can receive points equal to or greater than 4 which customer 1 gets rewarded when wins
 *
 * Case -2) If 2nd customer wins, then the reward point of 2nd customer becomes (inititalRewards[1] = 3) + (n = 3) = 6 which is highest than both 1st customer with reward points max[ (inititalRewards[1] =1) + (n – 1 = 2) = 3, (inititalRewards[1] =1) + (n – 2 = 1) = 2] = 3 and 3rd customer with reward points max(4 + 2, 4 + 1) = 6.
 * There are no other customers which can achieve more than 6 points which is rewarded to 2nd customer if wins.
 *
 * Case-3) If 3rd customer wins, then the reward point of 3rd customer becomes 4 + 3 = 7 which is highest reward among if either 2nd customer becomes runner up or third position(3 + 2 = 5 or 3 + 1 = 4) or 3rd customer becomes runner up or third position(4 + 2 = 6, 4 + 1 = 5).
 * There are no other customers which can achieve more than 7 points which is rewarded to 3rd customer if wins.
 *
 * So, answer is the count of such customer ie 2 where 2 customers are 2 and 3.
 * But the algorithm is asking only count. So output should be 2
 *
 * Example-2. n = 3, inititalRewards = [8, 10, 9]
 *
 * Case-1) When Customer 1 wins: 8 + 3 = 11,
 *               Then,
 *               Customer 2 possible rewards: max(10 + 2, 10 + 1) = 12
 * 	        Customer 3 possible rewards: max(9 + 2, 9 + 1) = 11
 * So, even if 1st customer wins, then the person is not reaching highest total points
 *
 * Case-2) When Customer 2 wins: 10 + 3 = 13,
 *         Then,
 *         Customer 1 possible rewards: max(10 + 2, 10 + 1) = 12
 * 	        Customer 3 possible rewards: max(9 + 2, 9 + 1) = 11
 * So, if 2nd customer wins, then the person is reaching highest total points and there are no other customers which can achieve more than 13 points.
 *
 * Case-3) When Customer 3 wins: 9 + 3 = 12,
 *         Then,
 *          Customer 1 possible rewards: max(10 + 2, 10 + 1) = 12
 * 	         Customer 3 possible rewards: max(9 + 2, 9 + 1) = 11
 * So, if 3rd  customer wins, then the person is reaching highest total points and there are no other customers which can achieve more than 12 points.
 *
 *
 * So, answer is the count of such customer ie 2 where 2 customers are 2 and 3.
 * But the algorithm is asking only count. So output should be again 2.
 *
 *
 * Example-3. n = 4, inititalRewards = [5, 7, 9, 11]
 *
 * Customer 4th is wins only then 11+4 = 15 is the highest total points
 */

public class CustomerRewards {


    public static int countPossibleWinners(int[] inititalRewards){

        int n = inititalRewards.length;
        int countWinners = 0;
        if(n == 1) return 1;
        Arrays.sort(inititalRewards);// ascending order
        for(int i = 0; i < n; i++){
            int winningReward = inititalRewards[i] + n;
            boolean flag = true;
            // for previous lower initial reward customer,
            // adding n-1 to them can never be greater than winning reward
            // so scanning from next higher initial reward customer
            for(int j = i + 1; j < n; j++){ // for any jth customer with higher initial reward than ith customer
                if(i != j){
                    if(inititalRewards[j] + (n - 1) > winningReward){// if any jth customer can achieve higher reward on runners up than ith customer
                        flag = false; // then ith customer can not achieve highest possible reward
                        break; // then no point of checking further
                    }
                }
            }
            // if still no higher initial reward customer can score more then winning reward
            // then ith customer can be possible winner with total highest achievable reward
            // else ith customer can not achieve highest possible reward
            if(flag) countWinners++;
        }
        return countWinners;
    }


    public static int countPossibleWinners2(int[] initialRewards) {
        int n = initialRewards.length;
        if (n == 1) return 1;

        // Sort in ascending order (to evaluate rewards easily)
        Arrays.sort(initialRewards);

        // Create a max-heap to track the highest possible runner-up scores
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Add all possible runner-up scores into the max-heap
        for (int reward : initialRewards) {
            pq.add(reward + (n - 1)); // Maximum possible points as runner-up
        }

        int countWinners = 0;

        for (int i = 0; i < n; i++) {
            int winningReward = initialRewards[i] + n;

            // Temporarily remove the ith player's max possible score since they are winning
            pq.remove(initialRewards[i] + (n - 1));

            // If the winning reward is greater than the highest remaining runner-up score, it's a valid win
            if (pq.isEmpty() || winningReward >= pq.peek()) {
                countWinners++;
            }

            // Restore the ith player's max possible score
            pq.add(initialRewards[i] + (n - 1));
        }

        return countWinners;
    }

    public static int countPossibleWinners3(int[] initialRewards) {

        int n = initialRewards.length;
        if (n == 1) return 1;

        // TreeSet to store the rewards in sorted order
        TreeSet<Integer> rewardsSet = new TreeSet<>();
        for (int reward : initialRewards) {
            rewardsSet.add(reward);
        }

        int countWinners = 0;

        for (int reward : initialRewards) {
            int winningReward = reward + n;

            // Find the next greater element after (winningReward - (n - 1))
            Integer higher = rewardsSet.higher(winningReward - (n - 1));

            // If no such element exists, this player can win
            if (higher == null) {
                countWinners++;
            }
        }

        return countWinners;
    }

    public static void main(String[] args) {
        int[] initialRewards = {5, 7, 9, 11};
        System.out.println(countPossibleWinners2(initialRewards));

        initialRewards = new int[]{8, 10, 9};
        System.out.println(countPossibleWinners2(initialRewards));

        initialRewards = new int[]{1, 3, 4};
        System.out.println(countPossibleWinners2(initialRewards));
    }
}
