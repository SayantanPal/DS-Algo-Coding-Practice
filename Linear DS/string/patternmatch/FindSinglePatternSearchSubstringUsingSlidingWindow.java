package patternmatch;

// Link: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
// T(n) = O(n*m)
public class FindSinglePatternSearchSubstringUsingSlidingWindow {

    // Slowest - 164ms Runtime
    public int strStr_v0(String text, String searchSubStr) {
        int n = text.length(), m = searchSubStr.length();
        int i = 0, j = 0;
        while(i < n){
            while((j < m) && (i < n) && (text.charAt(i) == searchSubStr.charAt(j))){
                i++;
                j++;
            }
            if(j == m){
                return i - m;
            } else{
                i -= j;
                j = 0;
            }
            i++;
        }
        return -1;
    }

    // Slow - 143ms Runtime
    public int strStr_v1(String text, String searchSubStr) {
        int n = text.length(), m = searchSubStr.length();
        int i = 0, j = 0;
        while(i < n){
            int start = i;
            while((j < m) && (i < n) && (text.charAt(i) == searchSubStr.charAt(j))){
                i++;
                j++;
            }
            if(j == m){
                return i - m;
            } else{
                j = 0;
                i = start;
            }
            i++;
        }
        return -1;
    }

    // Better among sliding window -  1ms Runtime
    public int strStr_v2(String text, String searchSubStr) {
        int n = text.length(), m = searchSubStr.length();
        int i = 0, j = 0;
        while(i < n){
            while((j < m) && (i <= n - m) && (text.charAt(i + j) == searchSubStr.charAt(j))){
                j++;
            }
            if(j == m){
                return i;
            } else{
                j = 0;
            }
            i++;
        }
        return -1;
    }

    // Better among sliding window - 1ms runtime
    public int strStr_v3(String text, String searchSubStr) {
        int n = text.length(), m = searchSubStr.length();
        int i = 0;
        while(i < n){
            int j = 0;
            while((j < m) && (i <= n - m) && (text.charAt(i + j) == searchSubStr.charAt(j))){
                j++;
            }
            if(j == m){
                return i;
            }
            i++;
        }
        return -1;
    }


    /*
    * Why the speed difference then?

          The charAt count is similar for small inputs. The real difference shows on worst cases like:

          text:   "aaaaaaaaaa...a" (1000 a's)
          needle: "aaaa...ab"      (500 a's + b)

          v0/v1: i advances 499 positions, backtracks 499, advances 499, backtracks 499... i ping-pongs back and forth. The JVM's branch predictor and CPU cache hate this unpredictable
          access pattern.

          v2/v3: i goes 0, 1, 2, 3, 4... monotonically forward. j resets but it's just a register set to 0. The access pattern charAt(i+j) is always forward-moving, cache-friendly and
          branch-predictor friendly.

          TL;DR: Same Big-O, but v0/v1's backtracking creates chaotic memory access patterns that kill real-world CPU performance. v2/v3's stable i keeps access predictable.
      *   GOLDEN Lesson: Keep your start pointer stable. Scan with an offset (i + j), never advance and backtrack i itself. When call counts are similar, access pattern matters — forward-only access is cache-friendly and branch-predictor friendly. Ping-ponging back and forth is not.
    *
    * */
}
