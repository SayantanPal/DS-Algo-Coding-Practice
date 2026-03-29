import java.util.HashMap;
import java.util.Map;

// link: https://leetcode.com/problems/valid-anagram/description/
public class ValidAnagrams {

    // HashMap
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> lookUp = new HashMap<>();

        for(int i = 0; i < s.length(); i++){
            lookUp.put(s.charAt(i), lookUp.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < t.length(); i++){
            // case: when t is superset of s
            if(lookUp.getOrDefault(t.charAt(i), 0) == 0){
                return false;
            } else if(lookUp.get(t.charAt(i)) == 1){
                lookUp.remove(t.charAt(i));
            } else{
                lookUp.put(t.charAt(i), lookUp.get(t.charAt(i)) - 1);
            }
        }
        // to check case if s contains extra chars than t, where after checking out t, s still has extra char left
        // that means to check case: when s is superset of t
        return lookUp.isEmpty();
    }

    // optimal solution: int[26] frequency array— since it's only lowercase English letters
    // Takeaway: When the charset is fixed/small (lowercase letters, digits, ASCII), prefer frequency array over HashMap even at the cost of multiple pass.
    // Same O(n) complexity but way faster in practice due to no hash collisions or boxing overhead. - HashMap: Autoboxing char → Character, hash computation, bucket lookup, collision handling, Integer boxing for values. All this per character.
    public boolean isAnagram2(String s, String t) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (char c : t.toCharArray()) freq[c - 'a']--;
        for (int f : freq) if (f != 0) return false;
        return true;
    }
}
