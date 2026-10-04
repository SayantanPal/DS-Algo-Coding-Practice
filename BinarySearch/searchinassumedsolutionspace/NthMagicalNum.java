package searchinassumedsolutionspace;

// Link: https://leetcode.com/problems/nth-magical-number/
public class NthMagicalNum {

    public long gcd(int a, int b){
        if(a == 0) return b;
        return gcd(b % a, a);
    }

    public long lcm(int a, int b){
        return (((long)a*b)/gcd(a,b));
    }

    public long countOfNumbersWithinRangeDivByEitherAOrB(int a, int b, long range){
        return (range / a) + (range / b) - (range / lcm(a, b));
    }
    public int nthMagicalNumber(int n, int a, int b) {
        long l = Math.min(a, b), r = n*(long)Math.min(a, b);
        while(l <= r){
            long mid = l + (r - l)/2;
            long cntOfDivisorsWithinRangeDivByEitherAOrB = countOfNumbersWithinRangeDivByEitherAOrB(a, b, mid);
            // check within mid range
            if( cntOfDivisorsWithinRangeDivByEitherAOrB == n){
                if(mid % a == 0 || mid % b == 0) return (int)(mid % 1000000007);
                else r = mid - 1; // keep on finding lowerbound so that mid is also the magical number
            }else if(cntOfDivisorsWithinRangeDivByEitherAOrB > n){
                r = mid - 1;
            }else if(cntOfDivisorsWithinRangeDivByEitherAOrB < n){
                l = mid + 1;
            }
        }
        return -1;
    }
}
