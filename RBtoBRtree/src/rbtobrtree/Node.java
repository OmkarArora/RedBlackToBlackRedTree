package rbtobrtree;

public class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    String color;
    
    public String toString(){
        return (data + "-"+ color);
    }
}
