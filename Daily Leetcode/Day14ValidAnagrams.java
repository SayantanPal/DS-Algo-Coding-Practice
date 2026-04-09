// Link: https://leetcode.com/problems/valid-anagram/description/
public class Day14ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        int[] freq = new int[26];

        for(char c: s.toCharArray()){
            freq[c-'a']++;
        }
        for(char c: t.toCharArray()){
            freq[c-'a']--;
        }

        for(int f: freq){
            if(f!=0){
                return false;
            }
        }
        return true;
    }
}
