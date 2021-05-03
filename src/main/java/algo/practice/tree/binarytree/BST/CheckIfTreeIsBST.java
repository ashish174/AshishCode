package algo.practice.tree.binarytree.BST;

import algo.practice.tree.binarytree.Node;

public class CheckIfTreeIsBST {
    public static int prev = -1;
    public static boolean isTreeBST(Node root){
        if(root==null){
            return true;
        }
        boolean isLeftSubtreeBST = isTreeBST(root.left);
        boolean isRightSubtreeBST = isTreeBST(root.right);
        return isLeftSubtreeBST && isRightSubtreeBST
                && (root.left == null || root.key > findMax(root.left))
                && (root.right == null || root.key < findMin(root.right));
    }

    private static int findMax(Node node) {
        while(node.right!=null){
            node = node.right;
        }
        return node.key;    }

    private static int findMin(Node node) {
        while(node.left!=null){
            node = node.left;
        }
        return node.key;
    }

    public static boolean isTreeBSTUsingRecursive(Node root) {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTUtil(Node node, int min, int max) {
        if(node==null){
            return true;
        }
        if (node.key < min || node.key > max) {
            return false;
        }
        return isBSTUtil(node.left, min, node.key - 1) && isBSTUtil(node.right, node.key + 1, max);
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.right = new Node(9);
        root.right = new Node(10);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.right.right.right = new Node(16);


        PrintTree.printBinaryTree2(root);
        System.out.println("Is tree BST : "+isTreeBST(root));
        System.out.println("Is tree BST Using recursion : "+isTreeBSTUsingRecursive(root));
    }
}
