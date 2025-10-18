package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Given a binary tree containing n nodes.
 * The problem is to replace each node in the binary tree with the sum of its
 * inorder predecessor and inorder successor (dont include node key).
 * Perform inorder traversal and keep storing previous elements in a array
 */
public class ReplaceKeyWithSumOfSuccessorAndPredecessor {
    public static Logger logger = LoggerFactory.getLogger(ReplaceKeyWithSumOfSuccessorAndPredecessor.class);
    public static Node predecessorNode = null;
    public static int predessorActualKey;

    private static void convertTreeWithKeyAsSumOfSuccessorAndPredecessor(Node root){
        if(root==null){
            return;
        }
        convertTreeWithKeyAsSumOfSuccessorAndPredecessor(root.left);
        if(predecessorNode!=null){
            predecessorNode.key = predecessorNode.key + root.key;
        }
        int predecessorKey = predessorActualKey;
        predessorActualKey = root.key;
        root.key = predecessorKey;
        predecessorNode = root;
        convertTreeWithKeyAsSumOfSuccessorAndPredecessor(root.right);
    }

    public static void main(String[] args) {
        Node root = new Node(1);        /*         1        */
        root.left = new Node(2);        /*       /   \      */
        root.right = new Node(3);       /*     2      3     */
        root.left.left = new Node(4);  /*    /  \  /   \   */
        root.left.right = new Node(5); /*   4   5  6   7   */
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        PrintTree.printBinaryTree2(root);
        convertTreeWithKeyAsSumOfSuccessorAndPredecessor(root);
        PrintTree.printBinaryTree2(root);
    }
}
