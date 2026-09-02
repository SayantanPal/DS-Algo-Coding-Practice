/*
* Q. Benzamin & XOR
* Problem Description
* -----------------
In the picturesque town of Bitville, nestled amidst rolling hills and shimmering lakes, the residents had a deep fascination with the world of bits and binary operations. The townsfolk were avid mathematicians, always seeking new puzzles to unravel and insights to gain.
One sunny morning, the town's esteemed professor of mathematics, Dr. Benjamin, presented a captivating challenge to his students. He introduced them to an array A of N integers, representing a sequence of numbers with each element holding a special significance.
Dr. Benjamin explained that the students" task was to analyze the array and determine the count of pairs that satisfied a unique condition. The condition revolved around the XOR operation on the ith bit of the pair's elements. The goal was to count the pairs for which the xor of the ith bit resulted in one. You have to answer for Q queries given in array B, each query B[i] denotes the index for which you need to find the count of pairs with xor of that index equals 1.
Can you solve the task given by Dr. Benjamin?
Please read the examples given below for better understanding of the problem.
HINT : Look at the binary representation of given numbers
*
Problem Constraints:
* -----------------
* 1 <= N <= 4 * 10^4
1 <= A[i] <= 10^9
1 <= Q <= 100
0 < B[i] < 32
*
* */

public class XORPairsResultsInOne {

    // always (0, 1) pair and (1, 0) pair at a bit position results in SET bit 1
    // for a bit position, no of set bits x no of unset bits pair with each other to give 1 at that bit position

    public void printPairsForXORvalOneAtBitPos(int[] nums, int[] bits){
        int n = nums.length;
        for(int bitPos: bits){
            int cntSetBits = 0;
            for (int num : nums) {
                if ((num & (1 << bitPos)) != 0) cntSetBits++;
            }
            int cntUnsetBits = n - cntSetBits;
            int pairsWithXorOne = cntSetBits * cntUnsetBits;
            System.out.print(pairsWithXorOne + " ");
        }
    }
}
