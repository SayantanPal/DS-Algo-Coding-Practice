package basic.minmax;

public class CountNoOfTriplets {

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
