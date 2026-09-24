import model.SLLNode;

public class KReverseLinkedList {

    public SLLNode reverseLLBetweenRange(SLLNode head, SLLNode tail){
        if(head == null) return head;
        SLLNode prev = null;
        SLLNode curr = head;
        while(curr != null && curr != tail.next){
            SLLNode after = curr.next;
            curr.next = prev;
            prev = curr;
            curr = after;
        }
        return prev;
    }
    public SLLNode reverseList(SLLNode head, int k) {
        if(k == 1) return head;
        SLLNode dummy = new SLLNode(-1);
        dummy.next = head;
        SLLNode curr = dummy;
        while(curr.next != null){
            SLLNode before = curr;
            SLLNode start = curr.next;
            for(int i = 1; i <= k; i++){
                if(curr.next == null){
                    break;
                }
                curr = curr.next;
            }
            SLLNode end = curr;
            SLLNode after = curr.next;

            before.next = null;
            end.next = null;

            SLLNode reversedhead = reverseLLBetweenRange(start, end);

            before.next = reversedhead;
            start.next = after;
            curr = start;
        }
        return dummy.next;
    }
}
