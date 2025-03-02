import java.util.Arrays;

// https://leetcode.com/problems/assign-cookies/
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int n = g.length;
        int m = s.length;

        while(i < n && j < m){
            if(s[j] >= g[i]){
                i++;
            }
            j++;
        }
        return i;
    }
}
