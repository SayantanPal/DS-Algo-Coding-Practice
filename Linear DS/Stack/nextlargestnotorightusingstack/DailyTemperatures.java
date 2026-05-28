package nextlargestnotorightusingstack;

import java.util.ArrayDeque;
import java.util.Deque;

// Link: https://leetcode.com/problems/daily-temperatures/
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> monotonicDecreasingDeque = new ArrayDeque<>();
        int[] answer = new int[temperatures.length];
        for(int i = temperatures.length - 1; i >=0; i--){
            while(!monotonicDecreasingDeque.isEmpty() && monotonicDecreasingDeque.peekFirst()[0] <= temperatures[i]){
                monotonicDecreasingDeque.pollFirst();
            }
            if(monotonicDecreasingDeque.isEmpty()){
                answer[i] = 0;
            }else{
                answer[i] = monotonicDecreasingDeque.peekFirst()[1] - i;
            }
            monotonicDecreasingDeque.offerFirst(new int[]{temperatures[i], i});
        }
        return answer;
    }

    public int[] dailyTemperatures_v2(int[] temperatures) {
        Deque<Integer> monotonicDecreasingDeque = new ArrayDeque<>(); // store index
        int[] answer = new int[temperatures.length];
        for(int i = temperatures.length - 1; i >=0; i--){
            while(!monotonicDecreasingDeque.isEmpty() && temperatures[monotonicDecreasingDeque.peekFirst()] <= temperatures[i]){
                monotonicDecreasingDeque.pollFirst();
            }
            if(monotonicDecreasingDeque.isEmpty()){
                answer[i] = 0;
            }else{
                answer[i] = monotonicDecreasingDeque.peekFirst() - i;
            }
            monotonicDecreasingDeque.offerFirst(i);
        }
        return answer;
    }
}
