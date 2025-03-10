package algo.practice.tree.binarytree;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate next pointer of each node with its successor
 */
public class PopulateInorderSuccessor {
    private static Logger logger = LoggerFactory.getLogger(PopulateInorderSuccessor.class);
    private static NodeWithNext prev = null;

    public static void main(String[] args) {
        NodeWithNext root = new NodeWithNext(1);/*         1        */
        root.left = new NodeWithNext(2);        /*       /   \      */
        root.right = new NodeWithNext(3);       /*     2      3     */
        root.left.left = new NodeWithNext(4);  /*    /  \  /   \   */
        root.left.right = new NodeWithNext(5); /*   4   5  6   7   */
        root.right.left = new NodeWithNext(6);
        root.right.right = new NodeWithNext(7);
        populateInorderSuccessor(root);
        populateInorderSuccessorV2(root, new NodeWithNextWrapper());
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

    /**
     * We are using wrapper so that, when we are reassigning, we do reassignment on inner objects,
     * and hence outer object reference don't change
     */
    public static void populateInorderSuccessorV2(NodeWithNext root, NodeWithNextWrapper prev) {
        if(root == null) {
            return;
        }
        populateInorderSuccessorV2(root.left, prev);
        if(prev.node!=null) {
            logger.info("V2: Key {} Inorder Successor is :- {}", prev.node.key, root.key);
            prev.node.next = root;
        }
        prev.node = root;
        populateInorderSuccessorV2(root.right, prev);
    }


    static class NodeWithNextWrapper {
        NodeWithNext node;

    }

}
