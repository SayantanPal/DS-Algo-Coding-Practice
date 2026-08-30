/*
* Alex and Sam are good friends. Alex is doing a lot of programming these days. He has set a target score of A for himself.
Initially, Alex's score was zero. Alex can double his score by doing a question, or Alex can seek help from Sam for doing questions that will contribute 1 to Alex's score. Alex wants his score to be precisely A. Also, he does not want to take much help from Sam.

Find and return the minimum number of times Alex needs to take help from Sam to achieve a score of A.
*
* */

/*
* Solution: Represent target score which needs to be achieved in binary form
* Why binary form?
* A can be represented as multiple doubles (powers of 2) and +1 anytime in between to form a number
* number of 1s or set bits says everytime when 0 to 1 as first step is needed
*
* for num = 5,
* Initial score : 0
 Takes help from Sam, score : 1
 Alex solves a question, score : 2
 Alex solves a question, score : 4
 Takes help from Sam, score: 5
*
* */
public class RepresentNumInBinaryForm {
    public int countNoOfSetBits(int num){
        return Integer.bitCount(num);
    }

    public int countNoOfSetBits2(int num){
        int INT_MAX_BITS = 32;
        int cntOfSetBits = 0;
        for(int bitPos = 0; bitPos < INT_MAX_BITS; bitPos++){
            if( (num & (1 << bitPos)) != 0 ){
                cntOfSetBits++;
            }
        }
        return cntOfSetBits;
    }
}
