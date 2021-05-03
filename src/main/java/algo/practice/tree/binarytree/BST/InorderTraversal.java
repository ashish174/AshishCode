package algo.practice.tree.binarytree.BST;

import algo.practice.tree.binarytree.Node;

public class InorderTraversal {

    public static Node findInorderSuccessor(Node root, int key){
        Node node = BinarySearchTree.searchBST(root, key);
        return findInorderSuccessor(root, node);
    }

    public static Node findInorderSuccessor(Node root, Node node){
        if(root==null || node==null){
            return null;
        }
        // Case 1 : when right subtree exist
        Node inorderSuccessorNode = null;
        if(node.right!=null){
            inorderSuccessorNode = findMin(node.right);
        } else {
            // Case 2 : when right subtree not exist
            inorderSuccessorNode = findInorderSuccessorFromParentIterative(root, node);
        }
        return inorderSuccessorNode;
    }

    private static Node findInorderSuccessorFromParent(Node root, Node node) {
        if(root==null){
            return null;
        }
        if(root.key < node.key ){
            return findInorderSuccessor(root.right, node);
        } else if(root.key > node.key ){
            return findInorderSuccessor(root.left, node);
        } else {
            return null;
        }
    }

    private static Node findInorderSuccessorFromParentIterative(Node root, Node node) {
        if(root==null){
            return null;
        }
        Node inOrderSuccessor = null;
        while(root!=null){
            if(node.key<root.key){
                inOrderSuccessor = root;
                root = root.left;
            } else if(node.key > root.key){
                root = root.right;
            } else {
                return inOrderSuccessor;
            }
        }
        return null;
    }

    private static Node findMin(Node node) {
        while(node.left!=null){
            node=node.left;
        }
        return node;
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
        System.out.println("\nInorder successor is : "+findInorderSuccessor(root, 10));
    }
}
