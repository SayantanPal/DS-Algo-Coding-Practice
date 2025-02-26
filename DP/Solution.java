class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(nums.length == 0 || nums == null || sum%4 != 0) {
            return false;
        }
        int target = sum / 4;
        boolean[] used = new boolean[nums.length];
        return makesquareHelper(nums , used , 0 , target , 0 , 4);
    }

    public boolean makesquareHelper(int[] nums , boolean[] used , int curSum , int target , int start , int k) {
        if(k == 1) return true;
        if(curSum == target) {
            return makesquareHelper(nums , used , 0 , target , 0 , k-1);
        }
        for(int i = start ; i < nums.length ; i++) {
            if(used[i]) continue;
            used[i] = true;
            if(makesquareHelper(nums , used , curSum + nums[i] , target , i+1 , k)) {
                return true;
            }
            used[i] = false;
        }
        return false;
    }

    private static boolean topDownRec2(int i, int curSum, int target, int noOfPartitions, int[] arr, boolean[] visited) {
        if (noOfPartitions == 1) {
            // Only one subset left, the rest of the elements will naturally sum to the target
            return true;
        }
        if (curSum == target) {
            // Current subset is complete, move to the next subset
            return topDownRec2(arr.length - 1, 0, target, noOfPartitions - 1, arr, visited);
        }
        if (i < 0) {
            // No more elements to pick
            return false;
        }

        for (int j = i; j >= 0; j--) {


            if (visited[j] || curSum + arr[j] > target) continue;

            // Mark the current element as used
            visited[j] = true;

            // Attempt to include the current element in the current subset
            if (topDownRec2(j - 1, curSum + arr[j], target, noOfPartitions, arr, visited)) {
                return true;
            }

            // Backtrack: unmark the current element
            visited[j] = false;
        }

        return false;
    }

}