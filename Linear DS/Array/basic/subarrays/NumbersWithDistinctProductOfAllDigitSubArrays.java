package basic.subarrays;

import java.util.ArrayList;
import java.util.HashSet;

/*
*  Colorful Number

 * Problem Description
Given a number A, find if it is COLORFUL number or not.
If number A is a COLORFUL number return 1 else, return 0.
*
What is a COLORFUL Number:
A number can be broken into different consecutive sequence of digits.
The number 3245 can be broken into sequences like 3, 2, 4, 5, 32, 24, 45, 324, 245 and 3245.
This number is a COLORFUL number, since the product of every consecutive sequence of digits is different

Problem Constraints: 1 <= A <= 2 * 109

Input Format: The first and only argument is an integer A.
Output Format: Return 1 if integer A is COLORFUL else return 0.
*
* Example Input
Input 1: A = 23
Input 2: A = 236
*
* Explanation 1:
 Possible Sub-sequences: [2, 3, 23] where
 2 -> 2
 3 -> 3
 23 -> 6  (product of digits)
 This number is a COLORFUL number since product of every digit of a sub-sequence are di

Example Output
Output 1: 1
Output 2: 0

* Explanation 2:
 Possible Sub-sequences: [2, 3, 6, 23, 36, 236] where
 2 -> 2
 3 -> 3
 6 -> 6
 23 -> 6  (product of digits)
 36 -> 18  (product of digits)
 236 -> 36  (product of digits)
 This number is not a COLORFUL number since the product sequence 23  and sequence 6 is same.
*
* */

public class NumbersWithDistinctProductOfAllDigitSubArrays {

    public int colorful(int A) {
        HashSet<Long> products = new HashSet<>();
        ArrayList<Integer> numberArray = new ArrayList<>();
        while(A >0){
            int lastDigit = A % 10;
            numberArray.add(lastDigit);
            A /= 10;
        }

        int l = 0, r = numberArray.size() - 1;
        while(l < r){
            int temp = numberArray.get(l);
            numberArray.set(l, numberArray.get(r));
            numberArray.set(r, temp);
            l++;
            r--;
        }

        // for(int i = 0; i < numberArray.size(); i++){
        //     System.out.print(numberArray.get(i) + " ");
        // }
        // System.out.println();
        for(int i = 0; i < numberArray.size(); i++){
            long product = 1;
            for(int j = i; j < numberArray.size(); j++){
                // System.out.print("From " + numberArray.get(i));
                // System.out.print(" Till " + numberArray.get(j));
                product *= numberArray.get(j);
                // System.out.print(" , Product is " + product + "\n\n");
                if(products.contains(product)){
                    return 0;
                }
                products.add(product);
            }
        }
        return 1;
    }
}
