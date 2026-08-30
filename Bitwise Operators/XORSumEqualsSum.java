public class XORSumEqualsSum {

    // (A OR B) = (A XOR B) + 2 * (A AND B)
    // when (A OR B) == (A XOR B)
    // then 2 * (A AND B) = 0
    // => A AND B = 0 which means A and B cannot together have BOTH SET bit present at a particular bit position
    // it is either (0, 1) or (1, 0) or (0, 0) BUT NEVER (1, 1)
    // the problem narrows down to find next greater or immediate smaller just by setting only unset bits.
    // here setting bit comes with a condition that not only whichever bit is being set should be unset, but also for other SET bit in A, there cannot be same SET bit in Y

    // to get greater element than num within the MSB bit position (inclusive),
        // a. unsetting any already set position in num won't give ever any number greater than A
        // b. setting an unset bit position in num can raise the value but only if other set bits in num are never changed
        // but we cannot keep same SET bits at any bit position till MSB
    // which means none of the unset bit position can be set to raise th value of num or to get greater value within till MSB
    // so the bit left to MSB needs to be set but to get immediate greater number than number, from MSB till 0-th bit position, all bits needs to remain as 0 (unset)

    // Eg: for num = 101, greater number can be 111, but at bit pos 0 and 3, both together cannot be 1 together
    // that means we need to SET left bit of MSB position of num which give 1010 but this is not immediate bigger
    // keeping the bits simply unset for all pos from MSB to 0th bit gives immediate greater that is 1000

    // Eg: similarly for 1011, it is 10000 and also for 1101, it is 10000 and also for 1111, it is 10000

    // Integer.highestOneBit(num) => MSB position (bit pos starting from 32th bit -> 0th bit where bit is found SET) of Num

    // Smaller number just greater than num
    // whose XOR sum = arithmetic (OR) sum
    public int immediateGreaterNoWithXORandORsumSame(int num){
        return Integer.highestOneBit(num) << 1;
    }

    // To get immediate smaller than A, but with condition that no same bit pos should have together 1
    // then except MSB( to make it smaller than num), TOGGLE the bit position for 1 -> 0(since they together cannot be set) and also 0 -> 1 ( because we want immediate higher number)
    // this is actually toggling operation except MSB position

    // this is possible by creating a mask of all 1's SET bit except MSB position
    // Eg: 1011 -> 1--011
    // Integer.highestOneBit(num) << 1 will create 10000 ie by 1 extra shift from MSB
    // followed by (Integer.highestOneBit(num) << 1) - 1 which will convert into mask of 1's except MSB ie 1000 -> 0111 ie 111
    // with MSB, toggle (0 -> 1 and 1 -> 0) num's bit by doing ~num
    // and to eliminate out MSB position do AND operation with mask of 1's except MSB ie ~num & ((Integer.highestOneBit(num) << 1) - 1)
    // so, num = 1011 => ~num = 0100 => ~num & ((Integer.highestOneBit(num) << 1) - 1) = 0100 & 0111 -> 0100
    // 0100 is toggle of num except MSB bit here

    // Greatest Number just smaller than num
    // whose XOR sum = arithmetic (OR) sum
    public int immediateSmallerNoWithXORandORsumSame(int num){
        return ~num & ((Integer.highestOneBit(num) << 1) - 1); // or ~num & (Integer.highestOneBit(num) - 1) if you ignore the MSB
    }
}
