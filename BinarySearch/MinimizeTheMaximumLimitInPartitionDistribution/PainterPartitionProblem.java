package MinimizeTheMaximumLimitInPartitionDistribution;
/*
* Problem Description

Given 2 integers A and B and an array of integers C of size N. Element arr[i] represents the length of ith board.
You have to paint all N boards [arr0, arr1, arr2, arr3 … arrN-1].
* There are k no. of painters available and
* each of them takes B units of time to paint 1 unit of the board.

Calculate and return the minimum amt og time required to paint all boards under below constraints that:
NOTE:
1. Any painter will only paint contiguous sections/sequence of the board (no skipping allowed).
2. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another. (no splitting allowed).
3. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.
4. There can be more painters than the no of boards - that is valid use-case
5. It is not mandatory to deploy/assign all the painters to work.
*
Return the ans % 10000003.
*
* Input Format:
The first argument given is the integer k.
The second argument given is the integer B.
The third argument given is the integer array arr.

Output Format:
Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.

Problem Constraints:

1 <= k <= 1000
1 <= B <= 10^6
1 <= N <= 10^5
1 <= arr[i] <= 10^6

* Example Input
Input 1:
 k = 2
 B = 5
 arr = [1, 10]
 *
 *
 * Output 1: 50
 *
 * Example Explanation
Explanation 1:
 Possibility 1:- One painter paints both blocks, time taken = 55 units.
 Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
 There are no other distinct ways to paint boards.
 ans = 50 % 10000003
*

Input 2:
 k = 10
 B = 1
 C = [1, 8, 11, 3]
 *
 * Output 2:  11

* Explanation 2:
 Each block is painted by a painter so, Painter 1 paints block 1, painter 2 paints block 2, painter 3 paints block 3
 and painter 4 paints block 4, time taken = max(1, 8, 11, 3) = 11
 ans = 11 % 10000003

Example Output
* */

// Link: https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
public class PainterPartitionProblem {

    public boolean canBePaintedByAllPainters(int[] lenOfWall, long maxTimeLimitEachPainterCanTake, long maxNoOfPaintersLimitAvailable){
        long cntOfPainterNeeded = 1L;
        long timeTakenByEachPainter = 0L;
        for(int i = 0; i < lenOfWall.length; i++){
            timeTakenByEachPainter += lenOfWall[i];
            if(timeTakenByEachPainter > maxTimeLimitEachPainterCanTake){
                cntOfPainterNeeded++;
                timeTakenByEachPainter = lenOfWall[i];
            }
            if(cntOfPainterNeeded > maxNoOfPaintersLimitAvailable) return false;
        }
        return true;
    }

    // costToPaintEachUnit -> B
    public int minTime(int[] arr, int k, int costToPaintEachUnit) {
        // code here
        int maxLength = arr[0]; // when all A painters are deployed, parallel time is the max time taken to paint N boards
        long sumOfLength = arr[0]; // when 1 painter handles the painting of all boards
        for(int i = 1; i < arr.length; i++){
            sumOfLength += arr[i];
            maxLength = Math.max(maxLength, arr[i]);
        }

        // solution search space ranges from [maxLength, sumOfLength]
        // target is to find total min length of boards that can be painted
        long l = maxLength, r = sumOfLength;
        long minLen = 0;
        while(l <= r){
            long mid = l + (r - l)/2;
            if(canBePaintedByAllPainters(arr, mid, k)){ // if yes, there try to search for more min time
                minLen = mid;
                r = mid - 1; // move left
            }else{
                l = mid + 1;
            }
        }
        return (int)( (minLen * costToPaintEachUnit) % 10000003 );
    }

}
