package core.search;

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
            boolean isLargerThanLeft = (mid == 0 || nums[mid] > nums[mid - 1]);
            boolean isLargerThanRight = (mid == n - 1 || nums[mid] > nums[mid + 1]);
            if(isLargerThanLeft && isLargerThanRight){ // when larger than BOTH left and right neighbour
                return mid; //nums[mid];
            }else if( !isLargerThanLeft ){ // when not larger than left, then move left to find more large
                result = mid; //nums[mid];
                r = mid - 1; // move left further till left neighbour is larger (left is same or not smaller)
            }else if( !isLargerThanRight ){ // when not larger than right, then move right to find more larger
                result = mid; //nums[mid];
                l = mid + 1; // move right further till right neighbour is larger (right is same or not smaller)
            }
        }
        return result;
    }

    public int findPeakElement_v2(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while(l <= r){
            int mid = l + (r - l)/2;
            boolean isLeftElemSmaller = (mid == 0) || (nums[mid - 1] < nums[mid]);
            boolean isRightElemSmaller = (mid == n - 1) || (nums[mid + 1] < nums[mid]);

            if(isLeftElemSmaller && isRightElemSmaller) return mid;

            if(!isLeftElemSmaller){ // left is greater
                r = mid - 1; // move towards left
            }else if(!isRightElemSmaller){ // right is greater
                l = mid + 1; // move towards right
            }
        }
        return -1;
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
                r = mid - 1; // move left further till left neighbour is smaller (left is same or not greater)
            }else if( !rightGreater ){
                result = mid; //nums[mid];
                l = mid + 1; // move right further till right neighbour is smaller (right is same or not greater)
            }
        }
        return result;
    }
}
