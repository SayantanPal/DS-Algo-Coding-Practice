import java.util.*;

public class LRUCacheUsingDLL {
    private int capacity;
    private Map<Integer, Node> map; // Use a HashMap for key-value storage.

    // Maintain order using a Doubly Linked List.
    class Node{
        int key,val;
        Node prev, next;

        Node(int key, int val)
        {
            this.key = key;
            this.val = val;
//            this.prev = null;
//            this.next = null;
        }
    }

    Node head, tail;

    LRUCacheUsingDLL(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
    }

    //Tracking consist of 2 part
    //1) delete a particular Node from anywhere
    //2) insert at tail( basical most recently used element at rear/tail end)

    // it always expects to have a valid non null node
    private void deleteAParticularNode(Node node)
    {
        if(node != null){
            Node beforeNode = node.prev;
            Node afterNode = node.next;

            System.out.println("Before Node while deleting as part of tracking: "+ beforeNode);
            System.out.println("After Node while deleting as part of tracking: "+ afterNode);

            if(beforeNode == null &&  afterNode == null){ // Only Single node in DLL
                head = tail = null;
                return;
            }
            if(beforeNode == null) // Node is at head
            {
                deleteAtHead();
                return;
            }

            if(afterNode == null) // Node is at tail
            {
                deleteAtTail();
                return;
            }

            node.prev = null;
            node.next = null;
            beforeNode.next = afterNode;
            afterNode.prev = beforeNode;

        }

    }

    private void insertNodeAtTail(Node node){
         if(tail == null) {
             if(head == null) {
                 head = tail = node;
                 node.prev = null;
                 node.next = null;
             } else{
                 node.prev = head;
                 node.next = null;
                 head.next = node;
                 tail = node;
             }
         } else{
             tail.next = node;
             node.prev = tail;
             node.next = null;
             tail = node;
         }
    }

    private void maintainTracking(Node node, boolean isNewNode){
        if(!isNewNode)
            deleteAParticularNode(node);
        insertNodeAtTail(node);
    }

    private void maintainTracking(Node node){
        System.out.println("Incoming Node for tracking: " + node+"(" + node.key +")");
        if(map.containsKey(node.key)) { // this is also cond to check if the element is in DLL tracker
            deleteAParticularNode(node);
            System.out.println("After deleting the node, DLL looks like:- ");
            this.displayDLL();
        } else{
            System.out.println("Incoming Node not present to be deleted from DLL");
            this.displayDLL();
        }
        insertNodeAtTail(node);
        System.out.println("\nAfter inserting the node at tail MRU, DLL looks like:- ");
        this.displayDLL();
    }

    // eviction method

    private void silentEviction(){
        // its basically deleteAthead
        if(head != null)
        {
            map.remove(head.key);
        }
        deleteAtHead();

        // we also have to remove element from the map

    }

    private void deleteAtHead(){
        if(head != null){
            Node nextTohead = head.next;
            head.prev = null;
            head.next = null;
            head = nextTohead;
            if(nextTohead != null)
                nextTohead.prev = null;
        }
        else {
            tail = null;
        }
    }

    private void deleteAtTail(){
        if(tail != null){
            Node beforeTail = tail.prev;
            if(beforeTail != null)
                beforeTail.next = null;
            tail.next = null;
            tail.prev = null;
            tail = beforeTail;
        }
        else {
            head = null;
        }
    }

    public int get(int key){
        System.out.println("\n\n################FETCH FROM CACHE KEY: " + key + " ################");
        if(!map.containsKey(key)) return -1;

        // maintainTracking(map.get(key));
        Node node = map.get(key);
        maintainTracking(node);

        return node.val;
    }

    public void put(int key, int val){
        System.out.println("\n\n################PUTTING TO CACHE KEY: " +key + " : VAL: " + val +" ################");
        Node node = null;
        if(! map.containsKey(key)){ // new elem
            node = new Node(key , val); // new node creation only when node is new(NOT for existing node with same key)
            if(map.size() + 1 > capacity){ // check capacity only for new elem
                silentEviction();
            }
            // we donot need to handle anything extra if within capacity for new elem
        } else{ // existing elem
            node = map.get(key); // instead of creating new node for existing key, fetch node obj from
            node.val = val; // for existing elem with same key, value needs to be mandatorily changed to reflect the updated value for same key
            // we donot need to check capacity for existing element
        }

        maintainTracking(node);
        map.put(key, node);
    }

    public void displayMap(){
        System.out.println("\n\nPrinting Step By Step: ");
        for (Map.Entry<Integer, Node> entry : this.map.entrySet() ) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " containing data " + entry.getValue().val);
        }
    }

    public void displayDLL(){
        Node node = this.head;
        System.out.print("DLL Tracker: ");
        while(node != null){
            System.out.print( node.toString() + "("+ node.key + ") ");
            node = node.next;
        }
        System.out.println("\nhead: " + this.head);
        System.out.println("tail: " + this.tail);
    }

    public void display()
    {
        displayMap();
        displayDLL();
    }
}
