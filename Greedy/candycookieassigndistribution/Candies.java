package candycookieassigndistribution;
import java.util.ArrayList;

public class Candies {

    public static int candies(ArrayList<Integer> ratings) {
        int n = ratings.size();
        // Ensure each child starts with 1 candy.
        int[] candies = new int[n];
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }
        // First pass: for each child, ensure the child has more candies than their
        // left-side neighbor if the current child's rating is higher.
        for (int i = 1; i < n; i++) {
            if (ratings.get(i) > ratings.get(i - 1)) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // Second pass: for each child, ensure the child has more candies than their
        // right-side neighbor if the current child's rating is higher.
        for (int i = n - 2; i >= 0; i--) {
            if (ratings.get(i) > ratings.get(i + 1)) {
                // If the current child already has more candies than their right-side
                // neighbor, keep the higher amount.
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int total = 0;
        for (int c : candies) {
            total += c;
        }
        return total;
    }
}
