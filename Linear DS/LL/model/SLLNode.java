package model;

public class SLLNode {
    public int data;
    public SLLNode next, random, down;

    public SLLNode(int data){
        this.data = data;
        this.next = null;
        this.random = null;
        this.down = null;
    }
}
