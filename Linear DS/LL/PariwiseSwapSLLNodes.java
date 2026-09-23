import model.SLLNode;

public class PariwiseSwapSLLNodes {

    public SLLNode swap(SLLNode node1, SLLNode node2){
        node1.next = node2.next;
        node2.next = node1;
        return node2;
    }

    public SLLNode swapPairs(SLLNode A) {
        SLLNode dummy = new SLLNode(-1);
        dummy.next = A;
        SLLNode curr = dummy;
        while(curr.next!=null && curr.next.next != null){
            SLLNode node1 = curr.next;
            SLLNode node2  = curr.next.next; // A.next
            curr.next = swap(node1, node2);

            curr = curr.next.next;
        }
        return dummy.next;
    }
}
