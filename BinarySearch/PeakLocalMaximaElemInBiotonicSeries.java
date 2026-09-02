
public class PeakLocalMaximaElemInBiotonicSeries {

    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int result = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            boolean leftSmaller = (mid == 0 || nums[mid] > nums[mid - 1]);
            boolean rightSmaller = (mid == n - 1 || nums[mid] > nums[mid + 1]);
            if(leftSmaller && rightSmaller){
                return mid; //nums[mid];
            }else if( !leftSmaller ){
                result = mid; //nums[mid];
                r = mid - 1; // move left further
            }else if( !rightSmaller ){
                result = mid; //nums[mid];
                l = mid + 1; // move right further
            }
        }
        return result;
    }
}
