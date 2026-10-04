public class CntOfNosDivByBAndOrCWithinRange {

    /*
    * Return No. of Integers Divisible by Both B and C
    * and within range <= A
    * */
    public int getCntOfFactorsDivByBothBAndCWithinA(int A, int B, int C) {
        return (int)( A / GCDAndLCM.lcm(B, C) );
    }

    /*
     * Return No. of Integers Divisible by Either B or C
     * and within range <= A
     * */
    public int getCntOfFactorsDivByEitherBOrCWithinA(int A, int B, int C) {
        return ( (int)(A / B) + (int)(A / C) ) - (int)( A / GCDAndLCM.lcm(B, C) );
    }
}
