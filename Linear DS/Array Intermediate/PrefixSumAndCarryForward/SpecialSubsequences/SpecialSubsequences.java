package SpecialSubsequences;

/*
* You have given a string A having Uppercase English letters.
* You have to find the number of pairs (i, j) such that A[i] = 'A', A[j] = 'G' and i < j.
* So, Find count of pairs of {'A','G'} such that index of A is less than index of G
*
* */
public class SpecialSubsequences {
    public static long findSpecialSubsequences(String A) {
        long countPairs = 0, countA = 0;
        for(char c: A.toCharArray()){
            if(c == 'A') countA++;
            else if (c == 'G') countPairs+=countA;
        }
        return countPairs;
    }

    public static void main(String[] args) {
        System.out.println(findSpecialSubsequences("ABCGAG"));
    }
}
