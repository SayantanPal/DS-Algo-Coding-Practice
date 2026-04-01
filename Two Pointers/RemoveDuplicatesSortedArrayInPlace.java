
// Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/

/*
*   The key insight: The array is already sorted. So duplicates are always adjacent. You don't need to push anything to the end — you just overwrite from the front.

  Think of it as:
  - slow = the position where the next unique element should be written
  - fast = scans through every element

  arr = [1, 1, 2, 2, 3]
         s
         f

  f moves forward. When arr[f] != arr[s], that's a new unique value.
  Move s forward, write arr[f] there.

  Step by step:
  [1, 1, 2, 2, 3]   f=1, arr[1]==arr[0] (1==1) → skip
   s  f

  [1, 1, 2, 2, 3]   f=2, arr[2]!=arr[0] (2!=1) → s++, arr[s]=arr[f]
   s     f

  [1, 2, 2, 2, 3]   f=3, arr[3]==arr[1] (2==2) → skip
      s     f

  [1, 2, 2, 2, 3]   f=4, arr[4]!=arr[1] (3!=2) → s++, arr[s]=arr[f]
      s        f

  [1, 2, 3, 2, 3]   done. s=2. Return s+1 = 3.
         s     f

  First 3 elements: [1, 2, 3] ✓
*
*
* */
public class RemoveDuplicatesSortedArrayInPlace {

    public int removeDuplicatesInPlace(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length){
            if(nums[slow] != nums[fast]){
                nums[++slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
}
