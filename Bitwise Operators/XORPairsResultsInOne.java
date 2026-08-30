public class XORPairsResultsInOne {

    // always (0, 1) pair and (1, 0) pair at a bit position results in SET bit 1
    // for a bit position, no of set bits x no of unset bits pair with each other to give 1 at that bit position

    public void printPairsForXORvalOneAtBitPos(int[] nums, int[] bits){
        int n = nums.length;
        for(int bitPos = 0; bitPos < bits.length; bitPos++){
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
