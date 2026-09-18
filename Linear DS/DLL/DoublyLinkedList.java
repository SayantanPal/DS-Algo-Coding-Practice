import model.Node;

public class DoublyLinkedList {

    public static Node insertAtHead(Node head, int data){
        Node newNode = new Node(data);

        // xx <-(prev)- newNode -(next)-> head
        // xx <-(prev)- newNode <-(prev)- head
        // xx <-(prev)- newNode[head]

        head.prev = newNode;
        newNode.next = head;
        newNode.prev = null;

        head = newNode;

        return head;
    }

    public static Node insertAtTail(int val, Node head){
        Node newNode = new Node(val);

        Node tail = head;
        while(tail.next!=null){
            tail = tail.next;
        }

        // endNode <-(prev)-newNode-> xx
        // endNode-(next)-> newNode-> xx

        tail.next = newNode;
        newNode.prev = tail;

        return head;
    }

    // whatever elem is at kth postion, push kth elem to (k+1)th position
    public static Node insertAtKthPositionIndex(Node head, int index, int val) {

        // Write your code here.

        if(index == 0){ // head node
            return insertAtHead(head, val);
        }

        Node curr = head;

        // normal cases
        // 0,1,....,(k - 1)th, kth[traversal]
        for(int i = 0; i < index; i++){ // at (k-1)th postion, traverse to next kth position and stop
            curr = curr.next;// 5, 4, 3, 2, 1, 0
        }
        // traversal points at kth position here

        if(curr == null){ // curr points to tail node
            return insertAtTail(val, head);
        }

        Node kthPositionNewNode = new Node(val);

        // xx <-(prev)- beforeKthPos -(next)-> kthPositionNewNode(new elem)-(next)-> afterK-(next)-> xx
        // xx <-(prev)- beforeKthPos  <-(prev)-kthPositionNewNode(new elem) <-(prev)-afterK-(next)-> xx

//        Node beforeKthPos = curr.prev;
//        Node afterK = curr; // before operations this was at kth postion. After operation this becomes k+1th position

//        beforeKthPos.next = kthPositionNewNode;
//        kthPositionNewNode.prev = beforeKthPos;
//
//        kthPositionNewNode.next = afterK;
//        afterK.prev = kthPositionNewNode;

        insertBeforeAParticularNode(curr, kthPositionNewNode);

        return head;
    }


    public static void insertBeforeAParticularNode(Node curr, Node newToBeInserted) {
        if(newToBeInserted == null) return;

        Node before = curr.prev;
        Node after = curr.next;

        before.next = newToBeInserted;
        newToBeInserted.prev = before;

        newToBeInserted.next = after;
        after.prev = newToBeInserted;
    }


    // Link: https://www.naukri.com/code360/problems/insert-at-end-of-doubly-linked-list_10491197?leftPanelTabValue=PROBLEM
    public static Node insertBeforeTail(Node head, int data) {
        if(head == null) {
            Node newNode = new Node(data);
            return newNode;
        }
        // Write your code here
        Node tail = head;
        while(tail.next!=null){
            tail = tail.next;
        }
        Node prev = tail.prev;

        Node newNode = new Node(data);

        // 4 pointer manipulation
        newNode.prev = prev;
        newNode.next = tail;
        prev.next = newNode;
        tail.prev = newNode;

        return head;
    }

    public static Node deleteAtHead(Node head){
        Node afterHead = head.next;
        afterHead.prev = null;
        return head = afterHead; // afterHead becomes head since no pointers pointing to original head now
    }

    public static Node deleteAtTail(Node head){
        Node tail = head;
        while(tail.next!=null){
            tail = tail.next;
        }

        Node beforeTail = tail.prev;
        beforeTail.next = null;
        // tail = beforeTail // beforeTail becomes tail since no pointers pointing to original tail now
        return head;
    }

    public static void deleteAParticularNode(Node nodeToBeDeleted){
        if(nodeToBeDeleted == null) return;
        Node before = nodeToBeDeleted.prev;
        Node after = nodeToBeDeleted.next;

        if(before == null && after == null) return; // single node - set head and tail to null

        if(before == null)
            deleteAtHead(nodeToBeDeleted); // nodeToBeDeleted is the head
        if(after == null)
            deleteAtTail(nodeToBeDeleted); // nodeToBeDeleted is the tail

        before.next = after;
        after.prev = before;

        nodeToBeDeleted.prev = null;
        nodeToBeDeleted.next = null;
    }

    // Link: https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/1
    public static Node deleteNodeAtPosition(Node head, int pos) { // pos is 1-based indexing
        if( head == null || head.next == null) return null;
        if(pos == 1){ // head node
            return deleteAtHead(head);
        }

        // traversal here
        Node curr = head;
        for(int i = 1; i < pos; i++){
            curr = curr.next;
        }

        if(curr.next == null){ // curr points to tail node
            return deleteAtTail(head);
        }

        //curr is pointing at pos-th node
        deleteAParticularNode(curr);

        return head;
    }

    public static void swap(Node a, Node b){
        int temp = (int) a.dataVal;
        a.dataVal = b.dataVal;
        b.dataVal = temp;
    }

    public static Node inPlaceReverseDLL(Node head) {
        // Write your code here.

        Node traversal = head;
        int length = 1;
        while(traversal.next!=null){
            traversal = traversal.next;
            length++;
        }

        Node low = head;
        Node high = traversal;

        for(int i = 0; i < length/2; i++){
            swap(low, high);
            low = low.next;
            high = high.prev;
        }

        return head;
    }

    // Reversing Linked List via pointer manipulation
    public static Node reverseDLL(Node head) {
        // Write your code here.
        Node currentTraversal = head.next;

        // this head will become tail node eventually with reversal
        head.next = null;// earlier head prev was null. Now with reversal, head next is null
        head.prev = currentTraversal; // earlier head next is current node. Now with reversal, head prev is current node



        // Note: we have to perform this till end node where current is last node
        // that means next node becomes null and consecutively the current node for next traversal becomes null
        while(currentTraversal != null){
            // hold ref to next node
            // as reversal of prev & next ptr will forget the next node
            Node nextNode = currentTraversal.next;

            // Get prev node for pointer manipulation
            Node prevNode = currentTraversal.prev;

            // pointer manipulation between current & prev node
            prevNode.prev = currentTraversal;
            currentTraversal.next = prevNode;

            if(nextNode == null){// at this point, current node is the last valid node in traversal and this will become head node
                head = currentTraversal;
            }

            // make next node as current scann node for next itr
            currentTraversal = nextNode;
        }
        return head;
    }
}
