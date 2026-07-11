package medium.MinMax;

/*
* In a kingdom, a mathematician named Arya was given an Array of integers, A of size N.
* The king challenged her to find the maximum distance between the indices of any two (not necessarily different) prime numbers in the array.
* If there is no prime number in the given array, return -1.
*
Help Arya solve this problem to prove her skill.
*
* Problem Constraints
1 <= A.length <= 105
2 <= A[i] <= 100
*
Input Format: The only argument is an Integer Array, A.
Output Format: Return an Integer, denoting the maximum distance between the indices of any two prime numbers.
*
* Example Input
Input 1: A = [14, 2, 8, 5, 3]
Input 2: A = [8, 4, 10, 7, 18]
Input 3: A = [9, 12, 4］
*
* Example Output
* Output 1: 3
Output 2: 6
Output 3: -1
*
* Example Explanation
*
For Input 1: A[1], A[3], and A[4] are prime.
* Choosing A[1] and A[1] => distance = |1 - 1| = 0
* Choosing A[1] and A[3] => distance = |3 - 1| = 2
* Choosing A[1] and A[4] => distance = |4 - 1| = 3
* Choosing A[3] and A[3] => distance = |3 - 3| = 0
* Choosing A[3] and A[4] => distance = |4 - 3| = 1
* Choosing A[4] and A[4] => distance = |4 - 4| = 0
*
So the maximum distance is 3.
*
For Input 2:
There is only 1 prime number at A[3].
Choosing A[3] and Al3] = distance = |3 - 3| = 0
So the maximum distance is 3.
*
For Input 3:
There is no Prime number in the given array.
The answer is -1.
* */
public class MaxPrimeLength {
    public boolean isPrime(int num){
        if (num == 2) return true;
        for (int divisor = 2; divisor <= Math.sqrt(num); divisor++) {
            if (num % divisor == 0)
                return false;
        }
        return true;
    }

    public int maximumPrimeDifference(int[] A) {
        int n = A. length;
        int maxLen = Integer.MIN_VALUE;
        int minLeftIndex = -1;
        for(int i = 0; i < n; i++){
            if (isPrime(A[i])){
                if (minLeftIndex == -1){
                        minLeftIndex = i;
                }
                maxLen= Math.max(maxLen, i - minLeftIndex);
            }
        }
        if (minLeftIndex == -1)
            return -1;
        else if (maxLen == Integer.MIN_VALUE)
            return 0;
        else
            return maxLen;
    }
}
