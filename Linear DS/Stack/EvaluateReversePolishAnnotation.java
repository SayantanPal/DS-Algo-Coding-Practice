import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishAnnotation {
    public int evalRPN(String[] tokens) {
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
