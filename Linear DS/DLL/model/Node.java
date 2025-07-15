package model;

public class Node<T> {

    public Node<T> prev, next;
    public T dataVal;

    public Node(int val) {
    }

    public void Node(T dataVal){
        this.dataVal = dataVal;
    }
}
