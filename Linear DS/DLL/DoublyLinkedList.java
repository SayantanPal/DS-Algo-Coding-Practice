import model.Node;

public class DoublyLinkedList {

    public static Node insertAtHead(int val, Node head){
        Node newNode = new Node(val);

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

        Node traversal = head;
        while(traversal.next!=null){
            traversal = traversal.next;
        }

        // endNode <-(prev)-newNode-> xx
        // endNode-(next)-> newNode-> xx

        traversal.next = newNode;
        newNode.prev = traversal;
        newNode.next = null;

        return head;
    }

    // whatever elem is at kth postion, push kth elem to (k+1)th position
    static Node insertAtKthPositionIndex(int k, int val, Node head) {

        // Write your code here.
        Node traversal = head;

        if(k == 0){ //k at head (edge case)
            return insertAtHead(val, head);
        }

        // normal cases
        // 0,1,....,(k - 1)th, kth[traversal]
        for(int counter = 0; counter < k; counter++){ // at (k-1)th postion, traverse to next kth position and stop
            traversal = traversal.next;// 5, 4, 3, 2, 1, 0
        }
        // traversal points at kth position here

        if(traversal == null){ //k at end (edge case)
            return insertAtTail(val, head);
        }


        Node beforeKthPos = traversal.prev;
        Node afterK = traversal; // before operations this was at kth postion. After operation this becomes k+1th position
        Node kthPositionNewNode = new Node(val);

        // xx <-(prev)- beforeKthPos -(next)-> kthPositionNewNode(new elem)-(next)-> afterK-(next)-> xx
        // xx <-(prev)- beforeKthPos  <-(prev)-kthPositionNewNode(new elem) <-(prev)-afterK-(next)-> xx

        beforeKthPos.next = kthPositionNewNode;
        kthPositionNewNode.prev = beforeKthPos;

        kthPositionNewNode.next = afterK;
        afterK.prev = kthPositionNewNode;

        return head;
    }

    static Node deleteAtHead(Node head, int pos){
        Node nextToHead = head.next;
        nextToHead.prev = null;

        head.prev = null;
        head.next = null;
        head = nextToHead;

        return head;
    }

    static Node deleteAtTail(Node head, int pos){
        Node traversal = head;
        while(traversal.next!=null){ // traversal is last node
            traversal = traversal.next;
        }

        Node beforeK = traversal.prev;
        beforeK.next = null;
        traversal.prev = null;

        return head;
    }

    static void deleteAParticularNode(Node nodeToBeDeleted){
        Node beforeK = nodeToBeDeleted.prev;
        Node afterK = nodeToBeDeleted.next;

        beforeK.next = afterK;
        afterK.prev = beforeK;

        nodeToBeDeleted.prev = null;
        nodeToBeDeleted.next = null;
    }

    static Node deleteNodeAtPos(Node head, int pos) {
        // Write your code here.
        Node traversal = head;

        if(pos == 0){
            return deleteAtHead(head, pos);
        }

        // we should point to exactly at kth position via traversal
        for(int counter = 0; counter < pos; counter++){
            traversal = traversal.next;
        }

        if(traversal.next == null){ // if traversal is last node
            return deleteAtTail(head, pos);
        }

//        Node beforeK = traversal.prev;
//        Node afterK = traversal.next;
//
//        beforeK.next = afterK;
//        afterK.prev = beforeK;
//
//        traversal.prev = null;
//        traversal.next = null;

        deleteAParticularNode(traversal);

        return head;
    }

    public static void swap(Node a, Node b){
        int temp = (int) a.dataVal;
        a.dataVal = b.dataVal;
        b.dataVal = temp;
    }

    public static Node inPlaceReverseDLL(Node head)
    {
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
    public static Node reverseDLL(Node head)
    {
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
