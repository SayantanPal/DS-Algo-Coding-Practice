// Link: https://leetcode.com/problems/counting-bits/description/
public class NoOfSetBits {

    public int countNoOfSetBits(int num){
        return Integer.bitCount(num);
    }

    public int countNoOfSetBits_v2(int num){
        int INT_MAX_BITS = 32;
        int cntOfSetBits = 0;
        for(int bitPos = 0; bitPos < INT_MAX_BITS; bitPos++){
            if( (num & (1 << bitPos)) != 0 ){
                cntOfSetBits++;
            }
        }
        return cntOfSetBits;
    }


    // Eg: (num & (num - 1))
    // (12) 1100 - 1 = 1000 (11) where LSB at 2nd bit pos in 12 got unset
    public int countNoOfSetBits_v3(int num) {
        int cntSetBits = 0;
        // one by one keep removing SET bit from LSB position till the number empties to 0 ie not containing any bit
        // this technique mutates the num content
        while(num != 0){
            num &= (num - 1); //num = num & (num - 1);// Removes the LSB from num
            cntSetBits++;
        }
        return cntSetBits;
    }

    public int countNoOfSetBits_v4(int num) {
        int countOfSetBits = 0;
        for(int i = 0; i < 32; i++){
            if( (num & (1 << i)) != 0){
                countOfSetBits++;
            }
        }
        return countOfSetBits;
    }

}
