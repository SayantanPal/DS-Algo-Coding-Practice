/*
* Given a string that contains only three characters: {, }, and *. A * can represent either a {, a }, or an empty string.
* Write a function to check if the expression is valid.
*
* He solved this using a greedy approach with min and max balance tracking.
* */

public class CustomExpressionValidator {
    public boolean isValidExpression(String s) {
        int min = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '{') {
                min++;
                max++;
            } else if (c == '}') {
                if (min > 0) min--;
                max--;
            } else if (c == '*') {
                if (min > 0) min--;
                max++;
            }

            if (max < 0) return false;
        }
        return min == 0;
    }
}
