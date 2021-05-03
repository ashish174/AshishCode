package algo.practice.tree.binarytree.BST;

import algo.practice.tree.binarytree.Node;

public class LCAAncestor {
    public static Node findLowestCommonAncestor(Node root, int n1, int n2){
        if(root==null){
            return null;
        }
        while(root!=null) {
            if (root.key < n1 && root.key < n2) {
                root = root.right;
            } else if(root.key > n1 && root.key > n2){
                root = root.left;
            } else{
                // root.key lies between n1 & n2
                return root;
            }
        }
        return null;
    }

    public static Node findLCARecursive(Node root, int n1, int n2){
        if(root==null){
            return null;
        }
        if(root.key > n1 && root.key > n2){
            return findLCARecursive(root.left, n1, n2);
        } else if(root.key < n1 && root.key < n2){
            return findLCARecursive(root.right, n1, n2);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right = new Node(10);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.right.right.right = new Node(16);


        PrintTree.printBinaryTree2(root);
        System.out.println("Lowest Common Ancestor for  : "+findLowestCommonAncestor(root, 10, 13 ));
        System.out.println("Lowest Common Ancestor Recursive for  : "+findLCARecursive(root, 10, 13 ));
    }
}
