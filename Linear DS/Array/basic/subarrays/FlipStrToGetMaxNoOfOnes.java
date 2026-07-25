package basic.subarrays;

// Link: https://www.interviewbit.com/problems/flipping-string/?amp=1/
public class FlipStrToGetMaxNoOfOnes {

    public int[] flip(String A) {
        char[] charA = A.toCharArray();
        int n = charA.length;
        int[] sum = new int[n];
        for(int i = 0; i < n; i++){
            if(charA[i] == '0'){
                sum[i] = 1; // Having 0 flipped to 1 increases the sum contribution
            }else if(charA[i] == '1'){
                sum[i] = -1; // Having 1 flipped to 0 decreases the sum contribution and this flip is mandatory as well as per prob stat
            }
        }

        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int endingIndex = -1;
        int len = 0;
        int desiredLen = 0;
        for(int i = 0; i <n; i++){
            currSum += sum[i];
            len++;
            if(currSum > maxSum){
                maxSum = currSum;
                endingIndex = i;
                desiredLen = len;
            }
            if(currSum < 0){
                currSum = 0;
                len = 0;
            }
        }

        if(maxSum <= 0){
            return new int[]{};
        }
        else{
            int[] ans = new int[2];
            ans[0] = (endingIndex - desiredLen + 1) + 1; // due to 1 based indexing
            ans[1] = (endingIndex) + 1; // due to 1 based indexing
            return ans;
        }
    }
}
