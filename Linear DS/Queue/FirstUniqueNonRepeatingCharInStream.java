import java.util.ArrayDeque;
import java.util.Deque;

/*
* Stream First Non-repeating
Difficulty: Medium
Given a string s consisting of only lowercase alphabets, for each index i in the string (0 ≤ i < n), find the first non-repeating character in the prefix s[0..i]. If no such character exists, use '#'.

Examples:
Input: s = "aabc"
Output: a#bb
*
Explanation:
At i=0 ("a"): First non-repeating character is 'a'.
At i=1 ("aa"): No non-repeating character, so '#'.
At i=2 ("aab"): First non-repeating character is 'b'.
At i=3 ("aabc"): Non-repeating characters are 'b' and 'c'; 'b' appeared first, so 'b'.
*
Input: s = "bb"
Output: "b#"
*
* Some more examples:
* "abccabc" -> "aaaab##"
"abbcabc" -> "aaaacc#"
"abcbabc -> "aaaacc#"

*
*
Explanation:
At i=0 ("b"): First non-repeating character is 'b'.
At i=1 ("bb"): No non-repeating character, so '#'.
*
Constraints: 1 ≤ s.size() ≤ 105
*
*
* */

// Link: https://www.geeksforgeeks.org/problems/first-non-repeating-character-in-a-stream1216/1
public class FirstUniqueNonRepeatingCharInStream {
    public String firstNonRepeating(String s) {
        // code here
        Deque<Character> queue = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        int[] freq = new int[26];
        for(char c: s.toCharArray()){

            if(freq[c - 'a'] == 0){ // new element not encountered yet
                queue.offerLast(c);
            }

            freq[c - 'a']++;

            // front should contain unique element
            while(!queue.isEmpty() && freq[queue.peekFirst() - 'a'] > 1){
                queue.pollFirst();
            }

            if(queue.isEmpty()){
                sb.append('#');
            }else{
                sb.append(queue.peekFirst());
            }
        }
        return sb.toString();
    }
}
