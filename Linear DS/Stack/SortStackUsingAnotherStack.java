import java.util.ArrayDeque;
import java.util.Deque;

public class SortStackUsingAnotherStack {

    public int[] solve(int[] A) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        for(int num: A) stack1.push(num);

        while(!stack1.isEmpty()){
            int elem = stack1.pop();
            while(!stack2.isEmpty() && stack2.peek() > elem){
                stack1.push(stack2.pop());
            }
            stack2.push(elem);
        }

        int j = A.length - 1;
        int[] ans = new int[A.length];
        while(!stack2.isEmpty()){
            ans[j--] = stack2.pop();
        }

        return ans;
    }
}
