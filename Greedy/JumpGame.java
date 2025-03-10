public class JumpGame {

    public static boolean canJump(int[] nums) {

        int maxJump = 0;
        int n = nums.length;
        if(n == 1) return true; // a number is always reachable by itself
        if(n > 0 && nums[0] == 0) return false; // if starting number is 0, then nowhere can be reached starting from that number except itself
        for(int i = 0; i < n; i++){
            int maxJumpFromCurrentIndex = nums[i] + i; // Check Local maximum index how far you can reach from this ith index
            if(nums[i] == 0  // deadlock: if nowhere can be reached from this ith index except itself
                    && maxJump == i){ // and this is the maximum reachable index element from all earlier indices starting from 0 // alternate: maxJump == maxJumpFromCurrentIndex
                return false;
            }
            // else if we still have higher index element that can be reached crossing this deadlock index element, we ignore this deadlock index
            maxJump = Math.max(maxJump, maxJumpFromCurrentIndex); // Hold the maximum that can be reached from all previously encountered indexes
            if(maxJump >= n - 1){ // the moment local maximum from any index reached or overexceeds array last element index
                return true;
            }
        }
        return true;
    }

    public static int jump(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        if(n == 1) return 0; // a number is always reachable by itself, no jump needed
        if(n > 0 && nums[0] == 0) return 0; // if starting number is 0, then nowhere can be reached starting from that number except itself
        int minJumpsToReachTarget = 0;
        // when r reaches at n - 1 or beyond n - 1, the next range calculation should stop
        // so till r < n - 1, the next range calculation towards target happens and it updates maxJump for that last range as well
        while(r < n - 1){
            System.out.println("l: " + l + " r: " + r + " minJumps: " + minJumpsToReachTarget);
            int nextL = r + 1;
            int maxJumpFromAllPreviousIndices = 0;
            for(int i = l; i <= r; i++){
                int maxJumpFromCurrentIndex = nums[i] + i;
                maxJumpFromAllPreviousIndices = Math.max(maxJumpFromAllPreviousIndices, maxJumpFromCurrentIndex);
            }
            minJumpsToReachTarget++; // update jump count for next range(say, l', r') wrt l and r
            l = nextL;
            r = maxJumpFromAllPreviousIndices;
        }
        return minJumpsToReachTarget;
    }

    public static void main(String[] args){
        System.out.println(jump(new int[]{1, 1, 1, 1}));
    }
}
