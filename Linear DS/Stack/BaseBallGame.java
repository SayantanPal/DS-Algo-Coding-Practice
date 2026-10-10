import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/baseball-game/description/
public class BaseBallGame {
    public int calPoints(String[] operations) {
        Deque<String> stack = new ArrayDeque<>();

        for(String s: operations){
            if(s.equals("C")){
                if(!stack.isEmpty()) stack.pop();
            }else if(s.equals("D")){
                if(!stack.isEmpty()) stack.push(String.valueOf(2 * Integer.parseInt(stack.peek())));
            }else if(s.equals("+")){
                int a = 0, b = 0;
                if(!stack.isEmpty()) a = Integer.parseInt(stack.pop());
                if(!stack.isEmpty()) b = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(b));
                stack.push(String.valueOf(a));
                stack.push(String.valueOf(a + b));
            }else stack.push(s);
        }

        int sum = 0;
        while(!stack.isEmpty()) sum += Integer.parseInt(stack.pop());

        return sum;
    }
}
