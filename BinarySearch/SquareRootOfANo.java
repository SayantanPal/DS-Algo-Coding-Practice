
// Link: https://leetcode.com/problems/sqrtx/
public class SquareRootOfANo {

    public int mySqrt(int x) {
        if(x <= 1) return x;

        // solution spxce rxnges from 1 to x
        int l = 1, r = x;
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
