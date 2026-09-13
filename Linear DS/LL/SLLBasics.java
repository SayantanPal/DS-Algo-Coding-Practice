import model.SLLNode;

import java.util.HashMap;

public class SLLBasics {

    // Function to insert a node at the end of the linked list.
    SLLNode insertAtEndTailReadLast(SLLNode head, int x) {
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

    // insert at head is equivalent to insert before existing head
    SLLNode insertAtFrontHeadFirst(SLLNode head, int x) {
        // code here
        if(head == null){
            return head = new SLLNode(x);
        }
        SLLNode newNode = new SLLNode(x);
        newNode.next = head; // connect new node to existing old head
        return head = newNode; // new node becomes the new head
    }

    SLLNode removeFrontHeadFirst(SLLNode head) {
        if(head == null) return head;
        head = head.next;
        return head;
    }

    SLLNode removeEndTailRearLast_v2(SLLNode head){
        if(head == null) return head;
        if(head.next == null) return head = null;

        SLLNode curr = head;
        while(curr.next.next != null){
            curr = curr.next; // LL traversal: curr point to node previous to tail
        }
        curr.next = null;
        return head;
    }


    SLLNode removeEndTailRearLast(SLLNode head){
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

    SLLNode insertBeforeIndex(SLLNode head, int data, int toBeInsertedBeforeIndex) {
        // code here
        SLLNode newNode = new SLLNode(data);
        if(head == null){ // insert at head
            return head = newNode;
        }
        if(toBeInsertedBeforeIndex == 0){ // insert left to existing head
            newNode.next = head; // connect new node to existing old head
            return head = newNode; // new node becomes the new head
        }

        // insert after current index node starting from head
        SLLNode curr = head;
        int i = 0;
        //  before target index, the index at which needs to be inserted is (toBeInsertedBeforeIndex - 2)
        while(curr.next != null && i < toBeInsertedBeforeIndex - 1){ // at end of loop, curr points to toBeInsertedBeforeIndex - 1 ie 1 node index before toBeInsertedBeforeIndex
            curr = curr.next;
            i++;
        }
        SLLNode nextListNodeChain = curr.next;
        curr.next = newNode;
        newNode.next = nextListNodeChain;
        return head;
    }

    SLLNode deleteAtIndex_v2(SLLNode head, int toBeDeletedIndex) {
        if(head == null) return null;
        if(toBeDeletedIndex == 0){
            head = head.next;
            return head;
        }
        SLLNode curr = head;
        int i = 0;

        // go one step before index 'toBeDeletedIndex' where toBeDeletedIndex is to be deleted
        while(curr.next != null && i < toBeDeletedIndex - 1){ // at end of loop, curr points to toBeDeletedIndex - 1 ie 1 node index before toBeDeletedIndex
            curr = curr.next;
            i++;
        }
        curr.next = curr.next.next;
        return head;
    }


    SLLNode deleteAtIndex(SLLNode head, int toBeDeletedIndex) {
        if(head == null) return null;
        if(toBeDeletedIndex == 0){
            head = head.next;
            return head;
        }
        SLLNode curr = head;
        SLLNode prev = null;
        int i = 0;
        // go one step before index 'toBeDeletedIndex' so that on exiting loop, curr points at 'toBeDeletedIndex' and prev points 1 node index before 'toBeDeletedIndex'
        while(curr.next != null && i < toBeDeletedIndex){
            prev = curr;
            curr = curr.next;
            i++;
        }
        prev.next = curr.next;
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
        if(node.next != null) {
            node.data = node.next.data;
            node.next = node.next.next;
        }
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

    // for arr: [1, 2, 3 , 4], it will return 3 as middle element/node
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
