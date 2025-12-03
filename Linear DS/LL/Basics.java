import model.SLLNode;

import java.util.HashMap;

public class Basics {

    // Function to insert a node at the end of the linked list.
    SLLNode insertAtEnd(SLLNode head, int x) {
        // code here
        if(head == null){
            return head = new SLLNode(x);
        }
        SLLNode curr = head;
        while(curr.next != null){ // SLL traversal
            curr = curr.next;
        }
        curr.next = new SLLNode(x); // insert at the end
        return head;
    }

    SLLNode insertAtFront(SLLNode head, int x) {
        // code here
        if(head == null){
            return head = new SLLNode(x);
        }
        SLLNode curr = new SLLNode(x);
        curr.next = head; // insert at the end
        return head = curr;
    }



    SLLNode removeHead(SLLNode head) {
        if(head == null) return head;
        head = head.next;
        return head;
    }

    SLLNode removeTail(SLLNode head){
        if(head == null) return head;

        SLLNode curr = head;
        SLLNode prev = head;
        while(curr.next != null){
            prev = curr; // point to 1 node before current
            curr = curr.next; // LL traversal: curr point to node which equals nodeValue
        }
        prev.next = null;
        return head;
    }

    SLLNode removeTail_v2(SLLNode head){
        if(head == null || head.next == null) return head;

        SLLNode curr = head;
        while(curr.next.next != null){
            curr = curr.next; // LL traversal: curr point to node previous to tail
        }
        curr.next = null;
        return head;
    }

    // delete input node passed as argument
    // assume that input node definitely exists in SLL
    // node might not be head of SLL - it can be any node
    // node is definitely not tail node, so node.next always exists as not null
    void deleteNode(SLLNode node){
        // approach: if input node is to be deleted,
        // make/clone current input node as next node with data copy
        // drop the next node since current node already behaves as next node
        node.data = node.next.data;
        node.next = node.next.next;
    }

    public static SLLNode detectCycleUsingHashing(SLLNode head) {
        //Your code goes here
        if(head == null) return null;
        HashMap<SLLNode, Integer> map = new HashMap<>();

        while(head.next != null){
            if(!map.containsKey(head)){
                map.put(head, 1);
            } else{
                return head;
            }
            head = head.next;
        }
        return null;
    }

    public static SLLNode findMiddleNode(SLLNode head) {
        if(head == null || head.next == null) return head;
        SLLNode slow = head;
        SLLNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

// Event queue that starts reprocessing old events due to misconfigured pointers.
    public static SLLNode detectCycleUsingFloydCycleOrTortoiseHare(SLLNode head) {
        //Your code goes here

        SLLNode slow = head;
        SLLNode fast = head;

        boolean hasCycle = false;
        while(fast != null && fast.next != null){ // in case of cyclic LL, this condition will never fail
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                hasCycle = true;
                break;
            }
        }

        if(!hasCycle) return null;

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public SLLNode reverseList(SLLNode head) {
        if(head == null) return head;

        SLLNode prev = null;
        SLLNode curr = head;

        while(curr != null){
            SLLNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    public SLLNode reverseListRec(SLLNode head) {
        if(head == null || head.next == null) return head;

        SLLNode newHead = reverseListRec(head.next);
        SLLNode smallerReversedLL = head.next; // initially head.next points to smaller subproblem which is already reversed
        smallerReversedLL.next = head;
        head.next = null;
        return newHead;
    }
}
