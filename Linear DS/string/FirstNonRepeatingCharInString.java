import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingCharInString {
    public static void main(String[] args) {
        String str = "swiss";
        System.out.println("First non repeating char in string: " + firstNonRepeatingChar(str));
    }

    private static Character firstNonRepeatingChar(String str) {
        Map<Character, Integer> count = new LinkedHashMap<>();
        for (char c : str.toCharArray())
            count.put(c, count.getOrDefault(c, 0) + 1);
        for (char c : str.toCharArray())
            if (count.get(c) == 1)
                return c;
        return '_';
    }
}
