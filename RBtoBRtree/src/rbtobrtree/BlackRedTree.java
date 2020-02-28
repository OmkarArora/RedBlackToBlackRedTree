package rbtobrtree;

import java.util.List;

public class BlackRedTree {
    private Node Tnil;
    private Node root;
    public BlackRedTree(){
        Tnil = new Node();
        Tnil.color = "red";
        Tnil.left = null;
        Tnil.right = null;
        root = Tnil;
    }
    void insertBR(Node z){
        //System.out.println("Inserting - " + z.data);
        //Phase 1: Find insert position
        z.parent = null;
        z.left = Tnil;
        z.right = Tnil;
        z.color = "black";
        Node y = null;
        Node x = root;
        while(x != Tnil){
            y = x;
            if(z.data < x.data){
                x = x.left;
            }
            else{
                x = x.right;
            }
        }
        z.parent = y;
        if(y==null){
            root = z;
        }
        else if(z.data < y.data){
            y.left = z;
        }
        else{
            y.right = z;
        }
       
        if (z.parent == null) {
            z.color = "red";
            return;
        }
        if (z.parent.parent == null) {
          return;
        }
        insertFixUpBR(z);
    }
    void insertFixUpBR(Node z){
        Node y;
        //Phase 2: Insertion and Fix
        while(z.parent.color.equals("black")){
            if(z.parent == z.parent.parent.left){ 
                y = z.parent.parent.right; //declare parent sibling
                if(y.color.equals("black")){
                    y.color = "red";
                    z.parent.color = "red";
                    z.parent.parent.color = "black";
                    z = z.parent.parent;
                }
                else{ 
                    if(z == z.parent.right){
                        z = z.parent;
                        leftRotateBR(z);
                    }
                    z.parent.color = "red";
                    z.parent.parent.color = "black";
                    rightRotateBR(z.parent.parent);
                }
                }
            
            else if(z.parent == z.parent.parent.right){
                y = z.parent.parent.left; //declare parent sibling
                if(y.color.equals("black")){
                    y.color = "red";
                    z.parent.color = "red";
                    z.parent.parent.color = "black";
                    z = z.parent.parent;
                }
                else{
                    if(z == z.parent.left){
                        z = z.parent;
                        rightRotateBR(z);
                    }
                    z.parent.color = "red";
                    z.parent.parent.color = "black";
                    leftRotateBR(z.parent.parent);
                }
            }
            if(z == root){
                break;
            }
        }
        root.color = "red";
    }
    void leftRotateBR(Node x){
        Node y = x.right;
        x.right = y.left;
        if(y.left != Tnil){
            y.left.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        }
        else{
            if(x.parent.left == x)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }
    
    void rightRotateBR(Node x){
        Node y = x.left;
        x.left = y.right;
        if(y.right != Tnil){
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(x.parent == null){
            root = y;
        }
        else{
            if(x.parent.left == x)
                x.parent.left = y;
            else
                x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }
    
    public List<Node> inOrderTraversal(Node node, List<Node> inOrderList){
        if(node == Tnil)
            return inOrderList;
        inOrderList = inOrderTraversal(node.left, inOrderList);
        inOrderList.add(node);
        inOrderList = inOrderTraversal(node.right, inOrderList);
        return inOrderList;
    }
       
    public int getRedHeight(){
    int Hr = 0;
    Node x = root;
    while(x.left != Tnil){
        x = x.left;
        if(x.color.equals("red")){
            Hr++;
        }
    }
        
        return Hr;
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    public Node getTnil(){
        return this.Tnil;
    }
    
    int spaceIncrement = 10;
    private void drawTreeUtil(Node node,int space){
        if(node == Tnil || node == null){
            return;
        }
        
        space += spaceIncrement;
        
        drawTreeUtil(node.right, space);
        System.out.print("\n");
        
        for(int i=spaceIncrement;i<space;i++){
            System.out.print(" ");
        }
        if(node == root){
            System.out.print(node + "\n  ROOT");
        }
        else
            System.out.print(node + "\n");
        
        drawTreeUtil(node.left, space);
    }
    
    public void drawTree(){
        drawTreeUtil(root, 0);
    }
}
