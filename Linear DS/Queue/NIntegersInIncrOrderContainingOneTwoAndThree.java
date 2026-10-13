import java.util.ArrayDeque;
import java.util.Deque;

public class NIntegersInIncrOrderContainingOneTwoAndThree {

    /*
    Can you use a queue to store the integers in ascending order?
    Try to have a solution having O(N) complexity to avoid TLE.
    We know the initial three values will be 1, 2, and 3.
    Now, the upcoming values will be by appending 1, 2, and 3 in each given value.
    We will use a queue to store the elements in ascending order.
    */
    public int[] solve(int N) {
        Deque<String> increasingSeriesQ = new ArrayDeque<>();
        int[] ans = new int[N];

        int i = 0;
        String polled = "";
        while(i < N){
            String curr = polled + "1";
            increasingSeriesQ.offerFirst(curr);
            ans[i] = Integer.parseInt(curr);
            if(i == N - 1) return ans;
            i++;

            curr = polled + "2";
            increasingSeriesQ.offerFirst(curr);
            ans[i] = Integer.parseInt(curr);
            if(i == N - 1) return ans;
            i++;


            curr = polled + "3";
            increasingSeriesQ.offerFirst(curr);
            ans[i] = Integer.parseInt(curr);
            if(i == N - 1) return ans;
            i++;

            polled = increasingSeriesQ.pollLast();
        }

        return ans;
    }
}
