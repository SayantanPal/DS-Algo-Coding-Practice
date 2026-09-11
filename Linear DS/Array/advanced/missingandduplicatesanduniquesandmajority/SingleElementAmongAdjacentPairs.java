package advanced.missingandduplicatesanduniquesandmajority;

// Link: https://leetcode.com/problems/single-element-in-a-sorted-array/description/
public class SingleElementAmongAdjacentPairs {

    // (P11, P12), (P21, P22), (P31, P32), P4, (P51, P52), (P61, P62), (P71, P72)
    // till P4, the first among the pairs like P11, P21, P31 all appears at even index
    // after P4, the first among the pairs like P51, P61, P71, .. all appears at odd index
    public int solve(int[] A) {
        int n = A.length;
        int l = 0, r = n - 1;
        if(n == 1) return A[0];

        // if(A[0] != A[1]) return A[0];
        // if(A[n - 1] != A[n - 2]) return A[n - 1];

        while(l <= r){
            int mid = l + (r - l)/2;

            boolean isLeftNeighbourDiff = (mid == 0 || A[mid] != A[mid - 1]);
            boolean isRightNeighbourDiff = (mid == n - 1 || A[mid] != A[mid + 1]);

            // when middle element is the unique one
            if(isLeftNeighbourDiff && isRightNeighbourDiff) return A[mid];

            // when middle is not the unique one
            // move to either left or right depending on odd/even parity index
            int firstIndexAmongPair = 0;

            if(isLeftNeighbourDiff)
                firstIndexAmongPair = mid;
            else if(isRightNeighbourDiff)
                firstIndexAmongPair = mid - 1;

            // !isLeftNeighbourDiff -> Left neighbour same and !isRightNeighbourDiff -> Right neightbour same -> not possible

            if(firstIndexAmongPair % 2 == 0){ // even index -> single elem lies in right half -> move right
                l = mid + 1;
            }else{ // odd index -> single elem lies in left half -> move left
                r = mid - 1;
            }
        }
        return -1;
    }
}
