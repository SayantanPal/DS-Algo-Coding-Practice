package classic.fixedsizeslidingwindow;

import java.util.HashMap;

public class CountDistinctNumbersInFixedSlidingWindow {

    public int[] dNums(int[] A, int B) {
        HashMap<Integer, Integer> unique = new HashMap<>();
        int n = A.length;
        int[] ans = new int[n - B + 1];
        for(int i = 0; i < B; i++){
            unique.put(A[i], unique.getOrDefault(A[i], 0) + 1);
        }
        int j = 0;
        ans[j++] = unique.size();

        for(int i = B; i < n; i++){
            if(unique.containsKey(A[i - B])){
                unique.put(A[i - B], unique.get(A[i - B]) - 1);
                if(unique.get(A[i - B]) == 0){
                    unique.remove(A[i - B]);
                }
            }
            unique.put(A[i], unique.getOrDefault(A[i], 0) + 1);
            ans[j++] = unique.size();
        }
        return ans;
    }

    // ONLY if constraint: 1 <= A[i] <= 10^8
    public int[] dNums_2(int[] A, int B) {
        int[] unique = new int[1000000001];
        int n = A.length;
        int[] ans = new int[n - B + 1];
        int distintCnt = 0;
        for(int i = 0; i < B; i++){
            if(unique[A[i]] == 0){
                distintCnt++;
            }
            unique[A[i]]++;
        }

        int j = 0;
        ans[j++] = distintCnt;

        for(int i = B; i < n; i++){
            if(unique[A[i - B]] > 0){
                unique[A[i - B]]--;
                if(unique[A[i - B]] == 0){
                    distintCnt--;
                }
            }
            if(unique[A[i]] == 0){
                distintCnt++;
            }
            unique[A[i]]++;
            ans[j++] = distintCnt;
        }
        return ans;
    }
}
