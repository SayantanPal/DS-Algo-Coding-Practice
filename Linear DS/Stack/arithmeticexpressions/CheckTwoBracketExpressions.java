package arithmeticexpressions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


/*
* Check two bracket expressions
Problem Description

Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.
The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.
ASSUMPTION NOTE 1: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’,
ASSUMPTION NOTE 2: every operand appears only once.
ASSUMPTION NOTE 3: All operators include only + or - apart from parenthesis.
ASSUMPTION NOTE 4: There are no extra redundant or extra outside parenthesis
ASSUMPTION NOTE 5: The arithmetic expressions are all valid expressions with valid parenthesis

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

    public HashMap<Character, Character> parsedSimplifiedStr(String str){
        HashMap<Character, Character> map = new HashMap<>();
        Deque<Character> operatorStack = new ArrayDeque<>();
        // Optional:
        // StringBuilder simplified = new StringBuilder();
        char lastOperator = '+';
        for(char ch: str.toCharArray()){
            if(ch >= 'a' && ch <= 'z'){ // when character is operand
                // maintain operator associated with unique second operand in map
                // for very first operand it is default initialized lastOperator
                map.put(ch, lastOperator);

                // optional: add answer of associated operator with second operand to result
//                    simplified.append(lastOperator);
//                    simplified.append(ch);
            }else if(ch == '('){
                // only maintain the assiociated left operators ie on the left side of EACH OPEN parenthesis
                // eg: plus for +(.. or minus for -(..
                // for very first parenthesis, the default lastOperator is plus
                operatorStack.push(lastOperator); // in case of any earlier domino effect, the toggled character state is maintained in stack
            }else if(ch == ')'){
                if(!operatorStack.isEmpty()){ // ideally since expression is valid, with each closing parenthesis, there is an associated operator to the left of its matching open parenthesis maintained in stack
                    operatorStack.pop(); // remove the left-side operator (+ or -) corresponding to its current matching open parenthesis
                    if(!operatorStack.isEmpty()){ // when the closing parenthesis is the nested one deep down and there are more closing parenthesis yet to come to match outer open parenthesis
                        lastOperator = operatorStack.peek(); // set the last operator to the associated left sign of its immediate outer parenthesis
                    }else{ // if there are no more outer parenthesis, drfault sign of the outer world becomes reset to +ve
                        lastOperator = '+'; // also default sign outside any parenthesis when -ve explicitly not mentioned
                    }
                }
            } else{ // when character is either operator
                // domino effect of toggling current operator only when the most recent last operator is -ve
                if(!operatorStack.isEmpty() && operatorStack.peek() == '-'){
                    if(ch == '+') ch = '-';
                    else ch = '+';
                }
                lastOperator = ch; // without domino effect, current operator sign remains as it is and updated as well
            }
        }
        // optional:
        // System.out.println(simplified.toString()); // resultant string will have starting with explicit signs like explcit + for any expression starting with plus
        return map;
    }
    public int solve(String A, String B) {

        HashMap<Character, Character> mapA = parsedSimplifiedStr(A);
        HashMap<Character, Character> mapB = parsedSimplifiedStr(B);

        // neither A should have more characters than B nor B should have more character in A
        if(mapA.size() != mapB.size()) return 0;

        // when character count is same in both A and B
        // just check if they are same characters
        for(char ch: mapA.keySet()){
            if(mapA.get(ch) != mapB.getOrDefault(ch, '$')) return 0;
        }

        return 1;
    }
}
