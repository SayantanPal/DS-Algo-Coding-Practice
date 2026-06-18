package basics;

public class ToggleCase {
    public String solve(String A) {
        char[] toggledStrArray = A.toCharArray();
        for(int i = 0; i < A.length(); i++){
            if(toggledStrArray[i] >= 'a' && toggledStrArray[i] <= 'z'){
                toggledStrArray[i] -= 32;
            }else if(toggledStrArray[i] >= 'A' && toggledStrArray[i] <= 'Z'){
                toggledStrArray[i] += 32;
            }
        }
        return new String(toggledStrArray);
    }
}
