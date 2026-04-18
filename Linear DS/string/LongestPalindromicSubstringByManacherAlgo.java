// Link: https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstringByManacherAlgo {

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) return s;
        StringBuilder transformedString = new StringBuilder();
        transformedString.append("^#");
        for(char c: s.toCharArray()){
            transformedString.append(c).append("#");
        }
        transformedString.append("$");
        char[] transformedStringArr = transformedString.toString().toCharArray();
        // System.out.println(transformedString);

        int n = transformedString.length();
        int[] p = new int[n]; // p of i stores the no of matching characters from center towards left and right
        p[0] = 0;
        int center = 0, rightRadius = 0;

        int longestLenRadius = 0;
        int longestLenCenterIndex = -1;
        for(int i = 1; i < n - 1; i++){

            // calculate p[i]
            // if within rhs of radius
            if(i < rightRadius){
                int left_mirror_index = 2 * center - i; // center - (i - center);
                // if palindromic substring formation with center at mirror index has crossed left radius, then rightRadius - i
                // else (when palindromic substring formation with center at mirror index within left radius), then p[mirror_index]
                p[i] = Math.min(rightRadius - i, p[left_mirror_index]);
            } else{ // if on circumference or outside
                p[i] = 0;
            }

            // expand around the epicenter
            while(transformedStringArr[i - 1 - p[i]]  == transformedStringArr[i + 1 + p[i]]){
                p[i]++;
            }

            if(p[i] > longestLenRadius){
                longestLenRadius = p[i];
                longestLenCenterIndex = i;
            }

            if(i + p[i] > rightRadius){
                center = i;
                rightRadius = i + p[i];
            }
        }
        // for(int i = 0; i < n; i++)
        // {
        //     System.out.print(p[i]);
        // }

        int startIndex = longestLenCenterIndex - longestLenRadius; // longestLenCenterIndex - p[longestLenCenterIndex]
        int longestDiameter = 2 * longestLenRadius;
        int endIndex = startIndex + longestDiameter + 1; //longestLenCenterIndex + longestLenRadius + 1;

        // return transformedString.substring(longestLenCenterIndex - p[longestLenCenterIndex], longestLenCenterIndex + 1 + p[longestLenCenterIndex]);
        // return transformedString.substring(startIndex, endIndex);
        return s.substring(startIndex/2, startIndex/2 + longestDiameter/2);
    }
}
