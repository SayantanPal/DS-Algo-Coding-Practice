import java.util.HashSet;

public class MaxXORPairValInArr {

    public int findMaximumXOR(int[] nums) {
        int answer = 0;
        for (int bitPos = 31; bitPos >= 0; bitPos--) {
            int candidate = answer | (1 << bitPos);
            HashSet<Integer> prefixes = new HashSet<>();
            for (int num : nums) {
                prefixes.add(num >> bitPos);
            }
            boolean found = false;
            for (int prefix : prefixes) {
                if (prefixes.contains(prefix ^ (candidate >> bitPos))) {
                    found = true;
                    break;
                }
            }
            if(found) answer = candidate;
        }
        return answer;
    }
}
