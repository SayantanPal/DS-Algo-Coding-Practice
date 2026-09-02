// Link: https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
// Link: https://leetcode.com/problems/find-peak-element/description/
public class PeakLocalMaximaElemInBiotonicSeries {

    // Supports Duplicate elements
    // Greater than or equal to BOTH works - BOTH strictly/leniently greater
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int result = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            boolean leftSmaller = (mid == 0 || nums[mid] > nums[mid - 1]);
            boolean rightSmaller = (mid == n - 1 || nums[mid] > nums[mid + 1]);
            if(leftSmaller && rightSmaller){ // when larger than both left and right neighbour
                return mid; //nums[mid];
            }else if( !leftSmaller ){
                result = mid; //nums[mid];
                r = mid - 1; // move left further till left neighbour is larger (left is same or not smaller)
            }else if( !rightSmaller ){
                result = mid; //nums[mid];
                l = mid + 1; // move right further till right neighbour is larger (right is same or not smaller)
            }
        }
        return result;
    }

    public int findLocalMinima(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int result = 0;
        while(l <= r){
            int mid = l + (r - l)/2;
            boolean leftGreater = (mid == 0 || nums[mid] < nums[mid - 1]);
            boolean rightGreater = (mid == n - 1 || nums[mid] < nums[mid + 1]);
            if(leftGreater && rightGreater){ // when smaller than both left and right neighbour
                return mid; //nums[mid];
            }else if( !leftGreater ){
                result = mid; //nums[mid];
                r = mid - 1; // move left further till left neighbour is smallet (left is same or not greater)
            }else if( !rightGreater ){
                result = mid; //nums[mid];
                l = mid + 1; // move right further till right neighbour is smaller (right is same or not greater)
            }
        }
        return result;
    }
}
