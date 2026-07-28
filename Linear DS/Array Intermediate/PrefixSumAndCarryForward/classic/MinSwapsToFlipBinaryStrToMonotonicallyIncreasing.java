package classic;


// Link: https://leetcode.com/problems/flip-string-to-monotone-increasing/
public class MinSwapsToFlipBinaryStrToMonotonicallyIncreasing {

    /*
    * for every index: take it as a possible pivot point of 0's on left and 1's on right after flipping
    * so, before flipping, 1's on the left and 0's on the right are the possible left and right costs for each index i
    * left cost of flipping 1's to 0's can range from 0 to i - 1 whereas right cos of flipping 0's to 1's can range from i to n - 1
    * alternatively left can also range from 0 to i whereas right as i+1 to n-1
    * */
    public int minFlipsMonoIncr(String s) {
        char[] sArr = s.toCharArray();
        int n = sArr.length;
        int[] prefixFromLeftCountOfOne = new int[n];
        int[] prefixFromLeftCountOfZero = new int[n];
//        int[] suffixFromRightCountOfZero = new int[n];

        for(int i = 0; i < n; i++){
            if(sArr[i] == '1')
                prefixFromLeftCountOfOne[i] = 1;
            if(sArr[i] == '0'){
                prefixFromLeftCountOfZero[i] = 1;
//                suffixFromRightCountOfZero[i] = 1;
            }
        }

        for(int i = 1; i < n; i++){
            prefixFromLeftCountOfOne[i] += prefixFromLeftCountOfOne[i - 1];
            prefixFromLeftCountOfZero[i] += prefixFromLeftCountOfZero[i - 1];
        }

//        for(int i = n - 1; i >= 1; i--){
//            suffixFromRightCountOfZero[i - 1] += suffixFromRightCountOfZero[i];
//        }

        int minCost = Integer.MAX_VALUE;
        // at i = -1, right side is from 0 to n - 1
        minCost =  Math.min(minCost, 0 + prefixFromLeftCountOfZero[n - 1]); //minCost =  Math.min(minCost, 0 + suffixFromRightCountOfZero[0]);
        for(int i = 0; i < n; i++){
            int leftCost = (i == 0) ? 0 : prefixFromLeftCountOfOne[i - 1]; // 0 to i - 1
            int rightCost = (i == 0) ? prefixFromLeftCountOfZero[n - 1] : prefixFromLeftCountOfZero[n - 1] - prefixFromLeftCountOfZero[i - 1]; //suffixFromRightCountOfZero[i]; // i to n-1
            minCost = Math.min(minCost, leftCost + rightCost);
        }
        // at i = n, left side is from 0 to n - 1
        minCost =  Math.min(minCost, prefixFromLeftCountOfOne[n - 1] + 0);
        return minCost;
    }
}
