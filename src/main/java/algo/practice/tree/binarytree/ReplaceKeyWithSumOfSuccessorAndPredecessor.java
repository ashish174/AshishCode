package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Not Correct :
     * @param root
     */
    private static void convertTreeWithKeyAsSumOfSuccessorAndPredecessorV1(Node root){
        if(root==null){
            return;
        }
        convertTreeWithKeyAsSumOfSuccessorAndPredecessorV1(root.left);
        if(predecessorNode!=null){
            predecessorNode.key = predecessorNode.key + root.key;
        }
        int predecessorKey = predessorActualKey;
        predessorActualKey = root.key;
        root.key = predecessorKey;
        predecessorNode = root;
        convertTreeWithKeyAsSumOfSuccessorAndPredecessorV1(root.right);
    }

    private static void convertTreeWithKeyAsSumOfSuccessorAndPredecessorV2(Node root){
        // Step 1: Get inorder traversal of original keys
        List<Integer> inorderList = new ArrayList<>();
        getInorder(root, inorderList);

        // Step 2: Overwrite node keys as per specification
        replaceKeysWithSum(root, inorderList, new int[]{0});

    }



    /**
     * Step 1: Perform inorder traversal and store all node values in a list.
     */
    private static void getInorder(Node root, List<Integer> inorderList) {
        if (root == null) return;
        getInorder(root.left, inorderList);
        inorderList.add(root.key);
        getInorder(root.right, inorderList);
    }

    /**
     * Step 2: Traverse the tree inorder again, and replace each node's key with
     * the sum of its inorder predecessor and successor.
     * Nodes at the beginning or end (no predecessor/successor) use 0 for missing side.
     */
    private static void replaceKeysWithSum(Node root, List<Integer> inorderList, int[] nodeIdx) {
        if (root == null) return;
        replaceKeysWithSum(root.left, inorderList, nodeIdx);

        int nodeIndex = nodeIdx[0];
        //Get pred from inorderList if exist else 0
        int pred = (nodeIndex > 0) ? inorderList.get(nodeIndex - 1) : 0;
        //Get succ from inorderList if exist else 0
        int succ = (nodeIndex < inorderList.size() - 1) ? inorderList.get(nodeIndex + 1) : 0;
        root.key = pred + succ;

        nodeIdx[0]++; // move to next node in inorder
        replaceKeysWithSum(root.right, inorderList, nodeIdx);
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
        convertTreeWithKeyAsSumOfSuccessorAndPredecessorV1(root);
        PrintTree.printBinaryTree2(root);
    }
}
