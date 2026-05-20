package basic.hashingpower;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/* Say, any char 'a' in String s must correspond/map to single character, say char 'b' in String t
* But also, we need to make sure any char 'a' in String s must not map to more than 1 char in String t =-> hashing power of char in String s
* Also, we need to make sure any char 'b' in String t must not map to more than 1 char in String s =-> hashing power of char in String t
* And, The reverse mapping might not be true, i.e. char 'b' in String s might not necessarily map back to char 'a' in String t although char 'a' in String s maps to char b in String t =-> we need two separate hashing space
* */

// Link: https://leetcode.com/problems/isomorphic-strings/
public class IsomorphicOrNot {

    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character, Character> sLookUp = new HashMap<>();
        Map<Character, Character> tLookUp = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            if(!sLookUp.containsKey(s.charAt(i))){
                sLookUp.put(s.charAt(i), t.charAt(i));
            }
            if(!tLookUp.containsKey(t.charAt(i))){
                tLookUp.put(t.charAt(i), s.charAt(i));
            }
            if(sLookUp.get(s.charAt(i)) != t.charAt(i) || tLookUp.get(t.charAt(i)) != s.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic_v2(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] sLookUp = new int[128]; Arrays.fill(sLookUp, -1);
        int[] tLookUp = new int[128]; Arrays.fill(tLookUp, -1);

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        for(int i = 0; i < s.length(); i++){
            if(sLookUp[sArr[i]] == -1){
                sLookUp[sArr[i]] = tArr[i];
            }
            if(tLookUp[tArr[i]] == -1){
                tLookUp[tArr[i]] = sArr[i];
            }
            if(sLookUp[sArr[i]] != tArr[i] || tLookUp[tArr[i]] != sArr[i]){
                return false;
            }
        }


        return true;
    }
}
