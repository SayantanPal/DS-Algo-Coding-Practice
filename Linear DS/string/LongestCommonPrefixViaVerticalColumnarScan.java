
// Link: https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefixViaVerticalColumnarScan {

    public String longestCommonPrefix(String[] strs) {
        int smallLen = Integer.MAX_VALUE;
        String smallStr = "";
        for(String s: strs){
            if(s.length() < smallLen){
                smallLen = s.length();
                smallStr = s;
            }
        }

        StringBuilder commonPrefix = new StringBuilder();;
        for(int i = 0; i < smallStr.length(); i++){
            char a = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                if(a != strs[j].charAt(i)){
                    return commonPrefix.toString();
                }
            }
            commonPrefix.append(a);
        }
        return commonPrefix.toString();
    }

    // more optimized: no need of prior scanning of small string
    // start with any string and during vertical scanning keep a check that if index under comparison greater than length of any currently scanning string should stop compute and return result
    public String longestCommonPrefix_v2(String[] strs) {
        StringBuilder commonPrefix = new StringBuilder();
        for(int i = 0; i < strs[0].length(); i++){
            char a = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                if((i >= strs[j].length()) || (a != strs[j].charAt(i))){ // catch: (i >= strs[j].length()) protection to overcome prior scanning of small string
                    return commonPrefix.toString();
                }
            }
            commonPrefix.append(a);
        }
        return commonPrefix.toString();
    }

    // Alternate: use substring() instead of Stringbuilder
    // Same runtime as that of previous
    public String longestCommonPrefix_v2_1(String[] strs) {
        for(int i = 0; i < strs[0].length(); i++){
            char a = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++){
                if((i >= strs[j].length()) || (a != strs[j].charAt(i))){ // catch: (i >= strs[j].length()) protection to overcome prior scanning of small string
                    return strs[0].substring(0, i); // [0, i) => [0, i-1]
                }
            }
        }
        return strs[0];
    }
}
