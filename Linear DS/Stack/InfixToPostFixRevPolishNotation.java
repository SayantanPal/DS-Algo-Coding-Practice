import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://www.geeksforgeeks.org/problems/infix-to-postfix-1587115620/1
public class InfixToPostFixRevPolishNotation {
    public int getOperatorPriority(char c){
        if(c == '+' || c == '-') return 1;
        else if(c == '*' || c == '/') return 2;
        else if(c == '^') return 3;
        return -1;
    }

    public String solve(String A) {
        Deque<Character> operatorStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for(char c: A.toCharArray()){
            if(c >= 'a' && c <= 'z')
                sb.append(String.valueOf(c));
            else if(c == '(')
                operatorStack.push(c);
            else if(c == ')'){
                while(!operatorStack.isEmpty() && operatorStack.peek() != '('){
                    sb.append(String.valueOf(operatorStack.pop()));
                }
                operatorStack.pop();
            }else{
                while(!operatorStack.isEmpty() && operatorStack.peek() != '(' && getOperatorPriority(operatorStack.peek()) >= getOperatorPriority(c)){
                    sb.append(String.valueOf(operatorStack.pop()));
                }
                operatorStack.push(c);
            }
        }
        while(!operatorStack.isEmpty()){
            sb.append(String.valueOf(operatorStack.pop()));
        }
        return sb.toString();
    }
}
