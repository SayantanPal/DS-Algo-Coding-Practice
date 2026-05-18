package hashset;

import java.util.HashSet;
import java.util.Set;

// Link: https://leetcode.com/problems/happy-number/
public class HappyNumber {
    public boolean isHappy(int n) {

        int num = n;

        Set<Integer> sums = new HashSet<>();

        int sum = 0;
        while(true){
            sum = 0;
            while(num > 0){
                int lastDigit = num%10;
                sum += lastDigit * lastDigit;
                num /= 10;
            }
            if(sum == 1) return true;
            if(sums.contains(sum)) // If a number isn't happy, the sequence of digit-square-sums will eventually loop.  If they meet and it's not 1, it's a cycle.
                return false;
            else
                sums.add(sum);
            num = sum;
        }
    }
}
