package basics;

// Link: https://leetcode.com/problems/reverse-words-in-a-string/
public class ReverseStrWordByWord {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        char[] sArr = s.toCharArray();
        while(i >= 0){
            while(i >= 0 && sArr[i] == ' '){
                i--;
            }
            int end = i + 1;
            while(i >= 0 && sArr[i] != ' '){
                i--;
            }
            int start = i + 1;
            sb.append(s.substring(start, end));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
