public class MinStackUsingArray {

    private final int MAX_STACK_SIZE = 1000000; // 10^6
    private int[] stackImpl = new int[MAX_STACK_SIZE + 1];
    private int[] minElemTracker = new int[MAX_STACK_SIZE + 1];
    private int minElem = Integer.MAX_VALUE; // set the tracker that no min visited yet
    private int tos = -1;

    public void push(int x) {
        if(tos == MAX_STACK_SIZE) return; // check overflow
        tos++;
        stackImpl[tos] = x;

        // below extra for min stack impl
        minElem = Math.min(minElem, x);
        minElemTracker[tos] = minElem;
    }

    public boolean isEmpty(){
        return tos == -1;
    }

    public void pop() {
        if(isEmpty()) return; // check underflow
        tos--; // delete the element

        // below extra for min stack impl
        if(isEmpty()) minElem = Integer.MAX_VALUE; // reset the tracker that no min elem visited yet
        else minElem = minElemTracker[tos]; // reset the tracker to the current min elem post deletion
    }

    public int top() {
        if(isEmpty()) return -1; // check underflow
        return stackImpl[tos];
    }

    public int getMin() {
        if(isEmpty()) return -1; // check underflow
        return minElemTracker[tos];
    }
}
