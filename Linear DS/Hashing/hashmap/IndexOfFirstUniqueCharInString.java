package hashmap;

import java.util.HashMap;
import java.util.Map;

/*
* Scenario
In distributed systems, “duplicate events” are not a bug — they are a fact of life.
Retries, network failures, and at-least-once delivery semantics mean your system will often receive the same event multiple times.
* Now imagine this:
    You are no longer allowed to process duplicates.
    You must detect the first event that appears exactly once in a stream.
    You are given a stream of event IDs represented as a string: "abacbdce"
    Each character represents an event arriving in order.
Due to retries in downstream systems, some events may appear multiple times.
*
* */


// Link: https://leetcode.com/problems/first-unique-character-in-a-string/description/
public class IndexOfFirstUniqueCharInString {
    public static int getIndexOfFirstUniqueCharacter(String s){
        char[] chars = s.toCharArray();
        Map<Character, Integer> lookUp = new HashMap<>();
        for(int i = 0; i < chars.length; i++){
            if(lookUp.containsKey(chars[i]) && lookUp.get(chars[i]) != -1) {
                lookUp.put(chars[i], -1);
            }else{
                lookUp.put(chars[i], i);
            }
        }

        for(char c: chars){
            if(lookUp.get(c) != -1){
                return lookUp.get(c);
            }
        }
        return -1;
    }

    public static int getIndexOfFirstUniqueCharacter_optimized_complicated(String s){
        char[] chars = s.toLowerCase().toCharArray();
        int[] frequency = new int[26];
        for(int i = 0; i < chars.length; i++){
            if(frequency[chars[i] - 'a'] == 1) {
                frequency[chars[i] - 'a'] = -1;
            }else if(frequency[chars[i] - 'a'] != -1){
                frequency[chars[i] - 'a'] = 1;
            }
        }

        for(int i = 0; i < chars.length; i++){
            if( frequency[chars[i] - 'a'] != -1){
                return i;
            }
        }
        return -1;
    }

    public static int getIndexOfFirstUniqueCharacter_optimized(String s){
        char[] chars = s.toLowerCase().toCharArray();
        int[] frequency = new int[26];
        for(char c: chars){
            frequency[c - 'a']++;
        }

        for(int i = 0; i < chars.length; i++){
            if( frequency[chars[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }


    public static void main(String args[]){
        String s = "abacbdce";
        System.out.println(getIndexOfFirstUniqueCharacter(s));
        System.out.println(getIndexOfFirstUniqueCharacter_optimized(s));
    }
}
