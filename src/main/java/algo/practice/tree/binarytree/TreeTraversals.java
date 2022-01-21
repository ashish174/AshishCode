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

    public static void printBoundaryTraversalAntiClockwise(Node root) {
        printLeftBoundaryFromRoot(root);
        printLeafs(root);
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
        while (root != null) {
            if (root.left == null) {
                return;
            }
            logger.info("Left Boundary : {}", root.key);
            root = root.left;
        }
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
        printRightBoundaryFromLeaf(root.right);
        logger.info("Right Boundary : {}", root.key);
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
