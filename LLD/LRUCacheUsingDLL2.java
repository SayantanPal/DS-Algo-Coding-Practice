import java.util.HashMap;
import java.util.Map;

// Link: https://leetcode.com/problems/lru-cache/
public class LRUCacheUsingDLL2 {
    class Node{
        Node prev, next;
        int key, data;
        Node(int key, int data){
            this.key = key;
            this.data = data;
        }

        Node(int data){
            this.data = data;
        }
    }
    int capacity;
    Node head, tail;
    HashMap<Integer, Node> map = new HashMap<>();
    public LRUCacheUsingDLL2(int capacity) {
        this.capacity = capacity;

        // making a sentinel list
        this.head = new Node(-1);
        this.tail = new Node(-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        deleteNode(node);
        insertNodeBeforeTail(node);
        return node.data;
    }

    public void put(int key, int value) {
        Node node = null;
        if(!map.containsKey(key)){
            node = new Node(key, value);
            if(map.size() + 1 > this.capacity){
                Node evictedNode = deleteNodeAfterHead();
                map.remove(evictedNode.key); // VERY IMPORTANT
            }
        }else{
            node = map.get(key);
            node.data = value;
            deleteNode(node);
        }
        insertNodeBeforeTail(node);
        map.put(key, node);
    }

    private void deleteNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void insertNodeBeforeTail(Node node){
        Node before = tail.prev;
        before.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = before;
    }

    private Node deleteNodeAfterHead(){
        Node after = this.head.next;
        deleteNode(after);
        return after;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */