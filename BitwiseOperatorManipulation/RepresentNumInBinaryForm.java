/*
* Problem 1: Alex and Sam are good friends. Alex is doing a lot of programming these days. He has set a target score of A for himself.
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

/*
* Problem 2: Alex has a cat named Boomer. He decides to put his cat to the test for eternity.
He starts on day 1 with one stash of food unit, every next day, the stash doubles.
If Boomer is well behaved during a particular day, only then she receives food worth equal to the stash produced on that day.
Boomer receives a net worth of A units of food. What is the number of days she received the stash?
*
* Input 1: A = 5
* Output 1: 2
*  To eat a total of 5 units of food, Boomer behaved normally on Day 1 and on the Day 3.
*
* Input 2: A = 8
* Output 2: 1
*  To eat a total of 8 units of food, Boomer behaved normally only on day 4.
*
* */
public class RepresentNumInBinaryForm {
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
}
