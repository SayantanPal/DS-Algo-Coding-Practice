package adjpairmatch;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://www.geeksforgeeks.org/problems/expression-contains-redundant-bracket-or-not/1
public class CheckRedundantParenthesis {

    public int braces(String A) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c: A.toCharArray()){
            if(c == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){ // there is no operator between paired parenthesis
                    return 1; // hence clearly redundant
                }else{
                    while(!stack.isEmpty() && stack.peek() != '('){
                        stack.pop();
                    }
                    stack.pop(); // remove '(' as well and its corresponing c ie ')' was never pushed - so everything in between popped out
                }
            }else if(c == '(' || c == '+' || c == '-' || c == '*' || c == '/'){
                stack.push(c);
            }
        }
        return 0;
    }
}
