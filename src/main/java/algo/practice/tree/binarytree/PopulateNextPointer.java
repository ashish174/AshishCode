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

    public static void main(String[] args) {
        NodeWithNext root = new NodeWithNext(10);
        root.left = new NodeWithNext(7);
        root.left.left = new NodeWithNext(20);
        root.right = new NodeWithNext(15);
        root.right.right = new NodeWithNext(40);
        addNextPointer(root);
        NodeWithNext node = root.left.left;
        while(node!=null){
            System.out.print(node.key+"->");
            node = node.next;
        }
    }
}
