import java.util.Stack;

public class PalindromeCheck {
    public static void main(String[] args) {
        String str = "madam";
        Stack<Character> stack = new Stack<>();

        for(char c : str.toCharArray())
            stack.push(c);
        StringBuilder reversed = new StringBuilder();
        while (!stack.isEmpty()) reversed.append(stack.pop());
        System.out.println(str.equals(reversed.toString())); // true
    }
}

