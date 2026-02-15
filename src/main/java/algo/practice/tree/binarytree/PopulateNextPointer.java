package algo.practice.tree.binarytree;

import algo.practice.tree.binarytree.Heap.NodeWithNext;

public class PopulateNextPointer {
    static NodeWithNext parent = null;

    public static void addNextPointer(NodeWithNext node){
        addNextPointerByInorderTraversal(node);
    }

    public static void addNextPointerByInorderTraversal(NodeWithNext node){
        if(node==null){
            return;
        }
        addNextPointerByInorderTraversal(node.left);
        if(parent!=null){
            parent.next = node;
        }
        parent = node;
        addNextPointerByInorderTraversal(node.right);
    }

    public void populateNextpointer(NodeWithNext node)  {
        NodeHolder prev = new NodeHolder();
        doPopulateNextPointerUsingInorder(node, prev);
    }

    private void doPopulateNextPointerUsingInorder(NodeWithNext node, NodeHolder prev) {
        if(node==null){
            return;
        }
        doPopulateNextPointerUsingInorder(node.left, prev);
        if(prev.node!=null){
            prev.node.next = node;
        }
        prev.node = node;
        doPopulateNextPointerUsingInorder(node.right, prev);
    }

    private class NodeHolder {
        NodeWithNext node = null;
    }




    public static void main(String[] args) {
        NodeWithNext root = new NodeWithNext(10);
        root.left = new NodeWithNext(7);
        root.left.left = new NodeWithNext(20);
        root.right = new NodeWithNext(15);
        root.right.right = new NodeWithNext(40);
        //Approach 1 using static
        //addNextPointer(root);
        // Approach 2 without using static. Using holder class
        new PopulateNextPointer().populateNextpointer(root);
        //Print
        NodeWithNext node = root.left.left;
        while(node!=null){
            System.out.print(node.key+"->");
            node = node.next;
        }





    }
}
