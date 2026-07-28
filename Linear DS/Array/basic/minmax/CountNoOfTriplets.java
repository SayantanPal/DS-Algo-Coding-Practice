package basic.minmax;

// Link: https://leetcode.com/problems/increasing-triplet-subsequence/
public class CountNoOfTriplets {
    public boolean increasingTripletPresent(int[] nums) {
        int secondSmallestNo = Integer.MAX_VALUE, smallestNo = Integer.MAX_VALUE;
        int secondSmallestIndex = -1, smallestIndex = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= smallestNo){
                smallestNo = nums[i];
                smallestIndex = i;
            }else if(nums[i] != smallestNo && nums[i] <= secondSmallestNo){
                secondSmallestNo = nums[i];
                secondSmallestIndex = i;
            }else{
                return true;
            }
        }
        return false;
    }

    public int findCountTripletsUsingBruteForce(int[] A) {
        int n = A.length;
        int[] leftToRightSmaller = new int[n];
        for(int i = 0; i < A.length; i++){
            int count = 0;
            for(int j = 0; j < i; j++){
                if(A[i] > A[j]){
                    count++;
                }
            }
            leftToRightSmaller[i] = count;
        }

        int[] rightToLeftLarger = new int[n];
        for(int i = 0; i < A.length; i++){
            int count = 0;
            for(int j = A.length - 1; j > i; j--){
                if(A[i] < A[j]){
                    count++;
                }
            }
            rightToLeftLarger[i] = count;
        }

        int countTriplets = 0;
        for(int i = 0; i <n; i++){
            countTriplets += leftToRightSmaller[i]*rightToLeftLarger[i];
        }
        return countTriplets;
    }
}
