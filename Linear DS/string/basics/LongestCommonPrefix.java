package basics;

// Link: https://leetcode.com/problems/longest-common-prefix/
// Link: https://www.geeksforgeeks.org/problems/longest-common-prefix-in-an-array5129/1
public class LongestCommonPrefix {
    public String longestCommonPrefix(String arr[]) {
        // code here
        for(int i = 0; i < arr[0].length(); i++){
            char c = arr[0].charAt(i);
            for(int j = 1; j < arr.length; j++){
                if(i >= arr[j].length() || c != arr[j].charAt(i)){
                    return arr[0].substring(0, i);
                }
            }
        }
        return arr[0];
    }
}
