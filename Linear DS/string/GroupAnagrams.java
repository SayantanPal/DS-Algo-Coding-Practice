import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Link: https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams {

    public String generateUniqueKey(String s){
        int[] freq = new int[26];
        for(char c: s.toLowerCase().toCharArray()){
            freq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++){
            sb.append(freq[i]);
            sb.append("#");
        }
        return sb.toString();
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupAnagram = new HashMap<>();
        for(String s: strs){
            String key = this.generateUniqueKey(s);
            // List<String> anagrams;
            // if(groupAnagram.containsKey(key)){
            //     anagrams = groupAnagram.get(key);
            // }else{
            //     anagrams = new ArrayList<>();
            //     groupAnagram.put(key, anagrams);
            // }
            // anagrams.add(s);
            // all above lines can be compressed into 1 line using below computeIfAbsent() method
            groupAnagram.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groupAnagram.values());
    }
}
