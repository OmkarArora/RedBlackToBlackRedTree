/*
Author: Omkar Arora
*/
package rbtobrtree;

import java.util.ArrayList;
import java.util.List;

public class RBtoBRtree {
    public static void main(String[] args) {
        RedBlackTree T = new RedBlackTree();
        BlackRedTree brT = new BlackRedTree();
        List<Node> inOrderList = new ArrayList<>();
        
        //Sample Inputs
//        int[] num = {41,38,31,12,19,8};
//        int[] num = {40,30,50,20,60};
//        int[] num = {50,60,40,45,43};
        int[] num = {50,60,30,40,22,75,55,21,19};
        
        //RB tree
        for(int i=0; i<num.length;i++){
            Node z = new Node();
            z.data = num[i];
            T.insertRB(z);
        }
        System.out.println("RB Tree::");
        T.drawTree();

        //RB converted to BR
        System.out.println("----------------------------------------");
        System.out.println("Converted Tree::");
        T.convertRBtoBR(T.getRoot());
        T.drawTree();

        //BR tree
        for(int i=0; i<num.length;i++){
            Node z = new Node();
            z.data = num[i];
            brT.insertBR(z);
        }
        System.out.println("----------------------------------------");
        System.out.println("BR Tree::");
        brT.drawTree();
    }
}

//Sample Output :-
/*
RB Tree::

                    75-red

          60-black

                    55-red

50-black
  ROOT
                    40-black

          30-red

                              22-red

                    21-black

                              19-red
----------------------------------------
Converted Tree::

                    75-red

          60-red

                    55-red

50-red
  ROOT
                    40-red

          30-red

                              22-red

                    21-black

                              19-red
----------------------------------------
BR Tree::

                    75-black

          60-red

                    55-black

50-red
  ROOT
                    40-red

          30-black

                              22-black

                    21-red

                              19-black
*/
