package searchinassumedsolutionspace;

// Link: https://leetcode.com/problems/sqrtx/
public class SquareRootOfANo {

    public int mySqrt(int x) {
        if(x <= 1) return x;

        // solution space ranges from 1 to x/2 except for 0 and 1
        int l = 1, r = x/2;
        int lowerBound = 0; // floor(sqrt(x))
        int upperBound = 0; // ceil(sqrt(x))
        while(l <= r){
            int mid = l + (r - l)/2;
            if(mid == x/mid) return mid;
            else if(mid > x/mid){
                upperBound = mid;
                r = mid - 1; // move left
            }else if(mid < x/mid){
                lowerBound = mid;
                l = mid + 1; // move right
            }
        }

        return lowerBound;
    }
}
