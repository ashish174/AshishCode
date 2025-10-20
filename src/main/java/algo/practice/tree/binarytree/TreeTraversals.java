package algo.practice.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Remove few duplicate printing like : 1, 4,7
 */
public class TreeTraversals {
    private static Logger logger = LoggerFactory.getLogger(TreeTraversals.class);

    public static void main(String[] args) {
        Node root = new Node(1);        /*         1        */
        root.left = new Node(2);        /*       /   \      */
        root.right = new Node(3);       /*     2      3     */
        root.left.left = new Node(4);  /*    /  \  /   \   */
        root.left.right = new Node(5); /*   4   5  6   7   */
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        PrintTree.printBinaryTree2(root);
        printBoundaryTraversalAntiClockwise(root);
        logger.info("################## Other Approach ##########################");
        inOrderTraversalIterative(root);
    }

    /**
     * Prints the boundary traversal of a binary tree in anti-clockwise direction,
     * starting from the root. The traversal includes the :-
         * 1. left boundary (excluding the leaf nodes),
         * 2. all leaf nodes (from left to right),
         * 3. and then the right boundary (excluding the leaf nodes and the root, and printed from bottom to top).
     *
     * Duplicate nodes are avoided during traversal to ensure each boundary node
     * is printed only once.
     * <p>
     * The output is logged using the logger in the following order:
     * <ul>
     *     <li>Left boundary nodes (excluding the last node which is a leaf)</li>
     *     <li>Leaf nodes from left to right</li>
     *     <li>Right boundary nodes from bottom to top (excluding leaf and root nodes)</li>
     * </ul>
     *
     * @param root the root node of the binary tree to be traversed
     */
    public static void printBoundaryTraversalAntiClockwise(Node root) {
        printLeftBoundaryFromRoot(root);
        printLeafs(root);
        // Note here we are passing root->right, as root is already included in left boundary
        printRightBoundaryFromLeaf(root.right);
    }

    public static void printDiagonalTraversal(Node root) {
        Queue<Node> queue = new LinkedList<>();


    }

    private static void printLeafs(Node root) {
        if (root == null) {
            return;
        }
        printLeafs(root.left);
        if (root.left == null & root.right == null) {
            logger.info("Leafs : {}", root.key);
        }
        printLeafs(root.right);

    }


    /**
     * exclude last node from printing
     *
     * @param root
     */
    private static void printLeftBoundaryFromRoot(Node root) {
        if(root==null) {
            return;
        }
        if(root.left != null){
            // print current, then go left
            logger.info("Left Boundary : {}", root.key);
            printLeftBoundaryFromRoot(root.left);
        } else if (root.right !=null) {
            // print current, then go right (since left is null)
            logger.info("Left Boundary : {}", root.key);
            printLeftBoundaryFromRoot(root.right);
        }
        // do nothing if it's a leaf
    }

    /**
     * exclude first and last node from printing
     *
     * @param root
     */
    private static void printRightBoundaryFromLeaf(Node root) {
        // exclude last leaf node so as not to count twice
        if (root == null || (root.left == null & root.right == null)) {
            return;
        }
        if(root.right != null) {
            // Go right, and then print it
            printRightBoundaryFromLeaf(root.right);
            logger.info("Right Boundary : {}", root.key);
        } else if (root.left != null) {
            //Go left, and then print it
            printRightBoundaryFromLeaf(root.left);
            logger.info("Right Boundary : {}", root.key);
        }
        // Do nothing if it's a leaf
    }

    private static void inOrderTraversalIterative(Node root) {
        if (root == null) {
            return;
        }
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder("Inorder Iterative Traversal : ");
        while (curr != null || stack.size() > 0) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            stringBuilder.append(curr.key + " ");
            curr = curr.right;
        }
        logger.info("{}", stringBuilder.toString());
    }


}
