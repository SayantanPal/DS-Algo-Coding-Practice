import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
// Link: https://www.geeksforgeeks.org/problems/evaluation-of-postfix-expression1735/1
public class EvaluatePostFixOrReversePolishAnnotation {

    public int evalPostfixRPN_v2(String[] arr) {
        // code here
        Deque<Integer> stack = new ArrayDeque<>();
        for(String s: arr){
            int b = 0, a = 0;

            // if s contains any operator - simplest alternative of py membership operator
            // first operand b popped out and then operand a popped out
            if("+-*/^".contains(s)) { b = stack.pop(); a = stack.pop(); }

            if(s.equals("+")) stack.push(a + b);
            else if(s.equals("-")) stack.push(a - b);
            else if(s.equals("*")) stack.push(a * b);
            else if(s.equals("/")) stack.push((int) Math.floorDiv(a, b)); // consider floor division
            else if(s.equals("^")) stack.push((int) Math.pow(a, b)); // one extra operation

            else stack.push(Integer.parseInt(s));
        }
        return stack.pop();
    }

    public int evalPostfixRPN_v1(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < tokens.length; i++){
            char[] tokenArr = tokens[i].toCharArray();
            if(tokenArr.length == 1 && tokenArr[0] == '*'){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                int c = (a * b);
                stack.offerFirst(c);
            }else if(tokenArr.length == 1 && tokenArr[0] == '/'){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                int c = (b / a);
                stack.offerFirst(c);
            }else if(tokenArr.length == 1 && tokenArr[0] == '+'){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                int c = (a + b);
                stack.offerFirst(c);
            }else if(tokenArr.length == 1 && tokenArr[0] == '-'){
                int a = stack.pollFirst();
                int b = stack.pollFirst();
                int c = (b - a);
                stack.offerFirst(c);
            }else{
                stack.offerFirst(Integer.parseInt(tokens[i]));
            }
        }
        return stack.peekFirst();
    }
}
