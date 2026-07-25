package classic;


// Link: https://leetcode.com/problems/flip-string-to-monotone-increasing/
public class MinSwapsToFlipBinaryStrToMonotonicallyIncreasing {
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
