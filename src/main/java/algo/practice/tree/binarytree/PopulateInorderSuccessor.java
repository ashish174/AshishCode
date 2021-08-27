package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate next pointer of each node with its successor
 */
public class PopulateInorderSuccessor {
    private static Logger logger = LoggerFactory.getLogger(PopulateInorderSuccessor.class);
    private static NodeWithNext prev = null;

    public static void main(String[] args) {
        NodeWithNext root = new NodeWithNext(1);        /*         1        */
        root.left = new NodeWithNext(2);        /*       /   \      */
        root.right = new NodeWithNext(3);       /*     2      3     */
        root.left.left = new NodeWithNext(4);  /*    /  \  /   \   */
        root.left.right = new NodeWithNext(5); /*   4   5  6   7   */
        root.right.left = new NodeWithNext(6);
        root.right.right = new NodeWithNext(7);
        populateInorderSuccessor(root);
    }


    public static void populateInorderSuccessor(NodeWithNext root) {
        if (root == null) {
            return;
        }
        populateInorderSuccessor(root.left);
        if (prev != null) {
            logger.info("Key {} Inorder Successor is :- {}", prev.key, root.key);
            prev.next = root;
        }
        prev = root;
        populateInorderSuccessor(root.right);
    }

}
