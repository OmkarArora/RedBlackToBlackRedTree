package rbtobrtree;

import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    private Node Tnil;
    private Node root;

    public RedBlackTree(){
        Tnil = new Node();
        Tnil.color = "black";
        Tnil.left = null;
        Tnil.right = null;
        root = Tnil;
    }
    void insertRB(Node z){
        //System.out.println("Inserting - " + z.data);
        //Phase 1: Find insert position
        z.parent = null;
        z.left = Tnil;
        z.right = Tnil;
        z.color = "red";
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
            z.color = "black";
            return;
        }
        if (z.parent.parent == null) {
          return;
        }
        insertFixUpRB(z);
    }
    void insertFixUpRB(Node z){
        Node y;
        //Phase 2: Insertion and Fix
        while(z.parent.color.equals("red")){
            if(z.parent == z.parent.parent.left){ 
                y = z.parent.parent.right; //declare parent sibling
                if(y.color.equals("red")){
                    y.color = "black";
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                }
                else{ 
                    if(z == z.parent.right){
                        z = z.parent;
                        leftRotateRB(z);
                    }
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    rightRotateRB(z.parent.parent);
                }
                }
            
            else if(z.parent == z.parent.parent.right){
                y = z.parent.parent.left; //declare parent sibling
                if(y.color.equals("red")){
                    y.color = "black";
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                }
                else{
                    if(z == z.parent.left){
                        z = z.parent;
                        rightRotateRB(z);
                    }
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    leftRotateRB(z.parent.parent);
                }
            }
            if(z == root){
                break;
            }
        }
        root.color = "black";
    }
    void leftRotateRB(Node x){
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
    
    void rightRotateRB(Node x){
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
    public void deleteRB(int deleteValue){
        Node z = searchRB(deleteValue);
        if(z == null){
            System.out.println("Node not found, delete failed");
            return;
        }
        
        Node y = z;
        Node x;
        String yOriginalColor = y.color;
        if(z.left == Tnil){
            x = z.right;
            transplantRB(z,z.right);
        }
        else if(z.right == Tnil){
            x = z.left;
            transplantRB(z,z.left);
        }
        else{
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if(y.parent == z){
                x.parent = y;
            }
            else{
                transplantRB(y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplantRB(z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(yOriginalColor.equals("black")){
            //System.out.println("\nDelete fixing - " + x);
            RBTreeDeleteFixUp(x);
        }
    }
   
    public void transplantRB(Node u, Node v){
        if(u.parent == null){
            root = v;
        }
        else if(u == u.parent.left){
            u.parent.left = v;
        }
        else{
            u.parent.right = v;
        }
        v.parent = u.parent;
    }
    
 
    public void RBTreeDeleteFixUp(Node x){
        Node w;
        while(x!=root && x.color.equals("black")){
            if(x == x.parent.left){
                w = x.parent.right;
                if(w.color.equals("red")){
                    w.color = "black";
                    x.parent.color = "red";
                    leftRotateRB(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color.equals("black") && w.right.color.equals("black")){
                    w.color = "red";
                    x = x.parent;
                }
                else{
                    if(w.right.color.equals("black")){
                        w.left.color = "black";
                        w.color = "red";
                        rightRotateRB(w);
                        w = x.parent.right;
                    }
                
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.right.color = "black";
                    leftRotateRB(x.parent);
                    x = root;
                }
            }
            else{
                w = x.parent.left;
                if(w.color.equals("red")){
                    w.color = "black";
                    x.parent.color = "red";
                    rightRotateRB(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color.equals("black") && w.left.color.equals("black")){
                    w.color = "red";
                    x = x.parent;
                }
                else{ 
                    if(w.left.color.equals("black")){
                        w.right.color = "black";
                        w.color = "red";
                        leftRotateRB(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.left.color = "black";
                    rightRotateRB(x.parent);
                    x = root;
                }
            }
        }
        x.color = "black";
    }
    
    public Node treeMinimum(Node x){
        while(x.left != Tnil){
            x = x.left;
        }
        return x;
    }
    
    public Node searchRB(int searchValue){
        Node x = root;
        while(x != Tnil){
            if(x.data == searchValue){
                //System.out.println("Node found");
                return x;
            }
            else if(x.data > searchValue){//traverse left subtree
                x = x.left;
            }
            else if(x.data < searchValue){//traverse right subtree
                x = x.right;
            }
        }
        return null;
    }
     
    public int getBlackHeight(){
        int Hb = 0;
        Node x = root;
        while(x.left != Tnil){
            x = x.left;
            if(x.color.equals("black")){
                Hb++;
            }
        }
        
        return Hb;
    }
    
    public List<Node> inOrderTraversal(Node node, List<Node> inOrderList){
        if(node == Tnil)
            return inOrderList;
        inOrderList = inOrderTraversal(node.left, inOrderList);
        inOrderList.add(node);
        inOrderList = inOrderTraversal(node.right, inOrderList);
        return inOrderList;
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
    
    public void convertRBtoBR(Node node){
        if(node == root){
            node.color = "red";
            Tnil.color = "red";
        }
        if(node == Tnil)
            return;
        else if(node.left == Tnil && node.right == Tnil){
            return;
        }
        else if(node.left == Tnil && node.right != Tnil){
            convertRBtoBR(node.right);
            node.color = "red";
            node.right.color = "black"; 
        }
        else if(node.left != Tnil && node.right == Tnil){
            convertRBtoBR(node.left);
            node.color = "red";
            node.left.color = "black";   
        }
        else{
            convertRBtoBR(node.left);
            convertRBtoBR(node.right);
            if(node.color.equals("black")){
                if(node.left.color.equals("black") || node.right.color.equals("black"))
                    node.color = "red";
            }
            Node tempL = node;
            Node tempR = node;
            int leftRedHeight = 0;
            int rightRedHeight = 0;
            while(tempL.left != Tnil){
                tempL = tempL.left;
                if(tempL.color.equals("red"))
                    leftRedHeight++;   
            }
            while(tempR.right != Tnil){
                tempR = tempR.right;
                if(tempR.color.equals("red"))
                    rightRedHeight++;   
            }
            if(leftRedHeight == rightRedHeight)
                return;
            else{
                if(leftRedHeight > rightRedHeight){
                    int diff = leftRedHeight - rightRedHeight;
                    incrementRedHeight(node.right,diff);
                }
                else{
                    int diff =  rightRedHeight - leftRedHeight;
                    incrementRedHeight(node.left,diff);
                }
            }
                
        }
    }
    
    private void incrementRedHeight(Node node, int diff){
        if(diff == 0){
            return;
        }
        if(node.color.equals("black")){
            node.color = "red";
            diff--;
            incrementRedHeight(node, diff);
        }
        else{
            incrementRedHeight(node.left, diff);
            incrementRedHeight(node.right, diff);
        }
    }
}