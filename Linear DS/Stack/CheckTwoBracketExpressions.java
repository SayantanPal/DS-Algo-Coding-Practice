import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


/*
* Check two bracket expressions
Problem Description

Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.
The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.
NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’, and every operand appears only once.

Problem Constraints
1 <= length of the each String <= 100

Input Format
The given arguments are string A and string B.

Output Format
Return 1 if they represent the same expression else return 0.


Example Input
Input 1:
 A = "-(a+b+c)"
 B = "-a-b-c"

 *
Input 2:
 A = "a-b-(c-d)"
 B = "a-b-c-d"


Example Output
Output 1: 1
Output 2: 0


Example Explanation:
Explanation 1: The expression "-(a+b+c)" can be written as "-a-b-c" which is equal as B.
Explanation 2: Both the expression are different.
*
*
* */
public class CheckTwoBracketExpressions {

    public HashMap parsedSimplifiedStr(String str){
        HashMap<Character, Character> map = new HashMap<>();
        Deque<Character> operatorStack = new ArrayDeque<>();
        StringBuilder simplified = new StringBuilder();
        char lastOperator = '+';
        for(char ch: str.toCharArray()){
            if(ch == '('){
                operatorStack.push(lastOperator);
            }else if(ch == ')'){
                if(!operatorStack.isEmpty()){
                    operatorStack.pop();
                    if(!operatorStack.isEmpty()){
                        lastOperator = operatorStack.peek();
                    }else{
                        lastOperator = '+';
                    }
                }
            } else{
                if(ch == '+'){
                    if(!operatorStack.isEmpty() && operatorStack.peek() == '-'){
                        ch = '-';
                    }
                    lastOperator = ch;

                }else if(ch == '-'){
                    if(!operatorStack.isEmpty() && operatorStack.peek() == '-'){
                        ch = '+';
                    }
                    lastOperator = ch;
                }
                else{
                    map.put(ch, lastOperator);
                    simplified.append(lastOperator);
                    simplified.append(ch);
                }

            }
        }
        // System.out.println(simplified.toString());
        return map;
    }
    public int solve(String A, String B) {

        HashMap<Character, Character> mapA = parsedSimplifiedStr(A);
        HashMap<Character, Character> mapB = parsedSimplifiedStr(B);

        if(mapA.size() != mapB.size()) return 0;

        for(char ch: mapA.keySet()){
            if(mapA.get(ch) != mapB.getOrDefault(ch, '$')) return 0;
        }

        return 1;
    }
}
